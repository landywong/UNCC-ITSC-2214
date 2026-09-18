import org.junit.*;
import static org.junit.Assert.*;

/**
 * Reference test for Project 1 (Game of Life).
 */
public class TicTacToeBoardTest {
    private TicTacToeBoard game;

    /**
     * Initializes 3x3 array for game board
     */
    @Before
    public void setup() {
        game = new TicTacToeBoard();
    }

    /**
     * Checks if first row is empty
     */
    @Test
    public void test1() {
        // Test default values
        assertNotNull(game);
        assertTrue(game.positionIsEmpty(0, 0));
        assertTrue(game.positionIsEmpty(0, 1));
        assertTrue(game.positionIsEmpty(0, 2));
    }

    /**
     * Checks if both x and o can be played in cells
     */
    @Test
    public void test2() {
        game.play('o', 0, 0);
        assertFalse(game.positionIsEmpty(0, 0));
        game.play('x', 0, 1);
        assertFalse(game.positionIsEmpty(0, 1));

    }

    /**
     * Verifies that win condition is funtioning
     */
    @Test
    public void test3() {
        game.play('o', 0, 0);
        game.play('x', 0, 1);
        game.play('o', 0, 2);

        game.play('x', 1, 0);
        game.play('x', 1, 1);
        game.play('o', 1, 2);

        game.play('o', 2, 1);
        game.play('x', 2, 0);
        game.play('o', 2, 2);

        assertTrue("Game over? ", game.gameIsOver());
        assertFalse("Game tied? ", game.gameIsATie());
        assertEquals("Winner is  ", 'o', game.getWinner());

    }
}
