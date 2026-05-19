package Lab06b;

import java.io.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileManager {
    private final File file = new File("RPNEquations.txt");
    private final Lock lock = new ReentrantLock();
    private final Condition readyToWrite = lock.newCondition();
    private final Condition readyToRead = lock.newCondition();
    private BufferedReader reader;

    public FileManager() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
    }

    public String readRPN() throws IOException {
        return reader.readLine();
    }
}
