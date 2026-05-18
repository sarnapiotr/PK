package Lab05b;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TaskHandle<V> extends FutureTask<V> {
    private int taskId;

    public TaskHandle(Callable<V> callable, int taskId) {
        super(callable);
        this.taskId = taskId;
    }

    @Override
    protected void done() {
        try {
            V result = get();
            System.out.println("Task: " + taskId + " Result: " + result);
        } catch (ExecutionException | InterruptedException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
