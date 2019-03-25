package engine;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;


public class DealerSchedular {
	public void initSchedular() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
				String current_time_str = time_formatter.format(System.currentTimeMillis());
				System.out.println(current_time_str );
			}
		};

		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 8 * 1000;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
		} // end of main
}
