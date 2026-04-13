import java.util.ArrayList;
import java.util.List;

class Task implements Runnable {
    private int id;
    private volatile String result = "";
    private Thread thread;

    public Task(int id) {
        this.id = id;
    }

    public void startTask() {
        thread = new Thread(this);
        thread.start();
    }

    public void cancelTask() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    public int getId() { return id; }
    public String getResult() { return result; }

    public Thread.State getStatus() {
        if (thread == null) {
            return Thread.State.NEW;
        }

        return thread.getState();
    }

    @Override
    public void run() {
        try {
            for(int i = 0 ; i < 3; i++) {
                Thread.sleep(1000);
            }
            result = "Result: " + (id * 100);

        } catch(InterruptedException e){
            result = "Work stopped";
        }
    }
}

public class Lab04a {
    public static void main(String[] args) throws InterruptedException {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1));
        taskList.add(new Task(2));
        taskList.add(new Task(3));

        System.out.println("Starting tasks");
        for(Task task : taskList) {
            task.startTask();
        }
        Thread.sleep(100);
        for(Task task : taskList) {
            System.out.println("Task " + task.getId() + " Status: " + task.getStatus());
        }

        System.out.println("Canceling task 2");
        taskList.get(1).cancelTask();
        Thread.sleep(100);
        for(Task task : taskList) {
            System.out.println("Task " + task.getId() + " Status: " + task.getStatus());
        }

        Thread.sleep(3000);

        System.out.println("Summary");
        for(Task task : taskList) {
            System.out.println("Task " + task.getId() + " Status: " + task.getStatus() + " Result: " + task.getResult());
        }
    }
}
