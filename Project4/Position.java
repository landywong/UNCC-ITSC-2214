import itsc2214.*;

/**
 * Position Class.
 * Records the row and coloumn a value is stored.
 */
public class Position {

    // Define all the methods as specified in the project documentation.
    private int row;
    private int col;

    /**
     * Position constructor.
     *
     * @param row the row index
     * @param col the column index
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the row of this position.
     *
     * @return the row index
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column of this position.
     *
     * @return the column index
     */
    public int getCol() {
        return col;
    }

    /**
     * Returns this position in parenthesis notation.
     *
     * @return the position as a String
     */
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    /**
     * Returns true if arg is a Position holding the same row and
     * column values as this one (equality, not identity).
     *
     * @param arg the object to compare against
     * @return true if arg is an equal Position, false otherwise
     */
    @Override
    public boolean equals(Object arg) {
        if (arg instanceof Position) {
            Position other = (Position) arg;
            return this.row == other.row && this.col == other.col;
        }
        return false;
    }
}
