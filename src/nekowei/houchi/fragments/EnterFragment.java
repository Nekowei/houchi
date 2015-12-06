package nekowei.houchi.fragments;

import nekowei.houchi.R;
import nekowei.houchi.task.EnterTask;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class EnterFragment extends Fragment {

	private TextView tv;
	private ImageView iv;
	private OnClickListener onClickListener;
	
	public EnterFragment(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_enter, container, false);
	}
	
	@Override
	public void onStart() {
		iv = (ImageView) getActivity().findViewById(R.id.enter_image);
		iv.setOnClickListener(onClickListener);
		
		EnterTask h = new EnterTask(this);
		h.sendEmptyMessageDelayed(111, 3000);
		super.onStart();
	}

	public void onEnter() {
		Log.i("mys", "onEnter");
		if (getActivity() != null) {			
			tv = (TextView) getActivity().findViewById(R.id.enter_text);
			tv.setText("你还不打算开始么！");
		}
	}
}
