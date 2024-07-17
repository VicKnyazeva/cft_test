package ru.knyazeva;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    static final String INTEGERS_FILE_NAME = "integers.txt";
    static final String FLOATS_FILE_NAME = "floats.txt";
    static final String STRINGS_FILE_NAME = "strings.txt";

    public static void main(String[] args) {
        ProcessingOptions options = new ProcessingOptions();

        if (!options.parseArgs(args)) {
            return;
        }

        try (
                CollectorBase intNumberCollector = new IntNumberCollector(options.makeOutFilePath(INTEGERS_FILE_NAME), options.appendToFile);
                CollectorBase realNumberCollector = new RealNumberCollector(options.makeOutFilePath(FLOATS_FILE_NAME), options.appendToFile);
                CollectorBase stringCollector = new StringCollector(options.makeOutFilePath(STRINGS_FILE_NAME), options.appendToFile)
        ) {
            for (String filePath : options.inputFiles) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
                    while (reader.ready()) {
                        String input = reader.readLine();
                        if (intNumberCollector.checkAndCollect(input)) {
                            continue;
                        }
                        if (realNumberCollector.checkAndCollect(input)) {
                            continue;
                        }
                        stringCollector.checkAndCollect(input);
                    }
                } catch (Throwable exception) {
                    System.out.println(exception.getMessage());
                }
            }
            intNumberCollector.showStatistics(options.stats);
            realNumberCollector.showStatistics(options.stats);
            stringCollector.showStatistics(options.stats);
        } catch (Throwable exception) {
            System.out.println(exception.getMessage());
        }
    }
}