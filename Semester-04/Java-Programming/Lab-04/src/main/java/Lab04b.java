import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class TicketPool {
    private boolean[] tickets = new boolean[3];

    public boolean reserve(int ticketId, int clientId) {
        if (!tickets[ticketId]) {
            tickets[ticketId] = true;
            System.out.println("Client " + clientId + " Reserved ticket: " + ticketId);
            return true;
        }
        return false;
    }

    public void release(int ticketId, int clientId) {
        tickets[ticketId] = false;
        System.out.println("Client " + clientId + " Released ticket: " + ticketId);
    }
}

class ClientThread implements Runnable {
    private int id;
    private TicketPool ticketPool;
    private Thread thread;
    private Random random = new Random();

    public ClientThread(int id, TicketPool ticketPool) {
        this.id = id;
        this.ticketPool = ticketPool;
    }

    public void startClient() {
        thread = new Thread(this);
        thread.start();
    }

    public void cancelClient() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int ticketId = random.nextInt(3);

            if (ticketPool.reserve(ticketId, id)) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Client " + id + " canceled while holding a ticket ");
                    ticketPool.release(ticketId, id);
                    break;
                }

                ticketPool.release(ticketId, id);
                try { Thread.sleep(200); } catch (InterruptedException e) { break; }

            } else {
                System.out.println("Client " + id + " is waiting for ticket: " + ticketId);
                try { Thread.sleep(200); } catch (InterruptedException e) { break; }
            }
        }
        System.out.println("Client " + id + " finished");
    }
}

public class Lab04b {
    public static void main(String[] args) throws InterruptedException {
        TicketPool ticketPool = new TicketPool();
        List<ClientThread> clientThreads = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            clientThreads.add(new ClientThread(i, ticketPool));
        }

        System.out.println("Simulation start");
        for (ClientThread client : clientThreads) {
            client.startClient();
        }
        Thread.sleep(1500);

        System.out.println("Canceling task 3");
        clientThreads.get(2).cancelClient();

        Thread.sleep(1000);

        System.out.println("Canceling all tasks");
        for (ClientThread client : clientThreads) {
            client.cancelClient();
        }
    }
}