import java.util.Scanner;

public class Lab1b {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int attempts = 0;
        boolean success = false;

        System.out.println("Kalkulator pierwiastków (masz 3 próby)");

        while(attempts < 3 && !success) {
            System.out.println("Próba " + (attempts + 1) + "/3. Podaj liczbę, aby obliczyć jej pierwiastek: ");

            try{
                double inputNumber = sc.nextDouble();

                sqrt(inputNumber);
                success = true;
            } catch (IllegalArgumentException e){
                System.err.println("Złapano błąd: " + e.getMessage());
                attempts++;
            }
        }

        if (!success) {
            System.err.println("Przekroczono limit błędów. Program kończy działanie.");
        }

        sc.close();
    }

    public static void sqrt(double number){
        if(number < 0){
            throw new IllegalArgumentException("Liczba nie może być liczbą ujemną! Podano: " + number);
        }
        System.out.println("Pierwiastek wynosi: " + Math.sqrt(number));
    }
}
