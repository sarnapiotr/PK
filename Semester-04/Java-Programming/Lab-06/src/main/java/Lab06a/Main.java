package Lab06a;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();

        Thread writerThread = new Thread(new Writer(fileManager));
        Thread readerThread = new Thread(new Reader(fileManager));

        writerThread.start();
        readerThread.start();
    }
}
