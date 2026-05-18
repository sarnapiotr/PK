package Lab06a;

import java.io.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileManager {
    private final File file = new File("sharedFile.txt");
    private final Lock lock = new ReentrantLock();
    private final Condition readyToWrite = lock.newCondition();
    private final Condition readyToRead = lock.newCondition();

    private boolean hasNewData = false;

    public void writeToFile(String text) {
        lock.lock();
        try {
            while (hasNewData) {
                readyToWrite.await();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(text);
                writer.newLine();

                System.out.println("[Writer] Saved to file: " + text);
                hasNewData = true;
                readyToRead.signal();
            }
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public String readFromFile() {
        lock.lock();
        String lastLine = "";
        try {
            while (!hasNewData) {
                readyToRead.await();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lastLine = line;
                }
            }

            System.out.println("[Reader] Read from file: " + lastLine);
            hasNewData = false;
            readyToWrite.signal();

        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }

        return lastLine;
    }
}
