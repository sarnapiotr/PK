package Lab06b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileManager {
    private final Path file = Paths.get("RPNEquations.txt");
    private final Lock lock = new ReentrantLock();
    private List<String> lines;
    private int currentReadIndex = 0;

    public FileManager() throws IOException {
        lines = Files.readAllLines(file);
    }

    public String readFromFile() {
        lock.lock();
        try {
            if (currentReadIndex < lines.size()) {
                return lines.get(currentReadIndex++);
            }
            return null;

        } finally {
            lock.unlock();
        }
    }

    public void writeToFile(int lineNumber, String result) throws IOException {
        lock.lock();
        try {
            if (lineNumber < lines.size()) {
                String currentLine = lines.get(lineNumber);
                lines.set(lineNumber, currentLine + " " + result);
                Files.write(file, lines);
            }
        } finally {
            lock.unlock();
        }
    }
}