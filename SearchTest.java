import org.junit.*;
import static org.junit.Assert.*;

public class SearchTest {
    private Search runner;
    private int[] data;

    /**
     * setup() method, runs before each of your test methods.
     * Use this method to recreate the objects needed for
     * testing your class.
     */
    @Before
    public void setup() {
        runner = new Search();
        data = new int[] { 5, 12, 15, 4, 8, 12, 7 };
    }

    /**
     * Simple test. Search for first element in the list.
     */
    @Test
    public void test1() {
        int r = runner.search(data, 5); // Search for 5 in the data
        assertEquals("Index of value 5 should be 0", 0, r); // Should return 0
    }

    /**
     * Edgecase test, null list, should return -1
     */
    @Test
    public void test2() {
        data = null;
        int r = runner.search(data, 5); // Search for 5 in the data
        assertEquals("Value is null: return -1", -1, r);
    }

    /**
     * Edgecase test, list does not contain target vaule
     */
    @Test
    public void test3() {
        data = new int[] { 5, 12, 15, 4, 8, 12, 7 };
        int r = runner.search(data, 10);
        assertEquals("Value does not exist: return -1", -1, r);
    }
}