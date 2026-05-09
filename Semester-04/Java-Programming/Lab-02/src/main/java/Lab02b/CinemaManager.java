package Lab02b;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaManager {
    private List<Seance> seanceList = new ArrayList<>();
    private List<Client> clientList = new ArrayList<>();

    public void addSeance(Seance seance) {
        seanceList.add(seance);
    }
    public void addClient(Client client) {
        clientList.add(client);
    }

    public Seance chooseSeance(Scanner sc) {
        System.out.println("Choose seance: ");
        int i = 1;
        for (Seance seance : seanceList) {
            System.out.println(i++ + ". " + seance.toString());
        }

        int choice = 0;
        choice = Integer.parseInt(sc.nextLine());
        if (choice < 1 || choice > seanceList.size())
            throw new IllegalArgumentException("Invalid seance choice!");

        return seanceList.get(choice - 1);
    }

    public void saveBinary(String filename) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(seanceList);
        out.writeObject(clientList);
        out.close();
    }

    public void loadBinary(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        seanceList = (List<Seance>) in.readObject();
        clientList = (List<Client>) in.readObject();

        in.close();
    }

    @Override
    public String toString() {
        String str = "";

        str += "--- SEANCE LIST ---\n";
        for (Seance seance : seanceList) {
            str += seance.toString() + "\n";
        }

        str += "--- CLIENT LIST ---\n";
        for (Client client : clientList) {
            str += client.toString() + "\n";
        }

        return str;
    }
}
