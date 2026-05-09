package Lab02b;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CinemaManager cinemaManager = new CinemaManager();
        int choice = 0;

        while (choice != 6) {
            try {
                System.out.println("Choose operation:\n1. Add new Seance\n2. Add new Client\n3. Show Seance and Client data (from RAM)\n" +
                        "4. Save Seance and Client data to binary file\n5. Load Seance and Client data from binary file\n6. Exit");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case (1):
                        Seance houseMd = new Seance("House MD", LocalDate.parse("2026-05-09"), LocalTime.parse("13:24"), 12);
                        cinemaManager.addSeance(houseMd);
                        Seance GOT = new Seance("Game of Thrones", LocalDate.parse("2026-05-09"), LocalTime.parse("13:52"), 16);
                        cinemaManager.addSeance(GOT);
                        Seance BB = new Seance("Breaking Bad", LocalDate.parse("2026-05-09"), LocalTime.parse("13:52"), 16);
                        cinemaManager.addSeance(BB);
                        break;

                    case (2):
                        int seanceChoice = 0;
                        cinemaManager.printSeanceList();
                        seanceChoice = Integer.parseInt(sc.nextLine());
                        Seance clientSeance = cinemaManager.getSeance(seanceChoice);

                        System.out.println(clientSeance);

                        break;

                    case (3):
                        System.out.println(cinemaManager);
                    case (6):
                        break;

                    default:
                        System.out.println("Invalid choice number: " + choice);
                }
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}