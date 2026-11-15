/**
 * MazeSolver solver code, based on code by Lewis & Chase.
 * Uses recursion to solve the puzzle.
 */
public class MazeSolver extends Maze {
    /**
     * Creates a MazeSolver by reading the maze description from a txt file
     *
     * @param file name of the file containing the maze description
     */
    public MazeSolver(String file) {
        try {
            readFile(file);
        } catch (Exception e) {
            System.err.println("An error occurred reading file " + file + ".");
        }
    }

    private CELL[][] grid;
    private Position startPosition;
    private Position targetPosition;

    /**
     * Returns the internal grid.
     *
     * @return the 2D array of cells
     */
    public CELL[][] getGrid() {
        return grid;
    }

    /**
     * Sets the internal grid.
     *
     * @param g the 2D array of cells to store
     */
    public void setGrid(CELL[][] g) {
        grid = g;
    }

    /**
     * Returns the size of the maze (number of rows).
     *
     * @return the number of rows, or 0 if there is no grid
     */
    public int getSize() {
        if (grid == null) {
            return 0;
        } else {
            return grid.length;
        }
    }

    /**
     * Sets the starting position for the traversals.
     *
     * @param p the start position
     */
    public void setStartPosition(Position p) {
        startPosition = p;
    }

    /**
     * Returns the starting position.
     *
     * @return the start position
     */
    public Position getStartPosition() {
        return startPosition;
    }

    /**
     * Sets the target position for the traversal.
     *
     * @param p the target position
     */
    public void setTargetPosition(Position p) {
        targetPosition = p;
    }

    /**
     * Returns the target position.
     *
     * @return the target position
     */
    public Position getTargetPosition() {
        return targetPosition;
    }

    /**
     * Checks whether a position equals the target position.
     *
     * @param p the position to check
     * @return true if pos is equal to the target position
     */
    public boolean positionIsTarget(Position p) {
        if (p == null) {
            return false;
        } else {
            return p.equals(targetPosition);
        }
    }

    /**
     * Checks whether a position lies inside the grid.
     *
     * @param p the position to check
     * @return true if the row and column are inside the grid
     */
    public boolean positionIsValid(Position p) {
        if (p == null || grid == null) {
            return false;
        }
        int row = p.getRow();
        int col = p.getCol();
        if (row < 0 || row >= grid.length) {
            return false;
        }
        if (col < 0 || col >= grid[row].length) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether a position holds a gold coin.
     *
     * @param p the position to check
     * @return true if pos is valid and contains a GOLDCOIN
     */
    public boolean positionHasGold(Position p) {
        if (positionIsValid(p)) {
            return grid[p.getRow()][p.getCol()] == CELL.GOLDCOIN;
        } else {
            return false;
        }
    }

    /**
     * Checks whether a position can be moved into (OPEN or GOLDCOIN).
     *
     * @param p the position to check
     * @return true if pos is valid and is OPEN or has a GOLDCOIN
     */
    public boolean positionIsAvailable(Position p) {
        if (positionIsValid(p)) {
            CELL cell = grid[p.getRow()][p.getCol()];
            return cell == CELL.OPEN || cell == CELL.GOLDCOIN;
        } else {
            return false;
        }
    }

    /**
     * Marks a position in the grid as VISITED.
     *
     * @param p the position to mark
     * @throws IllegalAccessException if pos is not a valid position
     */
    public void markAsVisited(Position p) throws IllegalAccessException {
        if (positionIsValid(p)) {
            grid[p.getRow()][p.getCol()] = CELL.VISITED;
        } else {
            throw new IllegalAccessException("Invalid position: " + p);
        }
    }

    /**
     * Marks a position in the grid as PATH.
     *
     * @param p the position to mark
     * @throws IllegalAccessException if pos is not a valid position
     */
    public void markAsPath(Position p) throws IllegalAccessException {
        if (positionIsValid(p)) {
            grid[p.getRow()][p.getCol()] = CELL.PATH;
        } else {
            throw new IllegalAccessException("Invalid position: " + p);
        }
    }

    /**
     * Recursively searches for a path from p to the target position.
     * Base case: if p is invalid, a wall, or already visited, returns false.
     * Otherwise p is marked VISITED; if p is the target, returns true.
     * If not, the four neighbors are explored (down, right, up, left), and
     * the first one that reaches the target causes p to be marked PATH
     * and true to be returned, skipping the remaining neighbors.
     *
     * @param p the position to start from
     * @return true if a path to the target was found, false otherwise
     * @throws IllegalAccessException if a mark is attempted on an invalid position
     */
    public boolean traverse(final Position p) throws IllegalAccessException {
        if (!positionIsAvailable(p)) {
            return false;
        }

        markAsVisited(p);

        if (positionIsTarget(p)) {
            return true;
        }

        boolean found = traverse(new Position(p.getRow() + 1, p.getCol()));
        if (!found) {
            found = traverse(new Position(p.getRow(), p.getCol() + 1));
        }
        if (!found) {
            found = traverse(new Position(p.getRow() - 1, p.getCol()));
        }
        if (!found) {
            found = traverse(new Position(p.getRow(), p.getCol() - 1));
        }

        if (found) {
            markAsPath(p);
        }
        return found;
    }

    /**
     * Recursively explores every position reachable from p and counts the
     * gold coins found. Each explored position is marked VISITED.
     *
     * @param p the position to start from
     * @return the number of coins collected
     * @throws IllegalAccessException if a mark is attempted on an invalid position
     */
    public int pickupGoldCoins(final Position p) throws IllegalAccessException {
        int coins = 0;

        if (positionIsAvailable(p)) {
            if (positionHasGold(p)) {
                coins = 1;
            }
            markAsVisited(p);

            coins += pickupGoldCoins(new Position(p.getRow() + 1, p.getCol()));
            coins += pickupGoldCoins(new Position(p.getRow(), p.getCol() + 1));
            coins += pickupGoldCoins(new Position(p.getRow() - 1, p.getCol()));
            coins += pickupGoldCoins(new Position(p.getRow(), p.getCol() - 1));
        }

        return coins;
    }
}
