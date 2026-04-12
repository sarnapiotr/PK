import java.util.Scanner;

public class Lab01c {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int attempts = 0;
        boolean success = false;

        System.out.println("Square Root Calculator (you have 3 attempts)");

        while (attempts < 3 && !success){
            System.out.println("Attempt " + (attempts + 1) + "/3.");

            try {
                System.out.println("Enter number a (the radicand): ");
                double a = sc.nextDouble();
                System.out.println("Enter number b (the degree of the root): ");
                double b = sc.nextDouble();

                root(a, b);
                success = true;
            } catch (IllegalArgumentException e) {
                System.err.println("Error caught: " + e.getMessage());
                attempts++;
            }
        }

        if (!success) {
            System.err.println("Error limit exceeded. The program is terminating");
        }

        sc.close();
    }

    public static void root(double a, double b){
        if (b == 0) {
            throw new IllegalArgumentException("The degree of the root (b) cannot be zero!");
        }
        if (a < 0) {
            throw new IllegalArgumentException("The radicand (a) cannot be negative!");
        }

        System.out.println("The " + b + "th root of " + a + " is: " + Math.pow(a, 1.0 / b));
    }
}