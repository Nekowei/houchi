package nekowei.houchi;

import nekowei.houchi.entity.Mystery;
import nekowei.houchi.fragments.EnterFragment;
import nekowei.houchi.fragments.HomeFragment;
import nekowei.houchi.util.FileManager;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.HandlerThread;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MainActivity extends Activity implements OnClickListener {

	private EnterFragment ef;
	private HomeFragment hf;
	private HandlerThread ht= new HandlerThread("mysHandler");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// 读档
		if (FileManager.isExternalStorageReadable()) {
			FileManager.load();
		} else {			
			SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
			long time = sp.getLong(getString(R.string.mys_time), System.currentTimeMillis());
			Mystery.getInstance().setMysTime(time);
		}
		// 预设面板
		FragmentTransaction t = getFragmentManager().beginTransaction();
		ef = new EnterFragment(this);
		t.add(R.id.id_content, ef);
		t.commit();
		// 启动handler
		ht.start();
	}

	@Override
	public void onClick(View v) {
		FragmentTransaction t = getFragmentManager().beginTransaction();
		switch (v.getId()) {
		case R.id.enter_image:
			if (hf == null)
				hf = new HomeFragment(ht);
			t.replace(R.id.id_content, hf);
			break;
		}
		t.commit();
	}
		
	@Override
	protected void onDestroy() {
		// 结束handler
		ht.quit();
		// 存盘
		if (FileManager.isExternalStorageWritable()) {
			FileManager.save();
		} else {
			Editor e = getPreferences(Context.MODE_PRIVATE).edit();
			long time = Mystery.getInstance().getMysTime();
			e.putLong(getString(R.string.mys_time), time);
			e.apply();
		}
		super.onDestroy();
	}

}
