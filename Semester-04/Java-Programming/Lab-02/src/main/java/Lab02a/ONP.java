package Lab02a;

import java.util.Scanner;

public class ONP {
    private TabStack stack = new TabStack();

    static void checkEmptyEquation(String equation){
        if(equation.isEmpty()){
            throw new RuntimeException("Equation is empty!");
        }
    }

    static void checkValidEquation(String equation){
        if (!equation.endsWith("=")){
            throw new RuntimeException("Missing '=' at the end!");
        }
    }

    static void checkValidParentheses(String equation) {
        int counter = 0;
        for (int i = 0; i < equation.length(); i++){
            if(equation.charAt(i) == '(') counter++;
            else if (equation.charAt(i) == ')') counter--;

            if(counter < 0){
                throw new RuntimeException("Too many closing parentheses!");
            }
        }

        if(counter != 0){
            throw new RuntimeException("Too many opening parentheses!");
        }
    }

    private double factorial(double n){
        if(n < 0) throw new RuntimeException("Factorial of a negative number!");
        double result = 1;
        for(int i = 1; i <= n; i++) result *= i;
        return result;
    }

    public String calculateOnp(String equation) {
        stack.setSize(0);
        String result = "";

        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) >= '0' && equation.charAt(i) <= '9') {
                result += equation.charAt(i);
                if (!(equation.charAt(i + 1) >= '0' && equation.charAt(i + 1) <= '9')) {
                    stack.push(result);
                    result = "";
                }
            } else if (equation.charAt(i) == '=') {
                return stack.pop();
            } else if (equation.charAt(i) != ' ') {
                if(equation.charAt(i) == '!' || equation.charAt(i) == 'p'){
                    double a = Double.parseDouble(stack.pop());

                    if (equation.charAt(i) == '!'){
                        stack.push(factorial(a) + "");
                    } else if (equation.charAt(i) == 'p'){
                        if (a < 0) throw new RuntimeException("Square root of a negative number!");
                        stack.push(Math.sqrt(a) + "");
                    }
                } else{
                    double b = Double.parseDouble(stack.pop());
                    double a = Double.parseDouble(stack.pop());
                    switch (equation.charAt(i)) {
                        case ('+'): {
                            stack.push((a + b) + "");
                            break;
                        }
                        case ('-'): {
                            stack.push((a - b) + "");
                            break;
                        }
                        case ('x'):
                            ;
                        case ('*'): {
                            stack.push((a * b) + "");
                            break;
                        }
                        case ('/'): {
                            if(b == 0) throw new RuntimeException("Division by zero!");
                            stack.push((a / b) + "");
                            break;
                        }
                        case ('^'): {
                            stack.push(Math.pow(a, b) + "");
                            break;
                        }
                        case ('%'): {
                            stack.push((a % b) + "");
                            break;
                        }
                    }
                }
            }
        }

        return "0.0";
    }

    public String convertToOnp(String equation) {
        String result = "";
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) >= '0' && equation.charAt(i) <= '9') {
                result += equation.charAt(i);
                if (!(equation.charAt(i + 1) >= '0' && equation.charAt(i + 1) <= '9'))
                    result += " ";
            } else
                switch (equation.charAt(i)) {
                    case ('+'):
                    case ('-'): {
                        while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")) {
                            result = result + stack.pop() + " ";
                        }
                        String str = "" + equation.charAt(i);
                        stack.push(str);
                        break;
                    }
                    case ('x'):
                    case ('*'):
                    case ('/'): {
                        while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")
                                && !stack.showValue(stack.getSize() - 1).equals("+")
                                && !stack.showValue(stack.getSize() - 1).equals("-")) {
                            result = result + stack.pop() + " ";
                        }
                        String str = "" + equation.charAt(i);
                        stack.push(str);
                        break;
                    }
                    case ('^'): {
                        while (stack.getSize() > 0 && stack.showValue(stack.getSize() - 1).equals("^")) {
                            result = result + stack.pop() + " ";
                        }
                        String str = "" + equation.charAt(i);
                        stack.push(str);
                        break;
                    }
                    case ('('): {
                        String str = "" + equation.charAt(i);
                        stack.push(str);
                        break;
                    }
                    case (')'): {
                        while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")) {
                            result = result + stack.pop() + " ";
                        }
                        stack.pop();
                        break;
                    }
                    case ('%'): {
                        while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")
                                && !stack.showValue(stack.getSize() - 1).equals("+")
                                && !stack.showValue(stack.getSize() - 1).equals("-")) {
                            result = result + stack.pop() + " ";
                        }
                        stack.push("" + equation.charAt(i));
                        break;
                    }
                    case ('!'): {
                        result += "! ";
                        break;
                    }
                    case ('p'): {
                        stack.push("" + equation.charAt(i));
                        break;
                    }
                    case ('='): {
                        while (stack.getSize() > 0) {
                            result = result + stack.pop() + " ";
                        }
                        result += "=";
                    }
                }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter an equation ending with '=': ");
        String input = sc.nextLine();

        try{
            checkEmptyEquation(input);
            checkValidEquation(input);
            checkValidParentheses(input);

            ONP onp = new ONP();
            System.out.print(input + " ");
            String onpEquation = onp.convertToOnp(input);
            System.out.print(onpEquation);
            String result = onp.calculateOnp(onpEquation);
            System.out.println(" " + result);

        } catch (RuntimeException e){
            System.err.println("Error caught: " + e.getMessage());
        }
    }
}