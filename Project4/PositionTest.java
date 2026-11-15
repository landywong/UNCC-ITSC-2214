import itsc2214.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * JUnit 4 tests for Position: constructor, getRow, getCol, toString,
 * and every branch of equals.
 */
public class PositionTest {
    /**
     * Test that getRow() returns the correct row.
     */
    @Test
    public void testGetRow() {
        Position p = new Position(2, 5);
        assertEquals(2, p.getRow());
    }

    /**
     * Test that getRow() returns the correct column.
     */
    @Test
    public void testGetCol() {
        Position p = new Position(2, 5);
        assertEquals(5, p.getCol());
    }

    /**
     * Test that get method works for 0.
     */
    @Test
    public void testZeroValues() {
        Position p = new Position(0, 0);
        assertEquals(0, p.getRow());
        assertEquals(0, p.getCol());
    }

    /**
     * Test that get method works for negative ints.
     */
    @Test
    public void testNegativeValues() {
        Position p = new Position(-1, -3);
        assertEquals(-1, p.getRow());
        assertEquals(-3, p.getCol());
    }

    /**
     * Test that toString properly prints in parenthesis notation, (row, col).
     */
    @Test
    public void testToString() {
        assertEquals("(2, 3)", new Position(2, 3).toString());
    }

    /**
     * Test that toString properly prints for 0.
     */
    @Test
    public void testToStringZero() {
        assertEquals("(0, 0)", new Position(0, 0).toString());
    }

    /**
     * Test that toString properly prints for negative values.
     */
    @Test
    public void testToStringNegative() {
        assertEquals("(-1, -4)", new Position(-1, -4).toString());
    }

    /**
     * Test that equals() override function correctly compare the values rather than
     * the object, same obj.
     */
    @Test
    public void testEqualsSameObject() {
        Position p = new Position(1, 2);
        assertTrue(p.equals(p));
    }

    /**
     * Test that equals() override function correctly compare the values rather than
     * the object, diff obj same value.
     */
    @Test
    public void testEqualsDiffObject() {
        Position p = new Position(1, 2);
        Position pp = new Position(1, 2);
        assertTrue(p.equals(pp));
    }

    /**
     * Test that equals() override function correctly compare the values rather than
     * the object, diff values.
     */
    @Test
    public void testEqualsDifferentRowAndCol() {
        assertFalse(new Position(1, 2).equals(new Position(3, 4)));
    }

    /**
     * Test that equals() override function correctly with null value.
     */
    @Test
    public void testEqualsNull() {
        assertFalse(new Position(1, 2).equals(null));
    }
}