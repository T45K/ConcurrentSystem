package readAndWrite;

public class LockAttempt1 implements Lock{
    private volatile int turn = 0;
    @Override
    public void lock() {
        final int id = Integer.parseInt(Thread.currentThread().getName());
        while (turn != id);
    }

    @Override
    public void unlock() {
        final int id = Integer.parseInt(Thread.currentThread().getName());
        turn = 1 - id;
    }
}
