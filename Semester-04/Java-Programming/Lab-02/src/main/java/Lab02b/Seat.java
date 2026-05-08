package Lab02b;

import java.io.Serializable;

public class Seat implements Serializable {
    private char row;
    private int number;

    public Seat(char row, int number) {
        this.row = row;
        this.number = number;
    }
}
