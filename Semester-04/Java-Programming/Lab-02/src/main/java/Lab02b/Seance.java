package Lab02b;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class Seance implements Serializable {
    private String title;
    private LocalDate date;
    private LocalTime time;
    private int ageRestriction;
    private HashMap<Character, HashMap<Integer, Boolean>> seats = new HashMap<>();

    public Seance(String title, LocalDate date, LocalTime time, int ageRestriction) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.ageRestriction = ageRestriction;

        for (char c = 'A'; c <= 'H'; c++) {
            HashMap<Integer, Boolean> row = new HashMap<>();

            for (int i = 1; i <= 10; i++) {
                row.put(i, false);
            }

            seats.put(c, row);
        }
    }

    @Override
    public String toString() {
        String str = "";

        str += title + " | " + date + " | " + time + " | " + ageRestriction + "\n";
        str += seats.toString();

        return str;
    }
}
