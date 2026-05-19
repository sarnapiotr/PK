package Lab06b;

import java.util.concurrent.Callable;

public class Reader implements Callable<String> {
    private FileManager fileManager;

    public Reader(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public String call() throws Exception {
        return fileManager.readRPN();
    }
}
