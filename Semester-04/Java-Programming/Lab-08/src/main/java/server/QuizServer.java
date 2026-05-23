package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuizServer {
    private static final int PORT = 8080;
    private static final int MAX_CLIENTS = 250;

    public static void main(String[] args) {
        System.out.println("UDP Server started");

        FileManager fileManager = new FileManager();
        List<Question> questions = fileManager.loadQuestions();

        if (questions.isEmpty()) {
            System.err.println("Questions file is empty!");
            System.err.println("Server terminated");
            return;
        }
        System.out.println("Loaded " + questions.size() + " questions");

        ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENTS);

        Map<String, ClientSession> activeClients = new ConcurrentHashMap<>();

        int clientIdCounter = 1;

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("UDP Server listening on port " + PORT);
            System.out.println("Ready to handle " + MAX_CLIENTS + " clients");

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet);

                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                String message = new String(packet.getData(), 0, packet.getLength()).trim();

                String clientKey = clientAddress.toString() + ":" + clientPort;

                if (message.equals("HELLO")) {
                    if (!activeClients.containsKey(clientKey)) {
                        System.out.println("[NEW UDP CONNECTION] from " + clientKey);

                        ClientSession newSession = new ClientSession(clientIdCounter++, clientAddress, clientPort);
                        activeClients.put(clientKey, newSession);

                        ClientHandler handler = new ClientHandler(serverSocket, newSession, questions, fileManager);
                        pool.execute(handler);
                    }
                } else {
                    ClientSession existingSession = activeClients.get(clientKey);

                    if (existingSession != null) {
                        existingSession.addAnswer(message);
                    } else {
                        System.out.println("[WARNING] Package recieved from unregistered Client: " + clientKey);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error caught: " + e.getMessage());
        } finally {
            pool.shutdown();
            System.out.println("Server terminated");
        }
    }
}