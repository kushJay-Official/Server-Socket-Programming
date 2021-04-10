package Server;

public class SyncExec {
    public static Object Lck1 = new Object();
    public static Object Lck2 = new Object();

    public static void main(String args[]) {
        SyncThread1 T1 = new SyncThread1();
        SyncThread2 T2 = new SyncThread2();
        T1.start();
        T2.start();
    }

    private static class SyncThread1 extends Thread {
        public void run() {
            synchronized (Lck1) {
                System.out.println("Thread 1: Holding lock 1...");

                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                System.out.println("Thread 1: Waiting for lock 2...");

                synchronized (Lck2) {
                    work();
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        }
    }
    private static class SyncThread2 extends Thread {
        public void run() {
            synchronized (Lck2) {
                System.out.println("Thread 2: Holding lock 2...");

                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                System.out.println("Thread 2: Waiting for lock 1...");

                synchronized (Lck1) {
                    work();
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                }
            }
        }
    }

    private static void work()
    {
        try
        {
            //the sleep() method suspends the execution of the current thread for 5 seconds
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
