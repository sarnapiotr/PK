package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class QuizClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        System.out.println("Connecting to the server");

        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                Scanner sc = new Scanner(System.in)
        ) {
            System.out.println("Succesfully connected");

            String serverMessage;

            while ((serverMessage = in.readLine()) != null) {
                if (serverMessage.equals("---WAITING_FOR_ANSWER---")) {
                    String myAnswer = sc.nextLine();
                    out.println(myAnswer);

                } else if (serverMessage.equals("---TEST_ENDED---")) {
                    break;

                } else {
                    System.out.println(serverMessage);
                }
            }

            System.out.println("Test ended. Terminated connection");

        } catch (IOException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
    }
}