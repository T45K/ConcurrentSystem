package readAndWrite;

public class Resource {
    private Lock mutex = new LockAttempt1();

    public void access() {
        mutex.lock();
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " enters CS");
            Thread.sleep(100);
            if (Math.random() < 0.01) {
                Thread.yield();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " leaves CS");
        } catch (final InterruptedException e) {
        }
        mutex.unlock();
    }
}
