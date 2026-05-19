package Lab06b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileManager {
    private final File file = new File("RPNequations.txt");
    private final Lock lock = new ReentrantLock();
    private final Condition readyToComputeWrite = lock.newCondition();
    private final Condition readyToRead = lock.newCondition();

    public void readRPN() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = "";

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
