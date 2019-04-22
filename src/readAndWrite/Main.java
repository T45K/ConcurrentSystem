package readAndWrite;

public class Main {
    public static void main(String[] args) {
        final Resource r = new Resource();
        final UseResource p = new UseResource(r,0);
        final UseResource q = new UseResource(r, 1);
        p.start();
        q.start();
    }
}
