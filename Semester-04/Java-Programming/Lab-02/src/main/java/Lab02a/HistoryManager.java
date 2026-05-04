package Lab02a;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
}
