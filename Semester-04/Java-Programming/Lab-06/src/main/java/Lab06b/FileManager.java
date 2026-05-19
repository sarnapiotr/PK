package Lab06b;

import java.io.*;

public class FileManager {
    private final File file = new File("RPNEquations.txt");
    private BufferedReader reader;

    public FileManager() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
    }

    public String readRPN() throws IOException {
        return reader.readLine();
    }
}
