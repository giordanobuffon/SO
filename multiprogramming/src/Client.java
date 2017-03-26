import java.util.ArrayList;
import java.util.Random;

public class Client implements Runnable {
    private Random rand;
    private PrintQueue printQueue;
    private int sleepTime;
    private int number;
    private int index = 1;

    public Client(int number, PrintQueue printQueue, int sleepTime) {
        this.number = number;
        this.printQueue = printQueue;
        rand = new Random();
        this.sleepTime = sleepTime;
    }

    public void run() {
        while (!printQueue.queueCompleted()) {
            String document = "C" + number + " -> doc " + (index);
            printQueue.setDocument(document);
            index++;
            try {
                Thread.sleep((rand.nextInt(sleepTime) + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
