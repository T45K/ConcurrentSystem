package phil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Phil extends Thread {
    private static final int NUM_OF_PHILS = 5;
    private final Semaphore left, right;
    private final int id;

    // the number of fork been used
    // this value is operated atomically
    private static AtomicInteger numOfUsedFork = new AtomicInteger(0);

    private Phil(final Semaphore left, final Semaphore right, final int id) {
        this.left = left;
        this.right = right;
        this.id = id;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextLong(100, 200));

                // if more than 3 forks are been used, taking last fork is prohibited to avoid deadlock
                while (numOfUsedFork.get() >= NUM_OF_PHILS - 1) ;
                right.acquire();
                System.out.println(id + " got right fork");
                numOfUsedFork.incrementAndGet();

                left.acquire();
                System.out.println(id + " got left fork");
                numOfUsedFork.incrementAndGet();

                Thread.sleep(100);

                right.release();
                System.out.println(id + " put right fork");
                numOfUsedFork.decrementAndGet();

                left.release();
                System.out.println(id + " put left fork");
                numOfUsedFork.decrementAndGet();
            } catch (final InterruptedException e) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        final Semaphore[] fork = new Semaphore[NUM_OF_PHILS];

        for (int i = 0; i < NUM_OF_PHILS; i++) {
            fork[i] = new Semaphore(1);
        }

        for (int i = 0; i < NUM_OF_PHILS; i++) {
            new Phil(fork[(i + NUM_OF_PHILS - 1) % NUM_OF_PHILS], fork[i], i).start();
        }
    }
}
