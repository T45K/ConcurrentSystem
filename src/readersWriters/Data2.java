package readersWriters;

public class Data2 {
    private final char[] buffer;
    //  private final ReadWriteLock lock = new ReadWriteLockUnsafe();
    //  private final ReadWriteLock lock = new ReadWriteLockSafe();
    //  private final ReadWriteLock lock = new ReadWriteLockWritePriority();
    private final ReadWriteLock lock = new ReadWriteLockLive();

    public Data2(final int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        lock.acquireRead();
        try {
            return doRead();
        } finally {
            lock.releaseRead();
        }
    }

    public void write(final char c) throws InterruptedException {
        lock.acquireWrite();
        try {
            doWrite(c);
        } finally {
            lock.releaseWrite();
        }
    }

    private char[] doRead() {
        char[] newBuffer = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuffer[i] = buffer[i];
            slowly();
        }
        slowly();
        return newBuffer;
    }

    private void doWrite(final char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly();
        }
    }

    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (final InterruptedException e) {
        }
    }
}
