package Lab06b;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        try {
            FileManager fileManager = new FileManager();
            ExecutorService pool = Executors.newFixedThreadPool(1);

            while (true) {
                FutureReader futureReader = new FutureReader(new Reader(fileManager));
                pool.execute(futureReader);

                String line = futureReader.get();
                if (line == null)
                    break;

                System.out.println(line);
            }

            pool.shutdown();
        } catch (IOException | ExecutionException | InterruptedException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
    }
}
