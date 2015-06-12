package com.epam.taskthree.report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Reporter {
    public static void writeToFile(String text, String pathToFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile));) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
