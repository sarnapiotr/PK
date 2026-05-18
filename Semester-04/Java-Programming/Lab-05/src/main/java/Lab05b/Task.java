package Lab05b;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {
    private int id;
    private int workTimeMs;

    public Task(int id, int workTimeMs) {
        this.id = id;
        this.workTimeMs = workTimeMs;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(workTimeMs);
        return "Result: " + (id * 100);
    }
}
