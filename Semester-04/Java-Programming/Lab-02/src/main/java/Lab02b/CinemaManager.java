package Lab02b;

import java.util.ArrayList;
import java.util.List;

public class CinemaManager {
    private List<Seance> seanceList = new ArrayList<>();
    private List<Client> clientList = new ArrayList<>();

    public void addSeance(Seance seance) {
        seanceList.add(seance);
    }
    public void addClient(Client client) {
        clientList.add(client);
    }

    public void printSeanceList() {
        System.out.println("Choose seance: ");
        int i = 1;

        for (Seance seance : seanceList) {
            System.out.println(i++ + ". " + seance.toString());
        }
    }

    public Seance getSeance(int choice) {
        if (choice < 1 || choice > seanceList.size()-1)
            throw new IllegalArgumentException("Invalid seance choice!");

        return seanceList.get(choice - 1);
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
