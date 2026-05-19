package Lab06b;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();

        fileManager.readRPN();

        /*Scanner sc = new Scanner(System.in);

        System.out.println("Enter an equation ending with '=': ");
        String input = sc.nextLine();

        try {
            Lab06b.RPN.checkEmptyEquation(input);
            Lab06b.RPN.checkValidEquation(input);
            Lab06b.RPN.checkValidParentheses(input);

            RPN rpn = new RPN();
            System.out.print(input + " ");
            String rpnEquation = rpn.convertToRpn(input);
            System.out.print(rpnEquation);
            String result = rpn.calculateRpn(rpnEquation);
            System.out.println(" " + result);

        } catch (RuntimeException e){
            System.err.println("Error caught: " + e.getMessage());
        }*/
    }
}
