package readAndWrite;

public class UseResource extends Thread{
    private final Resource r;

    public UseResource (final Resource r,final int id){
        super(Integer.toString(id));
        this.r  =r;
    }

    public void run(){
        while(true){
            r.access();
        }
    }
}
