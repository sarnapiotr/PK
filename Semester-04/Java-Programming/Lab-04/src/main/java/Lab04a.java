import java.util.ArrayList;
import java.util.List;

class Task implements Runnable {
    private int id;
    private Thread thread;

    private String status = "Ready";
    private String result = "";

    public Task(int id) {
        this.id = id;
    }

    public void startTask() {
        thread = new Thread(this);
        thread.start();
    }

    public void cancelTask() {
        thread.interrupt();
    }

    public int getId() { return id; }
    public String getStatus() { return status; }
    public String getResult() { return result; }

    @Override
    public void run() {
        status = "Running";

        try {
            for(int i = 0 ; i < 3; i++) {
                Thread.sleep(1000);
            }
            result = "Result: " + (id * 100);
            status = "Finished";

        } catch(InterruptedException e){
            status = "Canceled";
            result = "Work stopped";
        }
    }
}

public class Lab04a {
    static void main(String[] args) throws InterruptedException {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1));
        taskList.add(new Task(2));
        taskList.add(new Task(3));

        System.out.println("Starting tasks");
        for(Task task : taskList) {
            task.startTask();
        }

        Thread.sleep(500);

        for(Task task : taskList) {
            System.out.println("Task " + task.getId() + " Status: " + task.getStatus());
        }

        System.out.println("Canceling task 2");
        taskList.get(1).cancelTask();

        Thread.sleep(3000);

        System.out.println("Summary");
        for(Task task : taskList) {
            System.out.println("Task " + task.getId() + " Status: " + task.getStatus());
        }
    }
}
