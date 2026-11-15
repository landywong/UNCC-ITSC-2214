import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Maze interface. Defines methods to
 * traverse and solve a maze.
 * 
 * DO NOT SUBMIT THIS FILE TO WEB-CAT.
 * 
 * It will be there already waiting for
 * your submission.
 */
public abstract class Maze {
    enum CELL {
        WALL, OPEN, VISITED, PATH, GOLDCOIN
        /* 0, 1, 2, 3, 4 */
    };

    /**
     * Reads a file in and stores the grid for
     * the maze based on the content of the file.
     * The file contains the size of the maze,
     * the start position for the
     * 
     * @param inFile name of the file
     */
    public void readFile(String inFile) {
        String line;
        int size = 5;
        Position start = new Position(0, 0);
        Position target = new Position(size - 1, size - 1);
        CELL[][] g = null;

        try {
            File myObj = new File(inFile);
            Scanner scan = new Scanner(myObj);

            while (scan.hasNext()) {
                line = scan.nextLine();
                if (line.equalsIgnoreCase("size")) {
                    size = scan.nextInt();
                    target = new Position(size - 1, size - 1);
                } else if (line.equalsIgnoreCase("grid")) {
                    g = new MazeSolver.CELL[size][size];
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            String s = scan.next();
                            if (s.equalsIgnoreCase("S")) {
                                g[i][j] = MazeSolver.CELL.OPEN;
                                start = new Position(i, j);
                            } else if (s.equalsIgnoreCase("T")) {
                                g[i][j] = MazeSolver.CELL.OPEN;
                                target = new Position(i, j);
                            } else if (s.equalsIgnoreCase("G")) {
                                g[i][j] = MazeSolver.CELL.GOLDCOIN;
                            } else {
                                g[i][j] = MazeSolver.CELL.values()[Integer.parseInt(s)];
                            }
                        }
                    }
                }
            }
            scan.close();
            if (g == null) {
                System.err.println("An error occurred reading file " + inFile + ".");
                System.err.println("No grid read from file.");
                // System.exit(1);
            }
            setGrid(g);
            setStartPosition(start);
            setTargetPosition(target);
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred reading file " + inFile + ".");
            e.printStackTrace();
            // System.exit(1);
        }
    }

    // All of the methods below need to be defined in the
    // MazeSolver class and properly documented.
    public abstract CELL[][] getGrid();

    public abstract void setGrid(CELL[][] g);

    public abstract int getSize();

    public abstract void setStartPosition(Position st);

    public abstract Position getStartPosition();

    public abstract void setTargetPosition(Position ed);

    public abstract Position getTargetPosition();

    public abstract boolean positionIsTarget(Position pos);

    public abstract boolean positionIsValid(Position pos);

    public abstract boolean positionHasGold(Position pos);

    public abstract boolean positionIsAvailable(Position pos);

    public abstract void markAsVisited(Position pos)
            throws IllegalAccessException;

    public abstract void markAsPath(Position pos)
            throws IllegalAccessException;

    public abstract boolean traverse(final Position p)
            throws IllegalAccessException;

    public abstract int pickupGoldCoins(final Position p)
            throws IllegalAccessException;

    // These are provided for you.
    public boolean traverse() throws IllegalAccessException {
        return traverse(getStartPosition());
    }

    public int pickupGoldCoins() throws IllegalAccessException {
        return pickupGoldCoins(getStartPosition());
    }

    @Override
    public String toString() {
        CELL[][] g = getGrid();
        StringBuilder result = new StringBuilder("\n");
        for (int r = 0; r < getSize(); r++) {
            result.append("[");
            for (int c = 0; c < getSize(); c++) {
                if (g[r][c] == CELL.WALL)
                    result.append(" + ");
                else if (g[r][c] == CELL.OPEN) {
                    if (getStartPosition().equals(new Position(r, c)))
                        result.append(" S "); // if start use S, if end use T
                    else if (getTargetPosition().equals(new Position(r, c)))
                        result.append(" T "); // if end use T
                    else
                        result.append("   ");
                } else if (g[r][c] == CELL.VISITED)
                    result.append(" x ");
                else if (g[r][c] == CELL.PATH)
                    result.append(" √ ");
                else if (g[r][c] == CELL.GOLDCOIN)
                    result.append(" G ");
            }
            result.append("]\n");
        }
        return result.toString();
    }

}
/*
 * Copyright: This programming assignment specification and the provided sample
 * code are protected by copyright. The professor is the exclusive owner of
 * copyright of this material. You are encouraged to take notes and make copies
 * of the specification and the source code for your own educational use.
 * However, you may not, nor may you knowingly allow others to reproduce or
 * distribute the materials publicly without the express written consent of the
 * professor. This includes providing materials to commercial course material
 * suppliers such as CourseHero and other similar services. Students who
 * publicly distribute or display or help others publicly distribute or display
 * copies or modified copies of this material may be in violation of University
 * Policy 406, The Code of Student Responsibility
 * https://legal.charlotte.edu/policies/up-406.
 */
