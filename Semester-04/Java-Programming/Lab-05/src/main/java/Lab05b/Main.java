package Lab05b;

import Lab05b.Task;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        System.out.println("Starting tasks");
        for (int i = 1; i <= 3; i++) {
            TaskHandle<String> task = new TaskHandle<>(new Task(i, i*1000), i);
            pool.execute(task);
        }

        Thread.sleep(100);

        pool.shutdown();
    }
}
