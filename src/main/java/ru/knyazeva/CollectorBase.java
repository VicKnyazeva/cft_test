package ru.knyazeva;

import java.io.*;

public abstract class CollectorBase implements Closeable {
    private final String fullOutputFilePath;
    private final boolean appendToFile;
    private PrintWriter writer;
    private int count = 0;

    public int getCount() {
        return count;
    }

    protected CollectorBase(String fullOutputFilePath, boolean appendToFile) {
        this.fullOutputFilePath = fullOutputFilePath;
        this.appendToFile = appendToFile;
    }

    public boolean checkAndCollect(String input) throws IOException {
        if (input != null && !input.isEmpty() && checkInput(input)) {
            count++;
            addToFile(input);
            return true;
        }
        return false;
    }

    protected abstract boolean checkInput(String input);

    public abstract void showStatistics(StatisticsMode options);

    private void addToFile(String input) throws IOException {
        if (writer == null) {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(fullOutputFilePath, appendToFile)), true);
        }
        writer.println(input);
    }

    @Override
    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}
