package server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String QUESTIONS_FILE = "bazaPytan.txt";
    private static final String ANSWERS_FILE = "bazaOdpowiedzi.txt";
    private static final String RESULTS_FILE = "wyniki.txt";

    public List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(QUESTIONS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String questionContent = line;
                String optionA = br.readLine();
                String optionB = br.readLine();
                String optionC = br.readLine();
                String optionD = br.readLine();
                String correctAnswer = br.readLine();

                if (correctAnswer != null) {
                    questions.add(new Question(questionContent, optionA, optionB, optionC, optionD, correctAnswer));
                }
            }
        } catch (IOException e) {
            System.err.println("Error caught: " + e.getMessage());
        }

        return questions;
    }

    public synchronized void saveAnswer(int clientId, String questionContent, String clientAnswer) {
        try (PrintWriter out = new PrintWriter(new FileWriter(ANSWERS_FILE, true))) {
            out.println("Client " + clientId + " | Question: " + questionContent + " | Answer: " + clientAnswer);
        } catch (IOException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
    }

    public synchronized void saveResult(int clientId, int score, int maxScore) {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESULTS_FILE, true))) {
            out.println("Client " + clientId + " | Score: " + score + "/" + maxScore);
        } catch (IOException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
    }
}
