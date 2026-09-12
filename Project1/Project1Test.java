import org.junit.*;
import static org.junit.Assert.*;

/**
 * Reference test for Project 1 (LightsOff).
 */
public class Project1Test {

    private Project1 runner;
    private Project1 iboard;
    private Project1 sboard;
    private Project1 rboard;
    private Project1 edgeboard;

    /**
     * Initialized runner board, starting up a blank board.
     */
    @Before
    public void setup() {
        runner = new Project1();
    }

    /**
     * testOne loads a starting board.
     * testing board size, should match aGame with 3.
     * test on moves, should be 0 since fresh start.
     * test games won, should also be 0.
     * test string and light match using lightIsOn method.
     */
    @Test
    public void testOne() {
        String aGame = "x o x | o o o | x o x |";
        runner.initialize(aGame);

        // What is the size of the board?
        assertEquals(3, runner.size());

        // How many moves have been made at this point?
        assertEquals(0, runner.numMoves());

        // How many games have been won?
        assertEquals(0, runner.numWins());

        // Is the light on at position 0,0, or at position 0,1, etc.
        // next three lines check the top row
        assertFalse(runner.isLightOn(0, 0));
        assertTrue(runner.isLightOn(0, 1));
        assertFalse(runner.isLightOn(0, 2));
    }

    /**
     * testOneMoveWin tests the conditions during game win.
     * Signals win condition by toggling center.
     * tests move and win registration.
     * test play() return after win.
     * tests toString() conversion.
     */
    @Test
    public void testOneMoveWin() {
        String aGame = "x o x | o o o | x o x |";
        runner.initialize(aGame);

        // toString(), should print board as String
        System.out.println(runner.toString());
        assertEquals("x o x | o o o | x o x |", runner.toString());

        // press the button in the center of the grid
        runner.play(4);
        // add asserts here to ensure that your game is working correctly
        assertTrue(runner.isGameOver());
        assertEquals(1, runner.numMoves());
        assertEquals(1, runner.numWins());
        // play() return after game is won, numMoves should remain the same (1)
        runner.play(4);
        assertEquals(1, runner.numMoves());
    }

    /**
     * testTwo tests the board initializations, default, int, string and random.
     * Tests also features edge cases for size of board and incorrect token
     * edgeboard continues testing play() edge cases
     */
    @Test
    public void testTwo() {
        iboard = new Project1(3);
        sboard = new Project1("x o x | o o o | x o z |"); // z char
        // despite the unknown z token, the size should remain 3
        assertEquals(3, sboard.size());
        sboard = new Project1("x o x | o o o | x o x |");
        rboard = new Project1();
        rboard.initialize();
        edgeboard = new Project1();
        edgeboard.initialize(2);
        edgeboard.initialize(100);
        edgeboard.initialize(-1); // -1 is not between 0 and <code>boards.length</code>

        // asserts if boards have been sucessfully created, return true
        assertNotNull(iboard);
        assertNotNull(sboard);
        // asserts if random int is generated in board, true for existing int
        assertTrue(rboard.size() > 0);
        // asserts edge case initialization -1 is turned to board[0]
        assertTrue(edgeboard.size() > 0);

        // asserts validPosition out of range is false, vise versa
        assertFalse(edgeboard.validPosition(-1, -1));
        assertTrue(edgeboard.validPosition(0, 0));
        // for loop for to test that all play squares complie
        edgeboard.play(-1, -1); // invalid square play
        for (int x = 0; x < edgeboard.size(); x++) {
            for (int y = 0; y < edgeboard.size(); y++) {
                edgeboard.play(x, y);
            }
        }
    }
}
