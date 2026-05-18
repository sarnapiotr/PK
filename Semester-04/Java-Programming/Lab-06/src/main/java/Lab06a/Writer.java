package Lab06a;

import java.util.Scanner;

public class Writer implements Runnable {
    private final FileManager fileManager;

    public Writer(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text, write 'End' to stop: ");

        while (true) {
            String input = scanner.nextLine();
            fileManager.writeToFile(input);

            if (input.equalsIgnoreCase("End")) {
                break;
            }
        }
        scanner.close();
    }
}
