package conditionSynchronization;

import java.util.Random;

public class MakerThread extends Thread {
    private final Random random;
    private final Table table;
    private static int id = 0;

    public MakerThread(final String name, final Table table, final long seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                String cake = "[ Cake No." + nextId() + " by " + getName() + " ]";
                table.put(cake);
            }
        } catch (final InterruptedException e) {
        }
    }

    private static synchronized int nextId() {
        return id++;
    }
}
