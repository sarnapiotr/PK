package server;

import java.net.InetAddress;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ClientSession {
    private final int clientId;
    private final InetAddress address;
    private final int port;
    private final LinkedBlockingQueue<String> answerQueue;

    public ClientSession(int clientId, InetAddress address, int port) {
        this.clientId = clientId;
        this.address = address;
        this.port = port;
        this.answerQueue = new LinkedBlockingQueue<>();
    }

    public int getClientId() { return clientId; }
    public InetAddress getAddress() { return address; }
    public int getPort() { return port; }

    public void addAnswer(String answer) {
        answerQueue.offer(answer);
    }

    public String waitForAnswer(long timeoutMs) throws InterruptedException {
        return answerQueue.poll(timeoutMs, TimeUnit.MILLISECONDS);
    }
}