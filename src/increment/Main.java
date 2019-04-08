package increment;

public class Main {
    public static void main(final String[] args){
        new Increment().start();
        new Increment().start();
        System.out.println("Main " + Increment.x);
    }
}
