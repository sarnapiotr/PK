package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL_SERVER = "jdbc:mysql://localhost:3306/";
    private static final String URL_DB = "jdbc:mysql://localhost:3306/quiz_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public DatabaseManager() {
        initDatabase();
    }

    private void initDatabase() {
        try (Connection conn = DriverManager.getConnection(URL_SERVER, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS quiz_db;");
            System.out.println("Database 'quiz_db' checked/created");

        } catch (SQLException e) {
            System.err.println("Error caught: " + e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(URL_DB, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS questions (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "content VARCHAR(255), " +
                    "optA VARCHAR(100), optB VARCHAR(100), optC VARCHAR(100), optD VARCHAR(100), " +
                    "correctAnswer VARCHAR(5))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS answers (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "client_id INT, " +
                    "question_content VARCHAR(255), " +
                    "client_answer VARCHAR(50))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS results (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "client_id INT, " +
                    "score INT, " +
                    "max_score INT)");

            System.out.println("Tables checked/created");

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM questions");
            if (rs.next() && rs.getInt(1) == 0) {
                seedQuestionsFromTxt(conn);
            }

        } catch (SQLException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
    }

    private void seedQuestionsFromTxt(Connection conn) {
        System.out.println("Empty questions table. Importing data from .txt file");
        String sql = "INSERT INTO questions (content, optA, optB, optC, optD, correctAnswer) VALUES (?, ?, ?, ?, ?, ?)";

        try (BufferedReader br = new BufferedReader(new FileReader("bazaPytan.txt"));
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                pstmt.setString(1, line);
                pstmt.setString(2, br.readLine());
                pstmt.setString(3, br.readLine());
                pstmt.setString(4, br.readLine());
                pstmt.setString(5, br.readLine());
                pstmt.setString(6, br.readLine().trim().toUpperCase());

                pstmt.executeUpdate();
            }
            System.out.println("Imported questions to table");

        } catch (IOException | SQLException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
    }

    public List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL_DB, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM questions")) {

            while (rs.next()) {
                String content = rs.getString("content");
                String optA = rs.getString("optA");
                String optB = rs.getString("optB");
                String optC = rs.getString("optC");
                String optD = rs.getString("optD");
                String correct = rs.getString("correctAnswer");

                questions.add(new Question(content, optA, optB, optC, optD, correct));
            }
        } catch (SQLException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        return questions;
    }

    public synchronized void saveAnswer(int clientId, String questionContent, String clientAnswer) {
        String sql = "INSERT INTO answers (client_id, question_content, client_answer) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL_DB, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clientId);
            pstmt.setString(2, questionContent);
            pstmt.setString(3, clientAnswer);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
    }

    public synchronized void saveResult(int clientId, int score, int maxScore) {
        String sql = "INSERT INTO results (client_id, score, max_score) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL_DB, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clientId);
            pstmt.setInt(2, score);
            pstmt.setInt(3, maxScore);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
    }
}