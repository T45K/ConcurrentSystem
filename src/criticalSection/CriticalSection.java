package criticalSection;

import java.util.concurrent.Semaphore;

public class CriticalSection extends Thread {
    private final Semaphore semaphore;
    private static final int NUM_OF_THREADS = 10;

    private CriticalSection(final Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void run() {
        final String name = Thread.currentThread().getName();
        while (true) {
            try {
                Thread.sleep(500);
                semaphore.acquire();
                System.out.println(name + ": entering");
                Thread.sleep(500);
                System.out.println(name + ": leaving");
                semaphore.release();
            } catch (final InterruptedException e) {
                return;
            }

        }
    }

    public static void main(final String[] args) {
        final Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            new CriticalSection(semaphore).start();
        }
    }
}