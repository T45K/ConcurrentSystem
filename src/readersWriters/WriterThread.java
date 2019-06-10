package readersWriters;

import java.util.Random;

public class WriterThread extends Thread {
    private static final Random random = new Random();
    private final Data2 data;
    private final String filler;
    private int index = 0;

    public WriterThread(final Data2 data, final String filler) {
        this.data = data;
        this.filler = filler;
    }

    public void run() {
        try {
            while (true) {
                final char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(3000));
            }
        } catch (final InterruptedException e) {
        }
    }

    private char nextChar() {
        final char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }
}
