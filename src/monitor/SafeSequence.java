package monitor;

public class SafeSequence {
    private int nextValue;

    public synchronized int getNext() {
        final int tmp = nextValue;
        nextValue = tmp + 1;
        return nextValue;
    }
}
