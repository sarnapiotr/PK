package Lab06b;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureReader extends FutureTask<String> {
    public FutureReader(Callable<String> callable) {
        super(callable);
    }
}
