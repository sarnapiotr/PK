package Lab02b;

import java.io.Serializable;
import java.util.List;

public class Client implements Serializable {
    private String name, surname, email, phoneNumber;
    private Seance seance;
    private List<Seat> seatList;

    public Client(String name, String surname, String email, String phoneNumber, Seance seance, List<Seat> seatList) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.seance = seance;
        this.seatList = seatList;
    }

    @Override
    public String toString() {
        String str = "";

        str += name + " | " + surname + " | " + email + " | " + phoneNumber + "\n" + seance.toString() + "\n";
        for (Seat seat : seatList) {
            str += seat.toString() + " | ";
        }

        return str;
    }
}
