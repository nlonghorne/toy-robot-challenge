package org.nlonghorne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot();
        InputProcessor processor = new InputProcessor(robot);
        readFromStdIn(processor);
    }

    private static void readFromStdIn(InputProcessor processor) {
        System.out.println("Enter commands for the toy robot. Type 'EXIT' to finish.");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equalsIgnoreCase("EXIT")) {
                    break;
                }
                processor.process(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }
}