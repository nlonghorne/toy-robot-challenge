package org.nlonghorne;

public class Robot {
    private int x;
    private int y;
    private Direction direction;
    private boolean isPlaced;

    public Robot() {
        this.isPlaced = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void place(int x, int y, Direction f) {
        if (x < 0 || x > 4) {
            System.out.println("Invalid co-ordinates, X co-ordinate is out of bounds. Please try again.");
        } else if (y < 0 || y > 4) {
            System.out.println("Invalid co-ordinates, Y co-ordinate is out of bounds. Please try again.");
        } else if (f == null) {
            System.out.println("Invalid direction. Please try again.");
        }else {
            this.x = x;
            this.y = y;
            this.direction = f;
            isPlaced = true;
        }
    }

    public void move() {
        if (!isPlaced()) {
            System.out.println("You cannot move before the robot is placed. Please try again.");
        } else {
            switch (this.direction) {
                case NORTH:
                    if (this.y < 4) {
                        this.y++;
                    } else {
                        System.out.println("The robot cannot move further in this direction.");
                    }
                    break;
                case EAST:
                    if (this.x < 4) {
                        this.x++;
                    } else {
                        System.out.println("The robot cannot move further in this direction.");
                    }
                    break;
                case SOUTH:
                    if (this.y > 0) {
                        this.y--;
                    } else {
                        System.out.println("The robot cannot move further in this direction.");
                    }
                    break;
                case WEST:
                    if (this.x > 0) {
                        this.x--;
                    } else {
                        System.out.println("The robot cannot move further in this direction.");
                    }
                    break;
            }
        }
    }

    public void left() {
        // Assuming left from North therefore anti-clockwise
        if (!isPlaced()) {
            System.out.println("You cannot rotate left before the robot is placed. Please try again.");
        } else {
            switch (this.direction) {
                case NORTH:
                    this.direction = Direction.WEST;
                    break;
                case EAST:
                    this.direction = Direction.NORTH;
                    break;
                case SOUTH:
                    this.direction = Direction.EAST;
                    break;
                case WEST:
                    this.direction = Direction.SOUTH;
                    break;
            }
        }
    }

    public void right() {
        // Assuming right from North therefore clockwise
        if (!isPlaced()) {
            System.out.println("You cannot rotate right before the robot is placed. Please try again.");
        } else {
            switch (this.direction) {
                case NORTH:
                    this.direction = Direction.EAST;
                    break;
                case EAST:
                    this.direction = Direction.SOUTH;
                    break;
                case SOUTH:
                    this.direction = Direction.WEST;
                    break;
                case WEST:
                    this.direction = Direction.NORTH;
                    break;
            }
        }
    }

    public String report() {
        String output = "";
        if (!isPlaced()) {
            output = "You cannot get a report before the robot is placed. Please try again.";
        } else {
            output = "Co-ordinates: " + getX() + ", " + getY() + ", " + getDirection().toString();
        }
        return output;
    }
}
