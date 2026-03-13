import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nlonghorne.InputProcessor;
import org.nlonghorne.Robot;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotIntegrationTests {
    private Robot testRobot;

    @BeforeEach
    void setup() {
        testRobot = new Robot();
    }

    @Test
    void exampleA() {
        PrintStream originalSystemOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        InputProcessor processor = new InputProcessor(testRobot);

        try {
            processor.process("PLACE 0,0,NORTH");
            processor.process("MOVE");
            processor.process("REPORT");

            String expectedOutput = "Co-ordinates: " + 0 + ", " + 1 + ", NORTH" + System.lineSeparator();
            assertEquals(expectedOutput, output.toString());
        } finally {
            System.setOut(originalSystemOut);
        }
    }

    @Test
    void exampleB() {
        PrintStream originalSystemOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        InputProcessor processor = new InputProcessor(testRobot);

        try {
            processor.process("PLACE 0,0,NORTH");
            processor.process("LEFT");
            processor.process("REPORT");

            String expectedOutput = "Co-ordinates: " + 0 + ", " + 0 + ", WEST" + System.lineSeparator();
            assertEquals(expectedOutput, output.toString());
        } finally {
            System.setOut(originalSystemOut);
        }
    }

    @Test
    void exampleC() {
        PrintStream originalSystemOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        InputProcessor processor = new InputProcessor(testRobot);

        try {
            processor.process("PLACE 1,2,EAST");
            processor.process("MOVE");
            processor.process("MOVE");
            processor.process("LEFT");
            processor.process("MOVE");
            processor.process("REPORT");

            String expectedOutput = "Co-ordinates: " + 3 + ", " + 3 + ", NORTH" + System.lineSeparator();
            assertEquals(expectedOutput, output.toString());
        } finally {
            System.setOut(originalSystemOut);
        }
    }
}
