package Lab02a;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private List<OperationRecord> history = new ArrayList<>();

    public void addOperation(OperationRecord operation) {
        history.add(operation);
    }

    public void saveBinary(String filename) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(history);
        out.close();
    }

    public void loadBinary(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        history = (List<OperationRecord>) in.readObject();

        for (OperationRecord operation : history) {
            System.out.println(operation);
        }

        in.close();
    }

    @Override
    public String toString() {
        String str = "";

        for (OperationRecord operation : history) {
            str += operation.toString() + "\n";
        }

        return str;
    }
}