package nekowei.houchi.task;

import nekowei.houchi.fragments.EnterFragment;
import android.os.Handler;
import android.os.Message;

public class EnterTask extends Handler {

	EnterFragment ef;
	
	public EnterTask(EnterFragment ef) {
		this.ef = ef;
	}
	
	@Override
	public void handleMessage(Message msg) {
		this.post(new Runnable() {
			@Override
			public void run() {
				ef.onEnter();
			}
		});
		super.handleMessage(msg);
	}
}
