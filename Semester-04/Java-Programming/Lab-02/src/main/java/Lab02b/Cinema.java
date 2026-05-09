package Lab02b;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
                        String seanceName, seanceDate, seanceTime, seanceAgeRestriction;
                        System.out.println("Enter Seance name: ");
                        seanceName = sc.nextLine();
                        System.out.println("Enter Seance date: ");
                        seanceDate = sc.nextLine();
                        System.out.println("Enter Seance time: ");
                        seanceTime = sc.nextLine();
                        System.out.println("Enter Seance age restriction: ");
                        seanceAgeRestriction = sc.nextLine();

                        cinemaManager.addSeance(new Seance(seanceName, LocalDate.parse(seanceDate), LocalTime.parse(seanceTime), Integer.parseInt(seanceAgeRestriction)));
                        break;

                    case (2):
                        String clientName, clientSurname, clientEmail, clientPhoneNumber;
                        System.out.println("Enter Client name: ");
                        clientName = sc.nextLine();
                        System.out.println("Enter Client surname: ");
                        clientSurname = sc.nextLine();
                        System.out.println("Enter Client email: ");
                        clientEmail = sc.nextLine();
                        System.out.println("Enter Client phone number: ");
                        clientPhoneNumber = sc.nextLine();

                        Seance clientSeance = cinemaManager.chooseSeance(sc);

                        List<Seat> seatList = new ArrayList<>();
                        int seatChoice = 0;
                        while (seatChoice != 2) {
                            System.out.println("Choose operation:\n1. Choose seat\n2. Exit");
                            seatChoice = Integer.parseInt(sc.nextLine());

                            switch (seatChoice) {
                                case (1):
                                    char row;
                                    int number;

                                    System.out.println("Enter row from A-H: ");
                                    row = sc.nextLine().toUpperCase().charAt(0);
                                    System.out.printf("Enter seat number in row " + row + ": ");
                                    number = Integer.parseInt(sc.nextLine());

                                    seatList.add(new Seat(row, number));
                                    clientSeance.reserveSeat(row, number);
                                    break;

                                case (2):
                                    break;

                                default:
                                    System.out.println("Invalid choice number: " + seatChoice);
                            }
                        }

                        cinemaManager.addClient(new Client(clientName, clientSurname, clientEmail, clientPhoneNumber, clientSeance, seatList));
                        break;

                    case (3):
                        System.out.println(cinemaManager);
                        break;

                    case (4):
                        cinemaManager.saveBinary(".\\seanceClientdata.dat");
                        break;

                    case (5):
                        cinemaManager.loadBinary(".\\seanceClientdata.dat");
                        break;

                    case (6):
                        break;

                    default:
                        System.out.println("Invalid choice number: " + choice);
                }
            } catch (RuntimeException | IOException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}