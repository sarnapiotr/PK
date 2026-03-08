package Lab1d;

import java.util.Scanner;

public class ONP {
    private TabStack stack = new TabStack();

    static void czyPusteRownanie(String tmp){
        if(tmp.isEmpty()){
            throw new RuntimeException("Równanie jest puste!");
        }
    }

    static void czyPoprawneRownanie(String rownanie){
        if (!rownanie.endsWith("=")){
            throw new RuntimeException("Brak '='!");
        }
    }

    static void czyPoprawneNawiasy(String rownanie) {
        int licznik = 0;
        for (int i = 0; i < rownanie.length(); i++){
            if(rownanie.charAt(i) == '(') licznik++;
            else if (rownanie.charAt(i) == ')') licznik--;

            if(licznik < 0){
                throw new RuntimeException("Za dużo nawiasów zamykających!");
            }
        }

        if(licznik != 0){
            throw new RuntimeException("Za dużo nawiasów otwierających!");
        }
    }

    private double silnia(double n){
        if(n < 0) throw new RuntimeException("Silnia z liczby ujemnej!");
        double wynik = 1;
        for(int i = 1; i <= n; i++) wynik *= i;
        return wynik;
    }

    public String obliczOnp(String rownanie) {
        stack.setSize(0);
        String wynik = "";

        for (int i = 0; i < rownanie.length(); i++) {
            if (rownanie.charAt(i) >= '0' && rownanie.charAt(i) <= '9') {
                wynik += rownanie.charAt(i);
                if (!(rownanie.charAt(i + 1) >= '0' && rownanie.charAt(i + 1) <= '9')) {
                    stack.push(wynik);
                    wynik = "";
                }
            } else if (rownanie.charAt(i) == '=') {
                return stack.pop();
            } else if (rownanie.charAt(i) != ' ') {
                if(rownanie.charAt(i) == '!' || rownanie.charAt(i) == 'p'){
                    double a = Double.parseDouble(stack.pop());

                    if (rownanie.charAt(i) == '!'){
                        stack.push(silnia(a) + "");
                    } else if (rownanie.charAt(i) == 'p'){
                        if (a < 0) throw new RuntimeException("Pierwiastek z liczby ujemnej!");
                        stack.push(Math.sqrt(a) + "");
                    }
                } else{
                    double b = Double.parseDouble(stack.pop());
                    double a = Double.parseDouble(stack.pop());
                    switch (rownanie.charAt(i)) {
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
                            if(b == 0) throw new RuntimeException("Dzielenie przez zero!");
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

    public String przeksztalcNaOnp(String rownanie) {
        String wynik = "";
        for (int i = 0; i < rownanie.length(); i++) {
            if (rownanie.charAt(i) >= '0' && rownanie.charAt(i) <= '9') {
                wynik += rownanie.charAt(i);
                if (!(rownanie.charAt(i + 1) >= '0' && rownanie.charAt(i + 1) <= '9'))
                    wynik += " ";
            } else
                switch (rownanie.charAt(i)) {
                    case ('+'):
                        ;
                    case ('-'): {
                        while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")) {
                            wynik = wynik + stack.pop() + " ";
                        }
                        String str = "" + rownanie.charAt(i);
                        stack.push(str);
                        break;
                    }
                    case ('x'):
                        ;
                    case ('*'):
                        ;
                    case ('/'): {
                        while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")
                                && !stack.showValue(stack.getSize() - 1).equals("+")
                                && !stack.showValue(stack.getSize() - 1).equals("-")) {
                            wynik = wynik + stack.pop() + " ";
                        }
                        String str = "" + rownanie.charAt(i);
                        stack.push(str);
                        break;
                    }
                    case ('^'): {
                        while (stack.getSize() > 0 && stack.showValue(stack.getSize() - 1).equals("^")) {
                            wynik = wynik + stack.pop() + " ";
                        }
                        String str = "" + rownanie.charAt(i);
                        stack.push(str);
                        break;
                    }
                    case ('('): {
                        String str = "" + rownanie.charAt(i);
                        stack.push(str);
                        break;
                    }
                    case (')'): {
                        while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")) {
                            wynik = wynik + stack.pop() + " ";
                        }
                        stack.pop();
                        break;
                    }
                    case ('%'): {
                        while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")
                                && !stack.showValue(stack.getSize() - 1).equals("+")
                                && !stack.showValue(stack.getSize() - 1).equals("-")) {
                            wynik = wynik + stack.pop() + " ";
                        }
                        stack.push("" + rownanie.charAt(i));
                        break;
                    }
                    case ('!'): {
                        wynik += "! ";
                        break;
                    }
                    case ('p'): {
                        stack.push("" + rownanie.charAt(i));
                        break;
                    }
                    case ('='): {
                        while (stack.getSize() > 0) {
                            wynik = wynik + stack.pop() + " ";
                        }
                        wynik += "=";
                    }
                }
        }

        return wynik;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj równanie zakończone znakiem '=': ");
        String tmp = sc.nextLine();

        try{
            czyPusteRownanie(tmp);
            czyPoprawneRownanie(tmp);
            czyPoprawneNawiasy(tmp);

            ONP onp = new ONP();
            System.out.print(tmp + " ");
            String rownanieOnp = onp.przeksztalcNaOnp(tmp);
            System.out.print(rownanieOnp);
            String wynik = onp.obliczOnp(rownanieOnp);
            System.out.println(" " + wynik);

        } catch (RuntimeException e){
            System.err.println("Złapano bład: " + e.getMessage());
        }
    }
}
