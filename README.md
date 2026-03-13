# Toy Robot Simulator

## Overview

This project is a **console-based simulation of a toy robot** moving on a square tabletop.
The robot operates on a **5 × 5 grid** and executes commands provided via **standard input or from a file**.

The robot can be placed on the table, moved around, rotated, and asked to report its position.
The program ensures the robot **never falls off the table**, ignoring any commands that would result in an invalid position.

This implementation focuses on **clean, readable code and simple architecture**, avoiding unnecessary frameworks or complexity.

---

# Tabletop Model

The tabletop is a **5 × 5 grid** with coordinates:

```
(0,0) -------- (4,0)
  |               |
  |               |
(0,4) -------- (4,4)
```

* `(0,0)` represents the **south-west corner**
* Valid X and Y coordinates range from **0 to 4**

Any command that would move the robot outside this area is ignored.

---

# Commands

The application supports the following commands:

| Command       | Description                                                                     |
| ------------- | ------------------------------------------------------------------------------- |
| `PLACE X,Y,F` | Places the robot at position `(X,Y)` facing `NORTH`, `SOUTH`, `EAST`, or `WEST` |
| `MOVE`        | Moves the robot one unit forward in the direction it is currently facing        |
| `LEFT`        | Rotates the robot 90° to the left                                               |
| `RIGHT`       | Rotates the robot 90° to the right                                              |
| `REPORT`      | Prints the robot’s current position and facing direction                        |
| `EXIT`        | Ends the program when using interactive input                                   |

Important rules:

* The **first valid command must be `PLACE`**
* Any commands issued before a valid `PLACE` are ignored
* The robot **cannot move off the table**
* Invalid commands are safely ignored

---

# Example

Input:

```
PLACE 1,2,EAST
MOVE
MOVE
LEFT
MOVE
REPORT
```

Output:

```
3,3,NORTH
```

---

# Running the Application

## Requirements

* Java **17+** (or compatible)
* Maven or standard Java compilation tools

---

## Compile

From the project root:

```bash
javac -d out src/main/java/org/nlonghorne/*.java
```

---

## Run (Interactive Mode)

Start the program:

```bash
java -cp out org.nlonghorne.Main
```

Enter commands directly in the console:

```
PLACE 0,0,NORTH
MOVE
REPORT
```

Exit the program using:

```
EXIT
```

---

# Project Structure

```
src
 ├─ main
 │   └─ java
 │       └─ org.nlonghorne
 │           ├─ Main.java
 │           ├─ InputProcessor.java
 │           ├─ Robot.java
 │           └─ Direction.java
 │
 └─ test
     └─ java
         └─ org.nlonghorne
             ├─ RobotTest.java
             └─ CommandProcessorTest.java
```

### Main Components

**Robot**

Handles the core simulation logic:

* robot position
* movement rules
* rotation
* boundary validation

**Direction**

Enum representing the four cardinal directions.

**InputProcessor**

Parses user input commands and maps them to robot actions.

**Main**

Handles reading commands from:

* standard input

---

# Testing

The project includes both:

### Unit Tests

Test the robot's core logic such as:

* movement
* rotation
* boundary protection
* placement rules

### Integration Tests

Integration tests verify command sequences from the challenge specification.

Example scenarios tested:

```
PLACE 0,0,NORTH
MOVE
REPORT
```

```
PLACE 0,0,NORTH
LEFT
REPORT
```

```
PLACE 1,2,EAST
MOVE
MOVE
LEFT
MOVE
REPORT
```

---

# Design Decisions

Key design principles used:

* **Single Responsibility**

    * Robot manages state and movement
    * InputProcessor handles command parsing
    * Main handles input

* **Robust Input Handling**

    * Invalid commands are ignored
    * Malformed `PLACE` commands are handled safely

* **Minimal Dependencies**

    * Uses only standard Java libraries

The solution intentionally avoids unnecessary frameworks to keep the implementation simple and focused on the core problem.

---

# Author

Nathaniel Longhorne
