package readersWriters;

public class ReaderThread extends Thread {
    private final Data2 data;

    public ReaderThread(final Data2 data) {
        this.data = data;
    }

    public void run() {
        try {
            while (true) {
                final char[] buffer = data.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(buffer));
            }
        } catch (final InterruptedException e) {
        }
    }
}
