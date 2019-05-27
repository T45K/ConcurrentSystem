package monitor;

public class Increment extends Thread {
    private static final SafeSequence x = new SafeSequence();

    public void run() {
        for (int i = 0; i < 10; i++) {
            final int value = x.getNext();
            System.out.println(value);
        }
    }
}
