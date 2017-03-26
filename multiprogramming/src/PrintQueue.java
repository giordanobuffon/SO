import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

    private String[] queue;
    private int maxSize;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int writingIndex = 0;
    private int readingIndex = 0;
    private boolean queueCompleted;

    public PrintQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new String[maxSize];
    }

    public String nextDocument() {
        if (readingIndex < maxSize) {
            String document = "<error>";
            lock.lock();
            try {
                while (queue[readingIndex] == null) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        System.out.printf("Problem detected: <%s>\n", e.getMessage());
                    }
                }
                document = queue[readingIndex];
                readingIndex++;
            } finally {
                lock.unlock();
                return document;
            }
        }
        return "Finished print list ";
    }

    public void setDocument(String document) {
        lock.lock();
        queue[writingIndex] = document;
        writingIndex++;
        condition.signalAll();
        lock.unlock();
        queueCompleted = writingIndex >= maxSize;
    }

    public boolean queueCompleted() {
        return queueCompleted;
    }

    public boolean printCompleted() {
        return readingIndex >= maxSize;
    }

}
