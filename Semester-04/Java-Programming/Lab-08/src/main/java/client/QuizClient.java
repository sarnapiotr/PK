package client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class QuizClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    private static final int TIMEOUT_MS = 1000;

    public static void main(String[] args) {
        System.out.println("Connecting to the UDP server");

        try (DatagramSocket socket = new DatagramSocket();
             Scanner sc = new Scanner(System.in)) {

            InetAddress serverIP = InetAddress.getByName(SERVER_ADDRESS);

            send(socket, "HELLO", serverIP, SERVER_PORT);
            System.out.println("Successfully pinged server. Waiting for test to start");

            String serverMessage;

            socket.setSoTimeout(0);

            while (true) {
                serverMessage = receive(socket);

                if (serverMessage.equals("---WAITING_FOR_ANSWER---")) {
                    String myAnswer = sc.nextLine();

                    sendWithConfirmation(socket, myAnswer, serverIP, SERVER_PORT);

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

    private static void send(DatagramSocket socket, String msg, InetAddress address, int port) throws IOException {
        byte[] buffer = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
    }

    private static String receive(DatagramSocket socket) throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return new String(packet.getData(), 0, packet.getLength()).trim();
    }

    private static void sendWithConfirmation(DatagramSocket socket, String msg, InetAddress address, int port) throws IOException {
        boolean ackReceived = false;

        while (!ackReceived) {
            send(socket, msg, address, port);

            try {
                socket.setSoTimeout(TIMEOUT_MS);
                String response = receive(socket);

                if (response.equals("ACK")) {
                    ackReceived = true;
                }
            } catch (SocketTimeoutException e) {
                System.out.println("[Retransmision] Confirmation not recieved. Sending answer again");
            }
        }

        socket.setSoTimeout(0);
    }
}