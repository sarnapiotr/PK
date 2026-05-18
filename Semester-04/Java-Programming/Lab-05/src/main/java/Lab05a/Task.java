package Lab05a;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {
    private int id;

    public Task(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String call() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
        }

        return "Result: " + (id * 100);
    }
}
