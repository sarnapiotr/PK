package Lab03b;

public class Main {
    public static void main(String args[]) {
        Set<IntegerClass> firstIntegerSet = new Set<>(10);
        Set<IntegerClass> secondIntegerSet = new Set<>(10);

        for (int i = 0; i < 10; i++) {
            IntegerClass firstIntegerClass = new IntegerClass(i);
            IntegerClass secondIntegerClass = new IntegerClass(i * 2);

            firstIntegerSet.addElement(firstIntegerClass);
            secondIntegerSet.addElement(secondIntegerClass);
        }

        System.out.println(firstIntegerSet);
        System.out.println(secondIntegerSet);

        Set<IntegerClass> thirdIntegerSet = Set.addElements(firstIntegerSet, secondIntegerSet);
        System.out.println(thirdIntegerSet);
    }
}
