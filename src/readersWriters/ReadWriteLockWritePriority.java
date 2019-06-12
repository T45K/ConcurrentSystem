package readersWriters;

public class ReadWriteLockWritePriority implements ReadWriteLock {
    private int readingReaders = 0;
    private int writingWriters = 0;
    private int waitingWriters = 0;

    @Override
    public synchronized void acquireRead() throws InterruptedException {
        while (writingWriters > 0 || waitingWriters > 0) {
            wait();
        }
        readingReaders++;
    }

    @Override
    public synchronized void releaseRead() {
        readingReaders--;
        notifyAll();
    }

    @Override
    public synchronized void acquireWrite() throws InterruptedException {
        waitingWriters++;
        while (readingReaders > 0 || writingWriters > 0) {
            wait();
        }
        waitingWriters--;
        writingWriters++;
    }

    @Override
    public synchronized void releaseWrite() {
        writingWriters--;
        notifyAll();
    }
}
