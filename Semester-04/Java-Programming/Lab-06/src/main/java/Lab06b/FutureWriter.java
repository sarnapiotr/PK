package Lab06b;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureWriter extends FutureTask<String> {
    private FileManager fileManager;
    private int lineNumber;

    public FutureWriter(Callable<String> callable, FileManager fileManager, int lineNumber) {
        super(callable);
        this.fileManager = fileManager;
        this.lineNumber = lineNumber;
    }

    @Override
    protected void done() {
        try {
            String result = get();
            fileManager.writeToFile(lineNumber, result);

        } catch (IOException | InterruptedException | ExecutionException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
    }
}
