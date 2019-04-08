package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static final AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) {
        final MyThread myThread = new MyThread();
        myThread.start();
        for (int i = 0; i < 1000; i++) {
            System.out.print(atomicInteger.getAndIncrement() + "Good!");
        }
    }
}
