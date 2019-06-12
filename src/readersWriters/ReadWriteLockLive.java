package readersWriters;

public class ReadWriteLockLive implements ReadWriteLock {
    private boolean isReadersTurn = false;
    private int writingWriters = 0;

    @Override
    public synchronized void acquireRead() throws InterruptedException {
        while (!isReadersTurn) {
            wait();
        }
    }

    @Override
    public synchronized void releaseRead() {
        isReadersTurn = false;
        notifyAll();
    }

    @Override
    public synchronized void acquireWrite() throws InterruptedException {
        while (isReadersTurn || writingWriters > 0) {
            wait();
        }
        writingWriters++;
    }

    @Override
    public synchronized void releaseWrite() {
        writingWriters--;
        isReadersTurn = true;
        notifyAll();
    }
}
