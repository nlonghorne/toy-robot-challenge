import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nlonghorne.Direction;
import org.nlonghorne.Robot;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RobotUnitTests {
    private Robot testRobot;

    @BeforeEach
    void setup() {
        testRobot = new Robot();
    }

    @Test
    void invalidMoveBeforePlace() {
        testRobot.move();

        assertFalse(testRobot.isPlaced());
        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertNull(testRobot.getDirection());
    }

    @Test
    void invalidLeftBeforePlace() {
        testRobot.left();

        assertFalse(testRobot.isPlaced());
        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertNull(testRobot.getDirection());
    }

    @Test
    void invalidRightBeforePlace() {
        testRobot.right();

        assertFalse(testRobot.isPlaced());
        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertNull(testRobot.getDirection());
    }

    @Test
    void invalidReportBeforePlace() {
        testRobot.report();

        assertFalse(testRobot.isPlaced());
        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertNull(testRobot.getDirection());
    }

    @Test
    void placeTestAtZeroZeroNorth() {
        testRobot.place(0, 0, Direction.NORTH);

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.NORTH, testRobot.getDirection());
    }

    @Test
    void placeTestAtThreeFourWest() {
        testRobot.place(3, 4, Direction.WEST);

        assertEquals(3, testRobot.getX());
        assertEquals(4, testRobot.getY());
        assertEquals(Direction.WEST, testRobot.getDirection());
    }

    @Test
    void placeTestOutOfBoundsFiveFive() {
        // Set initial co-ordinates
        testRobot.place(0, 0, Direction.NORTH);
        // Set out of bounds co-ordinates
        testRobot.place(5, 5, Direction.WEST);

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.NORTH, testRobot.getDirection());
    }

    @Test
    void placeTestOutOfBoundsBelowZero() {
        // Set initial co-ordinates
        testRobot.place(0, 0, Direction.NORTH);
        // Set out of bounds co-ordinates
        testRobot.place(-1, -2, Direction.WEST);

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.NORTH, testRobot.getDirection());
    }

    @Test
    void placeTestInvalidCoordinatesFollowedByMove() {
        testRobot.place(-1, -2, Direction.WEST);
        testRobot.move();

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertNull(testRobot.getDirection());
        assertFalse(testRobot.isPlaced());
    }

    @Test
    void moveTestNorth() {
        testRobot.place(0, 0, Direction.NORTH);
        testRobot.move();
        assertEquals(0, testRobot.getX());
        assertEquals(1, testRobot.getY());
        assertEquals(Direction.NORTH, testRobot.getDirection());
    }

    @Test
    void moveTestEast() {
        testRobot.place(0, 0, Direction.EAST);
        testRobot.move();

        assertEquals(1, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.EAST, testRobot.getDirection());
    }

    @Test
    void moveTestSouth() {
        testRobot.place(0, 1, Direction.SOUTH);
        testRobot.move();

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.SOUTH, testRobot.getDirection());
    }

    @Test
    void moveTestWest() {
        testRobot.place(1, 0, Direction.WEST);
        testRobot.move();

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.WEST, testRobot.getDirection());
    }

    @Test
    void moveTestOutOfBoundsBelowZeroX() {
        testRobot.place(0, 0, Direction.WEST);
        testRobot.move();

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.WEST, testRobot.getDirection());
    }

    @Test
    void moveTestOutOfBoundsBelowZeroY() {
        testRobot.place(0, 0, Direction.SOUTH);
        testRobot.move();

        assertEquals(0, testRobot.getY());
        assertEquals(0, testRobot.getX());
        assertEquals(Direction.SOUTH, testRobot.getDirection());
    }

    @Test
    void moveTestOutOfBoundsAboveX() {
        testRobot.place(4, 4, Direction.EAST);
        testRobot.move();

        assertEquals(4, testRobot.getX());
        assertEquals(4, testRobot.getY());
        assertEquals(Direction.EAST, testRobot.getDirection());
    }

    @Test
    void moveTestOutOfBoundsAboveY() {
        testRobot.place(4, 4, Direction.NORTH);
        testRobot.move();

        assertEquals(4, testRobot.getX());
        assertEquals(4, testRobot.getY());
        assertEquals(Direction.NORTH, testRobot.getDirection());
    }

    @Test
    void leftTestNorthToWest() {
        testRobot.place(0, 0, Direction.NORTH);
        testRobot.left();

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.WEST, testRobot.getDirection());
    }

    @Test
    void leftTestWestToSouth() {
        testRobot.place(0, 0, Direction.WEST);
        testRobot.left();

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.SOUTH, testRobot.getDirection());
    }

    @Test
    void leftTestSouthToEast() {
        testRobot.place(0, 0, Direction.SOUTH);
        testRobot.left();

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.EAST, testRobot.getDirection());
    }

    @Test
    void leftTestEastToNorth() {
        testRobot.place(0, 0, Direction.EAST);
        testRobot.left();

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.NORTH, testRobot.getDirection());
    }

    @Test
    void rightTestNorthToEast() {
        testRobot.place(0, 0, Direction.NORTH);
        testRobot.right();

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.EAST, testRobot.getDirection());
    }

    @Test
    void rightTestEastToSouth() {
        testRobot.place(0, 0, Direction.EAST);
        testRobot.right();

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.SOUTH, testRobot.getDirection());
    }

    @Test
    void rightTestSouthToWest() {
        testRobot.place(0, 0, Direction.SOUTH);
        testRobot.right();

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.WEST, testRobot.getDirection());
    }

    @Test
    void rightTestWestToNorth() {
        testRobot.place(0, 0, Direction.WEST);
        testRobot.right();

        assertEquals(0, testRobot.getX());
        assertEquals(0, testRobot.getY());
        assertEquals(Direction.NORTH, testRobot.getDirection());
    }

    @Test
    void reportTest() {
        PrintStream originalSystemOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            testRobot.place(0, 0, Direction.WEST);
            testRobot.report();

            String expectedOutput = "Co-ordinates: " + 0 + ", " + 0 + ", WEST" + System.lineSeparator();
            assertEquals(expectedOutput, output.toString());
        } finally {
            System.setOut(originalSystemOut);
        }
    }
}
