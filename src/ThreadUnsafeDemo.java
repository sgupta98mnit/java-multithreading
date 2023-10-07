import java.util.Random;

public class ThreadUnsafeDemo {
    static Random random = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws InterruptedException {
        UnsafeThread t = new UnsafeThread();

        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    t.increment();
                    ThreadUnsafeDemo.sleepRandomlyForTenSecs();
                }
            }
        });

        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    t.decrement();
                    ThreadUnsafeDemo.sleepRandomlyForTenSecs();
                }
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        t.printCount();
    }

    public static void sleepRandomlyForTenSecs() {
        try {
            Thread.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class UnsafeThread {
    int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public void printCount() {
        System.out.println("The count is " + count);
    }
}
