import org.junit.*;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;

public class SimpleMetaphoneTest {

    private String test[] = {"knight", "xavier", "scent", 
    "pharaoh", "smyth", "smith", "abscond", "acme", "acoustics", 
    "comic", "lacy","knapsack","gnome","pneumonia",
    "aerodynamic","wreckage", "absence","aficionado"};

    private String key[] = {"nt", "ksvr", "ssnt", 
    "frh", "smyth", "smth", "absknd", "akm", "akstks", 
    "kmk", "lsy", "npsk", "nm", "nmn", "erdynmk", "rkg","absns","afsnd"};

    /**
     * Silly test..
     */
    @Test
    public void testOne()
    {
        SimpleMetaphone sm = new SimpleMetaphone();
        assertNotNull(sm);
    }
    /**
     * testing some words
     */
    @Test
    public void testTwo()
    {
        for (int i = 0; i < test.length; i++) {
            assertEquals("Metaphone for "+test[i]+" was wrong", 
                key[i], SimpleMetaphone.simpleMetaphone(test[i]));
        }
    }

    @Test
    public void testThree()
    {
        String[] tests = {
            "pairs", "pares", "paris", "pars", "parse",
            "parsi", "pearce", "pears", "peers", "perez",
            "persia", "peruse", "pierce", "piers", "piraeus",
            "pores", "porous", "pours", "praise", "price",
            "prize", "pros", "prose", "purse", "pursue"
        };
        for (String word : tests)
            assertEquals("Metaphone for "+word+" was wrong",
                "prs", SimpleMetaphone.simpleMetaphone(word));
    }
    @Test
    public void testFour()
    {
        String[] tests = {
			"lace", "laos", "laughs", "lease", "lees",
			"lice", "lies", "lisa", "lise", "liz",
			"loci", "lois", "loose", "los", "lose",
			"louis", "louisa", "louise", "louse",
			"lucia", "luis"
        };
        for (String word : tests)
            assertEquals("Metaphone for "+word+" was wrong",
                "ls", SimpleMetaphone.simpleMetaphone(word));
    }

    @Test
    public void testAllCases()
    {
        assertEquals("Metaphone for was wrong",
            "nkl", SimpleMetaphone.simpleMetaphone("knuckle"));
        assertEquals("Metaphone for was wrong", 
            "nm", SimpleMetaphone.simpleMetaphone("gnome"));
        assertEquals("Metaphone for was wrong", 
            "erntkl", SimpleMetaphone.simpleMetaphone("aeronautical"));
        assertEquals("Metaphone for was wrong", 
            "nmtk", SimpleMetaphone.simpleMetaphone("pneumatic"));
        assertEquals("Metaphone for was wrong", 
            "rstwtkh", SimpleMetaphone.simpleMetaphone("wristwatch"));
        assertEquals("Metaphone for was wrong", 
            "unsfstktd", SimpleMetaphone.simpleMetaphone("unsophisticated"));
        assertEquals("Metaphone for was wrong", 
            "wkd", SimpleMetaphone.simpleMetaphone("wicked"));
        assertEquals("Metaphone for was wrong", 
            "wstns", SimpleMetaphone.simpleMetaphone("westinghouse"));
        assertEquals("Metaphone for was wrong", 
            "unk", SimpleMetaphone.simpleMetaphone("unique"));
        assertEquals("Metaphone for was wrong", 
            "ksrks", SimpleMetaphone.simpleMetaphone("xerox"));
        assertEquals("Metaphone for was wrong", 
            "slgkl", SimpleMetaphone.simpleMetaphone("zoological"));
        assertEquals("Metaphone for was wrong", 
            "wrsstrshr", SimpleMetaphone.simpleMetaphone("worcestershire"));
        assertEquals("Metaphone for was wrong", 
            "vlsty", SimpleMetaphone.simpleMetaphone("velocity"));
        assertEquals("Metaphone for was wrong", 
            "prfsy", SimpleMetaphone.simpleMetaphone("prophecy"));
        assertEquals("Metaphone for was wrong", 
            "vndktn", SimpleMetaphone.simpleMetaphone("vindication"));
        assertEquals("Metaphone for was wrong", 
            "tlkmmnktn", SimpleMetaphone.simpleMetaphone("telecommunication"));
        assertEquals("Metaphone for was wrong", 
            "rkrsv", SimpleMetaphone.simpleMetaphone("recursive"));
        assertEquals("Metaphone for was wrong", 
            "vhkls", SimpleMetaphone.simpleMetaphone("vehicles"));
        assertEquals("Metaphone for was wrong", 
            "mkmlln", SimpleMetaphone.simpleMetaphone("mcmullen"));
        assertEquals("Metaphone for was wrong", 
            "khrnk", SimpleMetaphone.simpleMetaphone("chronic"));
        assertEquals("Metaphone for was wrong", 
            "pnk", SimpleMetaphone.simpleMetaphone("panic"));
    }

}
