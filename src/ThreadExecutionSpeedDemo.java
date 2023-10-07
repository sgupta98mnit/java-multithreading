public class ThreadExecutionSpeedDemo {
    public static void main(String[] args) throws InterruptedException {
        SumExample s =  new SumExample();
        try {
            s.runTest();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class SumExample {
        long startRange;
        long endRange;
        long counter = 0;
        static long MAX_INT = Integer.MAX_VALUE;

        public SumExample(long startRange, long endRange) {
            this.startRange = startRange;
            this.endRange = endRange;
        }

        public SumExample() {

        }

        public void add() {
            for (long i = startRange; i <= endRange; i++) {
                counter += i;
            }
        }

        public void twoThreads() throws InterruptedException {
            long startTime = System.currentTimeMillis();
            SumExample s1 = new SumExample(1, MAX_INT / 2);
            SumExample s2 = new SumExample(1 + (MAX_INT / 2), MAX_INT);

            Thread t1 = new Thread(() -> {
                s1.add();
            });

            Thread t2 = new Thread(() -> {
                s2.add();
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            long finalSum = s1.counter + s2.counter;
            long endTime = System.currentTimeMillis();
            System.out.println("Sum using two threads: " + finalSum + " took " + (endTime - startTime) + " milliseconds");
        }

        public void oneThread() {
            long startTime = System.currentTimeMillis();
            SumExample s = new SumExample(1, MAX_INT);
            s.add();
            long endTime = System.currentTimeMillis();
            System.out.println("final sum using one thread: " + s.counter + " took " + (endTime - startTime) + " milliseconds");
        }

        public void runTest() throws InterruptedException {
            oneThread();
            twoThreads();
        }
    }
}
