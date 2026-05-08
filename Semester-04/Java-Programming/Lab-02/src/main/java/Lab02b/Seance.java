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
    private HashMap<Character, HashMap<Integer, Boolean>> seats;
}
