package readersWriters;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Data1 {
    private final char[] buffer;
    private final ReadWriteLock rwlock = new ReentrantReadWriteLock(true);
    private final Lock readlock = rwlock.readLock();
    private final Lock writelock = rwlock.writeLock();
 
    public Data1(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }
    public char[] read() throws InterruptedException {
        readlock.lock();
        try {
            return doRead();
        } finally {
            readlock.unlock();
        }
    }
    public void write(char c) throws InterruptedException {
        writelock.lock();
        try {
            doWrite(c);
        } finally {
            writelock.unlock();
        }
    }
    private char[] doRead() {
        char[] newbuf = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newbuf[i] = buffer[i];
        }
        slowly();
        return newbuf;
    }
    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly();
        }
    }
    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
    }
}
