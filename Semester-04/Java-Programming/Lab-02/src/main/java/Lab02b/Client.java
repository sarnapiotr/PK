package Lab02b;

import java.io.Serializable;
import java.util.List;

public class Client implements Serializable {
    private String name, surname, email, phoneNumber;
    private Seance seance;
    private List<Seat> seatList;
}
