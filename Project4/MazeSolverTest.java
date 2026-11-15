import itsc2214.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests for MazeSolver. The tests read the provided maze files
 * from the working directory (txt files).
 * 
 * Files used:
 * m2x2 2x2, all open
 * maze3 5x5, one route to the target; dead-end pockets
 * maze4 5x5, one route to the target; dead end at (4,0)
 * maze5 5x5, a single snaking corridor
 * maze6 10x10, one coin at (0,9), all open cells connected
 * maze0g 5x5, all open, coins at (2,2), (3,3), (4,1)
 * mazeg 5x5, every cell a coin (mix of G and 4)
 * maze-dead 5x5, all walls
 * mazejunk not a maze, so no grid is read
 */
public class MazeSolverTest {
    /**
     * Setup all maze txt files.
     */
    private static final String M2X2 = "m2x2-1.txt";
    private static final String MAZE3 = "maze3-1.txt";
    private static final String MAZE4 = "maze4-1.txt";
    private static final String MAZE5 = "maze5-1.txt";
    private static final String MAZE6 = "maze6-1.txt";
    private static final String MAZE0G = "maze0g-1.txt";
    private static final String MAZEG = "mazeg-1.txt";
    private static final String DEAD = "maze-dead-1.txt";
    private static final String JUNK = "mazejunk-1.txt";

    /**
     * Counts how many cells in the grid hold the given value.
     * 
     * @param value is what cell type its counting (open, wall, visited, path, gold)
     * @param grid  is a 2D array used as a maze
     * @return an int value of have many cells in the grid
     */
    private int countCells(MazeSolver.CELL[][] grid, MazeSolver.CELL value) {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == value) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Tests that m2x2 file is constructed properly.
     */
    @Test
    public void testConstructorSmallMaze() {
        MazeSolver m = new MazeSolver(M2X2);
        assertEquals(2, m.getSize());
        assertEquals(new Position(0, 0), m.getStartPosition());
        assertEquals(new Position(1, 1), m.getTargetPosition());
    }

    /**
     * Tests that maze6 file is constructed properly.
     * Maze6 is more complex testing for gold and wall return included.
     */
    @Test
    public void testConstructorMaze6() {
        MazeSolver m = new MazeSolver(MAZE6);
        assertEquals(10, m.getSize());
        assertEquals(new Position(9, 9), m.getTargetPosition());
        assertEquals(MazeSolver.CELL.GOLDCOIN, m.getGrid()[0][9]);
        assertEquals(MazeSolver.CELL.WALL, m.getGrid()[0][6]);
        assertEquals(1, countCells(m.getGrid(), MazeSolver.CELL.GOLDCOIN));
    }

    /**
     * Tests that positionIsTarget() functions for target.
     * Should return true.
     */
    @Test
    public void testPositionIsTargetTrue() {
        MazeSolver m = new MazeSolver(M2X2);
        assertTrue(m.positionIsTarget(new Position(1, 1)));
    }

    /**
     * Tests that positionIsTarget() functions for non-target.
     * Should return false.
     */
    @Test
    public void testPositionIsTargetFalse() {
        MazeSolver m = new MazeSolver(M2X2);
        assertFalse(m.positionIsTarget(new Position(0, 0)));
        assertFalse(m.positionIsTarget(new Position(1, 0)));
    }

    /**
     * Tests that positionIsTarget() functions for null.
     * Should return false.
     */
    @Test
    public void testPositionIsTargetNull() {
        MazeSolver m = new MazeSolver(M2X2);
        assertFalse(m.positionIsTarget(null));
    }

    /**
     * Tests positionIsValid() for valid positions.
     * Should return true.
     */
    @Test
    public void testPositionIsValid() {
        MazeSolver m = new MazeSolver(M2X2);
        assertTrue(m.positionIsValid(new Position(0, 0)));
        assertTrue(m.positionIsValid(new Position(1, 1)));
    }

    /**
     * Tests positionIsValid() for non-valid positions.
     * (null position & grid, out-of-bounds, and negative cases)
     * Should return false.
     */
    @Test
    public void testPositionIsNotValid() {
        MazeSolver m = new MazeSolver(M2X2);
        assertFalse(m.positionIsValid(new Position(-1, -1)));
        assertFalse(m.positionIsValid(new Position(2, 2)));
        assertFalse(m.positionIsValid(null));
        m.setGrid(null);
        assertFalse(m.positionIsValid(new Position(0, 0)));
    }

    /**
     * Tests positionIsAvailable is functioning properly for all valid cases.
     * (open true, wall false, gold true should be returned)
     */
    @Test
    public void testPositionIsAvailableOpen() {
        MazeSolver m = new MazeSolver(MAZE6);
        assertTrue(m.positionIsAvailable(new Position(0, 0)));
        assertFalse(m.positionIsAvailable(new Position(0, 6)));
        assertTrue(m.positionIsAvailable(new Position(0, 9)));
    }

    /**
     * Tests positionIsAvailable is functioning properly for non-valid cases.
     * (null, out-of-bounds, and negative cases)
     * Should return false.
     */
    @Test
    public void testPositionIsAvailableInvalid() {
        MazeSolver m = new MazeSolver(M2X2);
        assertFalse(m.positionIsAvailable(new Position(-1, 0)));
        assertFalse(m.positionIsAvailable(new Position(0, 2)));
        assertFalse(m.positionIsAvailable(null));
    }

    /**
     * Tests positionHasGold for all cases.
     * Should return true for G/4.
     * Should return false for 1, 0, out-of-bounds, and negatives.
     */
    @Test
    public void testPositionHasGoldTrue() {
        MazeSolver m = new MazeSolver(MAZE6);
        assertFalse(m.positionHasGold(new Position(0, 0)));
        assertFalse(m.positionHasGold(new Position(0, 6)));
        assertTrue(m.positionHasGold(new Position(0, 9)));
        assertFalse(m.positionHasGold(new Position(-1, 0)));
        assertFalse(m.positionHasGold(new Position(0, 10)));
    }

    /**
     * Test that markAsVisited functions correctly marks if a position has been
     * visited.
     * Should increase count and mark cell visited. (down 1)
     */
    @Test
    public void testMarkAsVisited() throws Exception {
        MazeSolver m = new MazeSolver(M2X2);
        m.markAsVisited(new Position(1, 0));
        assertEquals(MazeSolver.CELL.VISITED, m.getGrid()[1][0]);
        assertEquals(1, countCells(m.getGrid(), MazeSolver.CELL.VISITED));
    }

    /**
     * Test that markAsVisited functions correctly marks travel path
     * Should increase count and mark cell visited. (down 1)
     */
    @Test
    public void testMarkAsPath() throws Exception {
        MazeSolver m = new MazeSolver(M2X2);
        m.markAsPath(new Position(1, 0));
        assertEquals(MazeSolver.CELL.PATH, m.getGrid()[1][0]);
        assertEquals(1, countCells(m.getGrid(), MazeSolver.CELL.PATH));
    }

    /**
     * Test that markAsVisited functions correctly when visiting a non-valid
     * position.
     * Should throw an exception
     */
    @Test(expected = IllegalAccessException.class)
    public void testMarkAsVisitedThrows() throws Exception {
        MazeSolver m = new MazeSolver(M2X2);
        m.markAsVisited(new Position(-1, 10));
    }

    /**
     * Test that markAsPath functions correctly when visiting a non-valid
     * position.
     * Should throw an exception
     */
    @Test(expected = IllegalAccessException.class)
    public void testMarkAsPathNegativeThrows() throws Exception {
        MazeSolver m = new MazeSolver(M2X2);
        m.markAsPath(new Position(-1, 10));
    }

    /**
     * Tests that traverse() functions properly, recording visit and path.
     * Tranversal must be able to vist all ways (up,right,left,down) and select 1
     * path.
     */
    @Test
    public void testTraverseLargeMaze() throws Exception {
        MazeSolver m = new MazeSolver(MAZE6);
        assertTrue(m.traverse());
        MazeSolver.CELL[][] g = m.getGrid();
        assertEquals(MazeSolver.CELL.PATH, g[0][0]);
        assertEquals(MazeSolver.CELL.VISITED, g[9][9]);
    }

    /**
     * Tests if pickupGoldCoins() functions properly for valid maze.
     * Uses maze g (5x5 all gold) to test if all gold is collected.
     * Should return 25, 1 gold for each pos.
     */
    @Test
    public void testPickupAllCoins() throws Exception {
        MazeSolver m = new MazeSolver(MAZEG);
        assertEquals(25, m.pickupGoldCoins());
        assertEquals(25, countCells(m.getGrid(), MazeSolver.CELL.VISITED));
        assertEquals(0, countCells(m.getGrid(), MazeSolver.CELL.GOLDCOIN));
    }

    /**
     * Tests if pickupGoldCoins() functions properly for no gold maze.
     * Uses maze2x2 for no gold.
     * Should return 0 gold but still visted (4).
     */
    @Test
    public void testPickupNoCoins() throws Exception {
        MazeSolver m = new MazeSolver(M2X2);
        assertEquals(0, m.pickupGoldCoins());
        assertEquals(4, countCells(m.getGrid(), MazeSolver.CELL.VISITED));
    }
}