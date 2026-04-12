class NumberTooLargeException extends RuntimeException {
    public NumberTooLargeException(String message){
        super(message);
    }
}

public class Lab01a {
    public static void main(String[] args) {
        try {
            System.out.println("Checking number 50");
            checkNumber(50);

            System.out.println("Checking number 150");
            checkNumber(150);
        } catch (NumberTooLargeException e){
            System.err.println("Error caught: " + e.getMessage());
        }
    }

    public static void checkNumber(int number) {
        if (number > 100) {
            throw new NumberTooLargeException("Provided number (" + number + ") is too large! The maximum allowed value is 100");
        }
        System.out.println("Number " + number + " is valid");
    }
}