package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<Question> questions;
    private FileManager fileManager;
    private int clientId;

    public ClientHandler(Socket clientSocket, List<Question> questions, FileManager fileManager, int clientId) {
        this.clientSocket = clientSocket;
        this.questions = questions;
        this.fileManager = fileManager;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        System.out.println("Client " + clientId + " started test");

        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            clientSocket.setSoTimeout(15000);

            int score = 0;

            out.println("Client " + clientId);
            out.println("You have 15 seconds to answer the question");

            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);

                out.println("Question " + (i + 1) + "/" + questions.size() + ":");
                out.println(q);
                out.println("---WAITING_FOR_ANSWER---");

                String answer = "";

                try {
                    answer = in.readLine();

                    if (answer == null) {
                        System.out.println("Student " + clientId + " disconnected");
                        break;
                    }

                    answer = answer.trim().toUpperCase();

                    if (answer.equals(q.getCorrectAnswer())) {
                        score++;
                    }

                } catch (SocketTimeoutException e) {
                    answer = "TIMEOUT";
                    out.println("Time for this question has passed");
                }

                String shortQuestion = q.toString().split("\n")[0];
                fileManager.saveAnswer(clientId, shortQuestion, answer);
            }

            out.println("Test ended. Your score is: " + score + "/" + questions.size());
            out.println("---TEST_ENDED---");

            fileManager.saveResult(clientId, score, questions.size());
            System.out.printf("Client " + clientId + " finished test with score: " + score);

        } catch (IOException e) {
            System.err.println("Error caught when connecting with client " + clientId + ": " + e.getMessage());
        } finally {
            try {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.err.println("Error caught: " + e.getMessage());
            }
        }
    }
}
