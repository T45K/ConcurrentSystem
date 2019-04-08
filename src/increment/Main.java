package increment;

public class Main {
    public static void main(final String[] args){
        new Increment().start();
        new Increment().start();
        System.out.println("Main " + Increment.x);
    }
}

/*
    Thraed1    |    Thread2
-------------------------------
  int tmp = x  |
               |  int tmp = x
               |  x = tmp + 1
  x = tmp + 1  |

  bug!!!!!!
 */
