public class Printer implements Runnable {
    private PrintQueue printQueue;
    private int printTime;
    private int number;

    public Printer(int number, PrintQueue printQueue, int printTime) {
        this.printQueue = printQueue;
        this.printTime = printTime;
        this.number = number;
    }

    public void run() {
        while (!printQueue.printCompleted()) {
            System.out.printf("P%s: %s\n", number, printQueue.nextDocument());
            try {
                Thread.sleep(printTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
