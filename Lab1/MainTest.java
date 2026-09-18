import org.junit.*;
import static org.junit.Assert.*;

/*
 * Very short example of testing.
 */
public class MainTest {
    @Test
    public void testMain() {
        // this calls calls getMessage with null arg
        assertEquals("Calling main with null parameter did not print what was expected.",
                "Hola!", Main.getMessage(null));
    }

    @Test
    public void testWithArg() {

        assertEquals("getMessage returned something unexpected.",
                "Hello, Joe!", Main.getMessage(new String[] { "Joe" }));
    }

    @Test
    public void testWithEmptyArray() {

        assertEquals("Calling main with an empty array of strings did not print what was expected.",
                "Hello World!", Main.getMessage(new String[] {}));
    }

    @Test
    public void testVersion() {
        String r = Main.getVersion();
        assertEquals("Version returned was not correct.", "v.45", r);
    }

    /**
     * This one is optional, for those of you that are ultra
     * curious. You can call the main() method from testing.
     */
    @Test
    public void testCallingMain() {
        new Main(); // this calls the constructor (which doesn't exist) but it
        // doesn't call the main() method... how do you call the main method?
    }
}
