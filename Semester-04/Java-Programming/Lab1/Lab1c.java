import java.util.Scanner;

public class Lab1c {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int attempts = 0;
        boolean success = false;

        System.out.println("Kalkulator pierwiastków (masz 3 próby)");

        while (attempts < 3 && !success){
            System.out.println("Próba " + (attempts + 1) + "/3.");

            try{
                System.out.println("Podaj liczbę a (podpierwiastkowa): ");
                double a = sc.nextDouble();
                System.out.println("Podaj liczbę b (stopień pierwiastka): ");
                double b = sc.nextDouble();

                root(a, b);
                success = true;
            } catch(IllegalArgumentException e) {
                System.err.println("Złapano błąd: " + e.getMessage());
                attempts++;
            }
        }

        if (!success) {
            System.err.println("Przekroczono limit błędów. Program kończy działanie.");
        }

        sc.close();
    }

    public static void root(double a, double b){
        if (b == 0) {
            throw new IllegalArgumentException("Stopień pierwiastka (b) nie może wynosić zero!");
        }
        if (a < 0) {
            throw new IllegalArgumentException("Liczba podpierwiastkowa (a) nie może być ujemna!");
        }

        System.out.println("Pierwiastek z liczby " + a + " stopnia " + b + " wynosi: " + Math.pow(a, 1.0 / b));
    }
}
