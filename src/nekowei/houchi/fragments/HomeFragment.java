package nekowei.houchi.fragments;

import nekowei.houchi.R;
import nekowei.houchi.entity.Mystery;
import nekowei.houchi.event.OnMysChangeListener;
import nekowei.houchi.task.MysTask;
import android.app.Fragment;
import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment implements OnMysChangeListener {

	private TextView mysText;
	private MysTask mt;
	private HandlerThread ht;

	public HomeFragment(HandlerThread ht) {
		this.ht = ht;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		mysText = (TextView) getActivity().findViewById(R.id.mysText);
		mysText.setText(getResources().getString(R.string.mys_count)+"0");
		Mystery.getInstance().setOnMysChangeListener(this);
		mt = new MysTask(ht.getLooper());
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onResume() {
		mt.sendEmptyMessage(Mystery.ID);
		super.onResume();
	}
	
	@Override
	public void onPause() {
		mt.removeMessages(Mystery.ID);
		super.onPause();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onMysCountChange(long count) {
		CharSequence time = ""+count;
		// 不显示毫秒位
		if (time.length() > 3) {			
			time = time.subSequence(0, time.length() - 3);
		} else {
			time = "0";
		}
		String text = getResources().getString(R.string.mys_count) + time;
		Log.i("mys", "text:"+text);
		mysText.setText(text);
	}

}
