import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        int numClient = 5;
        int numPrinter = 4;
        int sleepTime = 100;
        int printTime = 500;
        int maxQueueSize = 40;

        PrintQueue printQueue = new PrintQueue(maxQueueSize);
        Client[] clients = new Client[numClient];
        Printer[] printers = new Printer[numPrinter];
        Thread[] threads = new Thread[numClient + numPrinter];

        // creating clients
        for (int i = 0; i < numClient; i++) {
            clients[i] = new Client((i + 1), printQueue, sleepTime);
        }

        // creating client threads
        for (int i = 0; i < numClient; i++) {
            threads[i] = new Thread(clients[i]);
        }

        // creating printers
        for (int i = 0; i < numPrinter; i++) {
            printers[i] = new Printer((i + 1), printQueue, printTime);
        }

        // creating printer threads
        int x = 0;
        for (int i = numClient; i < (numClient + numPrinter); i++) {
            threads[i] = new Thread(printers[x]);
            x++;
        }

        // starting threads
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

    }

}
