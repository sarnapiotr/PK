package Lab1d;

import java.util.Scanner;

public class ONP {
    private TabStack stack = new TabStack();

    void czyPoprawneRownanie(String rownanie){
        if (!rownanie.endsWith("=")){
            throw new RuntimeException("Brak '='!");
        }
    }

    static void czyPusteRownanie(String tmp){
        if(tmp.isEmpty()){
            throw new RuntimeException("Równanie jest puste!");
        }
    }

    public String obliczOnp(String rownanie) {
        try {
            czyPoprawneRownanie(rownanie);

            stack.setSize(0);
            String wynik = "";
            Double a = 0.0;
            Double b = 0.0;
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
                    b = Double.parseDouble(stack.pop());
                    a = Double.parseDouble(stack.pop());
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
                            stack.push((a / b) + "");
                            break;
                        }
                        case ('^'): {
                            stack.push(Math.pow(a, b) + "");
                            break;
                        }
                    }
                }
            }
        } catch (RuntimeException e){
            System.err.println("Złapano błąd: " + e.getMessage());
        }

        return "0.0";
    }

    public String przeksztalcNaOnp(String rownanie) {
        try {
            czyPoprawneRownanie(rownanie);

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
                            // zdjęcie ze stosu znaku (
                            stack.pop();
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
        } catch (RuntimeException e) {
            System.err.println("Złapano błąd: " + e.getMessage());
        }

        return "null";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj równanie zakończone znakiem '=': ");
        String tmp = sc.nextLine();

        try{
            czyPusteRownanie(tmp);
        } catch (RuntimeException e){
            System.err.println("Złapano bład: " + e.getMessage());
            tmp = "(2+3)*(6-2)^2=";
        }

        
        ONP onp = new ONP();
        System.out.print(tmp + " ");
        String rownanieOnp = onp.przeksztalcNaOnp(tmp);
        System.out.print(rownanieOnp);
        String wynik = onp.obliczOnp(rownanieOnp);
        System.out.println(" " + wynik);
    }
}