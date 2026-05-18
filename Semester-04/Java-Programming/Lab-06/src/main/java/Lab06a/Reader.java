package Lab06a;

public class Reader implements Runnable {
    private FileManager filemManager;

    public Reader(FileManager fileManager) {
        this.filemManager = fileManager;
    }

    @Override
    public void run() {
        while (true) {
            String text = filemManager.readFromFile();

            if (text.equalsIgnoreCase("End")) {
                break;
            }
        }
    }
}
