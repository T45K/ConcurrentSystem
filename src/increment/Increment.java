package increment;

public class Increment extends Thread {
    static volatile int x = 0;
    public void run(){
        int tmp = x;
        x = tmp + 1;

        System.out.println(x);
    }
}
