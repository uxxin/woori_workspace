package dev.syntax.step03_blocking_queue;

public class ThreadUtil {
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
