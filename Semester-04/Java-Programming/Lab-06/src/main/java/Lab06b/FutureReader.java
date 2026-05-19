package Lab06b;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class FutureReader extends FutureTask<String> {
    private RPN rpn = new RPN();
    private FileManager fileManager;
    private ExecutorService pool;
    private int lineNumber;

    public FutureReader(Callable<String> callable, FileManager fileManager, ExecutorService pool, int lineNumber) {
        super(callable);
        this.fileManager = fileManager;
        this.pool = pool;
        this.lineNumber = lineNumber;
    }

    @Override
    protected void done() {
        try {
            String line = get();
            if (line == null)
                return;

            FutureWriter futureWriter = new FutureWriter(new Writer(line), fileManager, lineNumber);
            pool.execute(futureWriter);

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
    }
}
