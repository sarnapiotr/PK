package Lab06b;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileManager fileManager = new FileManager();
            RPN rpn = new RPN();

            String line = "";
            while ((line = fileManager.readRPN()) != null) {
                System.out.println(line);

                Lab06b.RPN.checkEmptyEquation(line);
                Lab06b.RPN.checkValidEquation(line);
                Lab06b.RPN.checkValidParentheses(line);

                String rpnEquation = rpn.convertToRpn(line);
                String result = rpn.calculateRpn(rpnEquation);
                System.out.println(result);
            }

        } catch (IOException | RuntimeException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
    }
}
