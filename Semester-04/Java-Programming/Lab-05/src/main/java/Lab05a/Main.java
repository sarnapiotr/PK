package Lab05a;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<TaskHandle> taskList = new ArrayList<>();

        System.out.println("Starting tasks");
        for (int i = 1; i <= 3; i++) {
            Task task = new Task(i);
            Future<String> future = pool.submit(task);
            taskList.add(new TaskHandle(task, future));
        }

        Thread.sleep(100);

        for (TaskHandle handle : taskList) {
            System.out.println("Task: " + handle.getTask().getId() + " Status: " + handle.getStatus());
        }

        System.out.println("Cancelling task 2");
        taskList.get(1).getFuture().cancel(true);

        Thread.sleep(100);

        for (TaskHandle handle : taskList) {
            System.out.println("Task: " + handle.getTask().getId() + " Status: " + handle.getStatus());
        }

        Thread.sleep(3000);

        System.out.println("Summary");
        for (TaskHandle handle : taskList) {
            String result = "";
            Future<String> future = handle.getFuture();

            if (future.isCancelled()) {
                result = "Work stopped";
            } else {
                try {
                    result = future.get();
                } catch (ExecutionException e) {
                    System.err.println(e.getMessage());
                }
            }

            System.out.println("Task: " + handle.getTask().getId() + " Status: " + handle.getStatus() + " Result: " + result);
        }

        pool.shutdown();
    }
}
