import java.util.Scanner;

public class Lab01b {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int attempts = 0;
        boolean success = false;

        System.out.println("Square Root Calculator (you have 3 attempts)");

        while (attempts < 3 && !success) {
            System.out.println("Attempt " + (attempts + 1) + "/3. Enter a number to calculate its square root: ");

            try {
                double inputNumber = sc.nextDouble();

                sqrt(inputNumber);
                success = true;
            } catch (IllegalArgumentException e){
                System.err.println("Error caught: " + e.getMessage());
                attempts++;
            }
        }

        if (!success) {
            System.err.println("Error limit exceeded. The program is terminating");
        }

        sc.close();
    }

    public static void sqrt(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number cannot be negative! Provided: " + number);
        }
        System.out.println("The square root is: " + Math.sqrt(number));
    }
}