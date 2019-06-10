package readersWriters;

public class Main {
    public static void main(final String[] args) {
        final Data2 data = new Data2(10);
        new ReaderThread(data).start();
        new ReaderThread(data).start();

        new WriterThread(data, "ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriterThread(data, "abcdefghijklmnopqrstuvwxyz").start();
        //        new WriterThread(data, "0123456789").start();
        //        new WriterThread(data, "!#$%&'()=~|").start();
    }
}
