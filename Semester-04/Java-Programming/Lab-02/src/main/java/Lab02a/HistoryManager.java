package Lab02a;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

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

        in.close();
    }

    public void saveXML(String filename) throws IOException {
        FileWriter file = new FileWriter(filename);
        BufferedWriter out = new BufferedWriter(file);
        XStream mapping = new XStream(new DomDriver());
        String xml = mapping.toXML(history);
        out.write(xml);
        out.close();
    }

    public void loadXML(String filename) throws IOException {
        String xml = "", strLine = "";

        FileReader file = new FileReader(filename);
        BufferedReader in = new BufferedReader(file);

        while ((strLine = in.readLine()) != null)
            xml += strLine;

        in.close();
        XStream mapping = new XStream(new DomDriver());
        mapping.addPermission(AnyTypePermission.ANY);

        history = (List<OperationRecord>) mapping.fromXML(xml);
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