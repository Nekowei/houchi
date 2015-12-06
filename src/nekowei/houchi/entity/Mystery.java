package nekowei.houchi.entity;

import nekowei.houchi.event.OnMysChangeListener;

public class Mystery {
	
	private long mysCount;
	private static Mystery mystery;
	private OnMysChangeListener onMysChangeListener;
	
	private long mysTime = System.currentTimeMillis();
	
	public static final int ID = 1234;
	
	public static Mystery getInstance(){
		if (mystery == null) {
			mystery = new Mystery();
		}
		return mystery;
	}
	
	private Mystery() {
		mysCount = 0;
	}
	
	public void setOnMysChangeListener(OnMysChangeListener onMysChangeListener) {
		this.onMysChangeListener = onMysChangeListener;
	}
	
	public synchronized void updateMysCount() {
		mysCount = System.currentTimeMillis() - mysTime;
		onMysChangeListener.onMysCountChange(mysCount);
	}
	
	public long getMysCount() {
		return mysCount;
	}

	public long getMysTime() {
		return mysTime;
	}

	public void setMysTime(long mysTime) {
		this.mysTime = mysTime;
	}
}
