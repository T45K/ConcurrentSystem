package readersWriters;

public class ReadWriteLockWriterPriority implements ReadWriteLock {
    private int readingReaders = 0;
    private int writingWriters = 0;
    private int waitingWriters = 0;

    @Override
    public void acquireRead() throws InterruptedException {
        while (writingWriters > 0 || waitingWriters > 0) {
            wait();
        }
        readingReaders++;
    }

    @Override
    public void releaseRead() {
        readingReaders--;
        notifyAll();
    }

    @Override
    public void acquireWrite() throws InterruptedException {
        waitingWriters++;
        while (readingReaders > 0 || writingWriters > 0) {
            wait();
        }
        waitingWriters--;
        writingWriters++;
    }

    @Override
    public void releaseWrite() {
        writingWriters++;
        notifyAll();
    }
}
