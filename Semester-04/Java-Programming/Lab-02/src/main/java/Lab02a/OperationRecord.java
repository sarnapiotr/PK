package Lab02a;

import java.io.Serializable;

public class OperationRecord implements Serializable {
    private String originalEquation, rpnEquation, result;

    public OperationRecord(String originalEquation, String rpnEquation, String result) {
        this.originalEquation = originalEquation;
        this.rpnEquation = rpnEquation;
        this.result = result;
    }

    @Override
    public String toString() {
        return originalEquation + " | " + rpnEquation + " | " + result;
    }
}
