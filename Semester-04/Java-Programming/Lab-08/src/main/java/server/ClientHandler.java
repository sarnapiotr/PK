package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class ClientHandler implements Runnable {
    private final DatagramSocket serverSocket;
    private final ClientSession session;
    private final List<Question> questions;
    private final FileManager fileManager;

    public ClientHandler(DatagramSocket serverSocket, ClientSession session, List<Question> questions, FileManager fileManager) {
        this.serverSocket = serverSocket;
        this.session = session;
        this.questions = questions;
        this.fileManager = fileManager;
    }

    @Override
    public void run() {
        int clientId = session.getClientId();
        InetAddress clientIP = session.getAddress();
        int clientPort = session.getPort();

        System.out.println("Client " + clientId + " started test via UDP");

        try {
            int score = 0;

            send("Client " + clientId, clientIP, clientPort);
            send("You have 15 seconds to answer the question", clientIP, clientPort);

            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);

                send("Question " + (i + 1) + "/" + questions.size() + ":", clientIP, clientPort);
                send(q.toString(), clientIP, clientPort);
                send("---WAITING_FOR_ANSWER---", clientIP, clientPort);

                String answer = "";

                answer = session.waitForAnswer(15000);

                if (answer == null) {
                    answer = "TIMEOUT";
                    send("Time for this question has passed", clientIP, clientPort);
                } else {
                    send("ACK", clientIP, clientPort);

                    answer = answer.trim().toUpperCase();
                    if (answer.equals(q.getCorrectAnswer())) {
                        score++;
                    }
                }

                String shortQuestion = q.toString().split("\n")[0];
                fileManager.saveAnswer(clientId, shortQuestion, answer);
            }

            send("Test ended. Your score is: " + score + "/" + questions.size(), clientIP, clientPort);
            send("---TEST_ENDED---", clientIP, clientPort);

            fileManager.saveResult(clientId, score, questions.size());
            System.out.println("Client " + clientId + " finished test with score: " + score);

        } catch (IOException | InterruptedException e) {
            System.err.println("Error in handler for client " + clientId + ": " + e.getMessage());
        }
    }

    private void send(String msg, InetAddress address, int port) throws IOException {
        byte[] buffer = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        synchronized (serverSocket) {
            serverSocket.send(packet);
        }
    }
}