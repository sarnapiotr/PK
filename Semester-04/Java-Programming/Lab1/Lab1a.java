class NumberTooLargeException extends RuntimeException {
    public NumberTooLargeException(String message){
        super(message);
    }
}

public class Lab1a {
    public static void main(String[] args) {
        try{
            System.out.println("Sprawdzam liczbę 50");
            checkNumber(50);

            System.out.println("Sprawdzam liczbę 150");
            checkNumber(150);
        } catch (NumberTooLargeException e){
            System.err.println("Złapano błąd: " + e.getMessage());
        }
    }

    public static void checkNumber(int number){
        if(number > 100){
            throw new NumberTooLargeException("Podana liczba (" + number + ") jest za duża! Maksymalna dozwolona wartość to 100.");
        }
        System.out.println("Liczba " + number + " jest prawidłowa");
    }
}
