package Lab05a;

import java.util.concurrent.Future;

public class TaskHandle {
    private Task task;
    private Future<String> future;

    public TaskHandle(Task task, Future<String> future) {
        this.task = task;
        this.future = future;
    }

    public Task getTask() { return task; }
    public Future<String> getFuture() { return future; }

    public String getStatus() {
        if (future.isCancelled()) return "CANCELLED";
        if (future.isDone()) return "DONE";
        return "RUNNING";
    }
}
