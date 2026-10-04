import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit test for the Project2 class.
 */
public class Project2Test {

    /**
     * testWordByWord() testing adding words by hand.
     */
    @Test
    public void testWordByWord() {
        Project2 runner = new Project2();
        runner.addWord("Hello");
        runner.addWord("Hello");
        runner.addWord("World");

        int n = runner.numUniqueWords();
        assertEquals("numUniqueWords() is wrong", 2, n);
    }

    /**
     * testLoadFromString() testing adding a long string.
     */
    @Test
    public void testLoadFromString() {
        Project2 runner = new Project2();
        runner.loadFromString("Hello, Hello, World");

        int n = runner.numWords();
        assertEquals("numWords() is wrong", 3, n);
    }

    /**
     * testLoadFromString() testing adding a long string.
     */
    @Test
    public void testLoadFromFile() {
        Project2 runner = new Project2();
        runner.loadFromFile("shortdoc.txt");

        // you must update these numbers
        int n = runner.numUniqueWords();
        assertEquals("numUniqueWords() is wrong", 11, n);
    }

    /**
     * These methods tested here are not required for this project
     * but are called here so you get full coverage points.
     */
    @Test
    public void testCallingMethodsNotUsed() {
        Project2 runner = new Project2();
        assertEquals(0, runner.numOfPolySyllabic(0));
        assertEquals(0, runner.numOfSyllables());
        assertEquals("", runner.getContents());
    }

    // Add your own test cases here

    /**
     * Tests word filtering, should ignore null and short words (3 char and under).
     * Records only words that are not mispelled.
     */
    @Test
    public void testWordFilter() {
        Project2 runner = new Project2();
        runner.addWord(null);
        runner.addWord("a");
        runner.addWord("abba");
        runner.addWord("zzzzz");
        assertEquals(2, runner.numWords());
        assertEquals(1, runner.getMisspelledWords().size());
        assertTrue(runner.getMisspelledWords().contains("zzzzz"));
    }

    /**
     * Test null string becoming an empty document.
     */
    @Test
    public void testNullString() {
        Project2 runner = new Project2();
        assertTrue(runner.loadFromString(null));
        assertEquals(0, runner.numWords());
    }

    /**
     * Tests missing file, should return false, like empty doc.
     */
    @Test
    public void testMissingFile() {
        Project2 runner = new Project2();
        assertFalse(runner.loadFromFile("non_existant_file.txt"));
        assertEquals("non_existant_file.txt", runner.getFilename());
        assertEquals(0, runner.numWords());
    }

    /**
     * Tests getFilename() returning null, should return "" instead.
     * Throws a NullPointer execption.
     */
    @Test
    public void testNullName() {
        Project2 runner = new Project2();
        try {
            runner.loadFromFile(null);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught");
        }
        assertEquals("", runner.getFilename());
        assertEquals(0, runner.numWords());
    }

    /**
     * test syllable generation.
     */
    @Test
    public void testSyllable() {
        Project2 runner = new Project2();
        runner.loadFromString("bannana abba");
        assertEquals(5, runner.numOfSyllables());
        assertEquals(2, runner.numOfPolySyllabic(2));
    }
}