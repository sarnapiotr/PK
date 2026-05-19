package Lab06b;

import java.util.concurrent.Callable;

public class Writer implements Callable<String> {
    private RPN rpn = new RPN();
    private String line;

    public Writer(String line) {
        this.line = line;
    }

    @Override
    public String call() throws Exception {
        RPN.checkEmptyEquation(line);
        RPN.checkValidEquation(line);
        RPN.checkValidParentheses(line);

        String rpnEquation = rpn.convertToRpn(line);
        String result = rpn.calculateRpn(rpnEquation);

        return result;
    }
}