package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuizServer {
    private static final int PORT = 12345;
    private static final int MAX_CLIENTS = 250;

    public static void main(String[] args) {
        System.out.println("Server started");

        FileManager fileManager = new FileManager();
        List<Question> questions = fileManager.loadQuestions();

        if (questions.isEmpty()) {
            System.err.println("Questions file is empty!");
            System.err.println("Server terminated");
            return;
        }
        System.out.println("Loaded " + questions.size() + " questions");

        ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENTS);

        int clientIdCounter = 1;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server listening on port " + PORT);
            System.out.println("Ready to handle " + MAX_CLIENTS + " clients");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[NEW CONNECTION]");

                ClientHandler clientTask = new ClientHandler(clientSocket, questions, fileManager, clientIdCounter);

                pool.execute(clientTask);

                clientIdCounter++;
            }

        } catch (IOException e) {
            System.err.println("Error caught: " + e.getMessage());
        } finally {
            pool.shutdown();
            System.out.println("Server terminated");
        }
    }
}