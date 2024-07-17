package ru.knyazeva;

import java.io.File;
import java.util.ArrayList;

import static java.lang.System.getProperty;

public class ProcessingOptions {
    String outputPath = "";
    String outputPrefix = "";
    boolean appendToFile = false;
    StatisticsMode stats = StatisticsMode.NONE;
    final ArrayList<String> inputFiles = new ArrayList<>();

    public ProcessingOptions() {
    }

    public boolean parseArgs(String[] args) {
        outputPath = "";
        outputPrefix = "";
        stats = StatisticsMode.NONE;
        for (int i = 0; i < args.length; i++) {
            String a = args[i];
            if (a.equals("-a")) {
                appendToFile = true;
                continue;
            }
            if (a.equals("-s")) {
                stats = StatisticsMode.SHORT;
                continue;
            }
            if (a.equals("-f")) {
                stats = StatisticsMode.FULL;
                continue;
            }
            if (a.equals("-o")) {
                i++;
                if (i < args.length) {
                    outputPath = args[i];
                    File file = new File(outputPath);
                    if (!file.isDirectory()) {
                        System.out.println("Переданный путь не является директорией.\nБудет использоваться директория по умолчанию.\n");
                        outputPath = "";
                    }
                } else {
                    System.out.println("Не задан путь для выходных файлов.\nБудет использоваться директория по умолчанию.\n");
                    outputPath = "";
                }
                continue;
            }
            if (a.equals("-p")) {
                i++;
                if (i < args.length) {
                    outputPrefix = args[i];
                } else {
                    System.out.println("Не задан префикс для имён выходных файлов.");
                    outputPrefix = "";
                }
                continue;
            }
            inputFiles.add(a);
        }
        if (inputFiles.size() == 0) {
            System.out.println("Не заданы входные файлы.");
            return false;
        }
        return true;
    }

    public String makeOutFilePath(String baseFileName) {
        String outputFilePath = this.outputPath;
        if (outputFilePath.isEmpty()) {
            outputFilePath = getProperty("user.dir");
        }
        return outputFilePath + File.separatorChar + outputPrefix + baseFileName;
    }
}
