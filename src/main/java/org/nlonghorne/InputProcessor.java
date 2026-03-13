package org.nlonghorne;

public class InputProcessor {
    private final Robot robot;

    public InputProcessor(Robot robot) {
        this.robot = robot;
    }

    public void process(String input) {
        if (input == null) {
            return;
        }

        String command = input.trim().toUpperCase();
        if (command.isEmpty()) {
            return;
        }

        if (command.startsWith("PLACE")) {
            processPlace(command);
            return;
        }

        switch (command) {
            case "MOVE" -> robot.move();
            case "LEFT" -> robot.left();
            case "RIGHT" -> robot.right();
            case "REPORT" -> System.out.println(robot.report());
            default -> System.out.println("Command not recognised.");
        }
    }

    private void processPlace(String command) {
        String[] commandSections = command.split("\\s+");
        if (commandSections.length != 2) {
            return;
        }

        String[] coordinates = commandSections[1].split(",");
        if (coordinates.length != 3) {
            return;
        }

        try {
            int x = Integer.parseInt(coordinates[0].trim());
            int y = Integer.parseInt(coordinates[1].trim());
            Direction f = Direction.valueOf(coordinates[2].trim());

            robot.place(x, y, f);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid PLACE command.");
        }
    }
}
