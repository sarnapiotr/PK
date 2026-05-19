package Lab06b;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        try {
            FileManager fileManager = new FileManager();
            ExecutorService pool = Executors.newFixedThreadPool(10);
            int lineNumber = 0;

            while (true) {
                FutureReader futureReader = new FutureReader(new Reader(fileManager), fileManager, pool, lineNumber++);
                pool.execute(futureReader);

                String line = futureReader.get();
                if (line == null)
                    break;
            }

            pool.shutdown();
        } catch (IOException | ExecutionException | InterruptedException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
    }
}

/*
To test, add
4 * 5 - (20 - 10) / 2 + 3^2 =
6 * 2 - (15 - 3) / 4 + 2^4 =
3 * 7 - (30 - 6) / 3 + 4^2 =
8 * 4 - (12 - 4) / 2 + 5^2 =
5 * 5 - (18 - 9) / 3 + 2^5 =
9 * 3 - (40 - 10) / 5 + 3^3 =
7 * 6 - (25 - 5) / 4 + 2^2 =
2 * 9 - (14 - 6) / 2 + 6^2 =
4 * 8 - (50 - 20) / 6 + 3^2 =
5 * 7 - (22 - 4) / 2 + 4^3 =
(3 + 4) * 2 - 10 / 5 + 3^2 =
(5 + 1) * 4 - 12 / 3 + 2^3 =
(8 + 2) * 3 - 20 / 4 + 4^2 =
(6 + 3) * 5 - 18 / 6 + 2^4 =
(2 + 7) * 2 - 16 / 8 + 5^2 =
(4 + 5) * 3 - 24 / 4 + 3^3 =
(7 + 3) * 6 - 30 / 5 + 2^5 =
(9 + 1) * 4 - 40 / 8 + 6^2 =
(3 + 5) * 7 - 14 / 2 + 4^3 =
(6 + 4) * 2 - 25 / 5 + 7^2 =
3^2 + (4 * 5 - 10) / 2 =
2^4 + (6 * 3 - 6) / 4 =
4^2 + (5 * 4 - 5) / 3 =
5^2 + (7 * 2 - 4) / 2 =
2^5 + (8 * 3 - 4) / 5 =
3^3 + (9 * 2 - 8) / 2 =
6^2 + (4 * 6 - 4) / 4 =
2^3 + (5 * 6 - 10) / 5 =
4^3 + (3 * 8 - 4) / 4 =
7^2 + (2 * 9 - 6) / 3 =
20 - 3 * 4 + 4^2 / (6 - 2) =
30 - 2 * 5 + 6^2 / (7 - 4) =
25 - 4 * 3 + 2^4 / (9 - 5) =
40 - 5 * 6 + 3^3 / (12 - 9) =
15 - 2 * 6 + 5^2 / (8 - 3) =
50 - 7 * 5 + 4^3 / (10 - 6) =
35 - 4 * 7 + 2^5 / (6 - 2) =
22 - 3 * 5 + 6^2 / (15 - 9) =
45 - 8 * 4 + 3^2 / (7 - 4) =
60 - 9 * 3 + 4^2 / (10 - 8) =
(2 * 3^2) - (15 + 5) / 4 =
(4 * 2^3) - (20 + 10) / 6 =
(3 * 4^2) - (18 + 6) / 3 =
(5 * 2^2) - (12 + 8) / 5 =
(2 * 5^2) - (30 + 10) / 8 =
(6 * 3^2) - (14 + 7) / 3 =
(4 * 3^3) - (25 + 15) / 5 =
(7 * 2^4) - (16 + 8) / 4 =
(3 * 5^2) - (22 + 18) / 10 =
(8 * 2^2) - (19 + 5) / 6 =
At the end of RPNEquations.txt file
*/