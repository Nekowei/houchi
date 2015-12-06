package nekowei.houchi.task;

import nekowei.houchi.entity.Mystery;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class MysTask extends Handler {

	private Handler handler = new Handler();
	
	public MysTask(Looper looper) {
		super(looper);
	}

	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case Mystery.ID:
			handler.post(new Runnable() {
				@Override
				public void run() {
					Mystery.getInstance().updateMysCount();
				}
			});
			this.sendEmptyMessageDelayed(Mystery.ID, 1000);
			break;
		}
		super.handleMessage(msg);
	}
	
}
