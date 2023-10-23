import java.time.LocalTime;

public class AnalyzeThreadDump {
	public static void main(String[] args) {
		Thread timeThread = new Thread(() -> {
			while (true) {
				try {
					System.out.println(LocalTime.now());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "timeThread");


		Thread helloThread = new Thread(() -> {
			while(true) {
				System.out.println("Hello world!");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "helloThread");

		timeThread.start();
		helloThread.start();
		System.out.println("Main thread finished");
	}
}
