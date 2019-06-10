package readersWriters;

public interface ReadWriteLock {
    public void acquireRead()
            throws InterruptedException;

    public void releaseRead();

    public void acquireWrite()
            throws InterruptedException;

    public void releaseWrite();
}
