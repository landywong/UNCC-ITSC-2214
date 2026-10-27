import itsc2214.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Project 3 Student Setup
 * ITSC 2214 Fall 2026
 * 
 * @author Landon Lwea, 801349148
 * @version Oct 07, 2026
 */
public class Project3Test {
    private ExpressionEvaluator runner = null;

    /**
     * setup() method, runs before each of your test methods.
     * Use this method to recreate the objects needed for
     * testing your class.
     */
    @Before
    public void setup() {
        runner = new Project3();
    }

    /**
     * Test balancedParenthesis()
     */
    @Test
    public void testBalancedParenthesis() {
        assertTrue("() should be valid",
                runner.balancedParenthesis("()"));

        assertFalse("()) not balanced",
                runner.balancedParenthesis("())"));
        // add other tests here to make sure you
        // have covered all of the possible options
        // on how parenthesis are balanced and to
        // get to 100% coverage for your balancedParenthesis
        // method.
        assertTrue("empty string should be valid", runner.balancedParenthesis(""));
        assertTrue("no parenthesis should be valid", runner.balancedParenthesis("10 + 2"));
        assertTrue("nested parenthesis should be valid", runner.balancedParenthesis("(())"));
        assertTrue("consecutive parenthesis should be valid", runner.balancedParenthesis("()()"));

        assertFalse("(() not balanced", runner.balancedParenthesis("(()"));
        assertFalse("( not balanced", runner.balancedParenthesis("("));
        assertFalse(") not balanced", runner.balancedParenthesis(")"));
        assertFalse(")( not balanced", runner.balancedParenthesis(")("));
    }

    /**
     * Test infixToPostfix()
     */
    @Test
    public void testInfixToPostfix() {
        QueueADT q = runner.infix2Postfix("10 + 20");
        // postfix should be 10 20 +
        assertEquals(3, q.size()); // number of elements in queue
        assertEquals("10", q.dequeue());
        assertEquals("20", q.dequeue());
        assertEquals("+", q.dequeue());
        assertTrue(q.isEmpty());

        // repeat similar comparisons with other expressions such
        // as...
        // 10 + 20 * 5 --> 10 20 + 5 *
        // 10 + (20 * 5) --> 10 20 5 * +
        // etc
        q = runner.infix2Postfix("10 + 20 * 5");
        // postfix should be 10 20 + 5 *
        assertEquals(5, q.size()); // number of elements in queue
        assertEquals("10", q.dequeue());
        assertEquals("20", q.dequeue());
        assertEquals("+", q.dequeue());
        assertEquals("5", q.dequeue());
        assertEquals("*", q.dequeue());
        assertTrue(q.isEmpty());

        q = runner.infix2Postfix("10 + (20 * 5)");
        // postfix should be 10 20 5 * +
        assertEquals(5, q.size()); // number of elements in queue
        assertEquals("10", q.dequeue());
        assertEquals("20", q.dequeue());
        assertEquals("5", q.dequeue());
        assertEquals("*", q.dequeue());
        assertEquals("+", q.dequeue());
        assertTrue(q.isEmpty());
    }

    /**
     * Test evaluatePostfix()
     */
    @Test
    public void testEvaluatePostfix() {
        QueueADT q = runner.infix2Postfix("10 + 20");
        assertEquals(30, runner.evaluatePostfix(q));

        q = runner.infix2Postfix("10 + 20 * 2");
        assertEquals(60, runner.evaluatePostfix(q));

        q = runner.infix2Postfix("10 + (20 * 2)");
        assertEquals(50, runner.evaluatePostfix(q));
        // add more here
    }

    /**
     * Test evaluateInfix()
     */
    @Test
    public void testEvaluateInfix() {
        assertEquals(30, runner.evaluateInfix("10 + 20"));
        assertEquals(60, runner.evaluateInfix("10 + 20 * 2"));
        assertEquals(50, runner.evaluateInfix("10 + (20 * 2)"));
        // add more here
        assertEquals(3, runner.evaluateInfix("7 / 2"));
    }

    // TODO add more test cases
    /**
     * Test divide by zero
     */
    @Test(expected = ArithmeticException.class)
    public void testDivideZero() {
        runner.evaluateInfix("10 / (2 - 2)");
    }

    /**
     * Test infix2Postfix() with a unclosed parenthesis
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInfixToPostfixMissingClose() {
        runner.infix2Postfix("(10 + 20");
    }

    /**
     * Test parenthesis error, inflix has unbalanced parenthesis
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInfixUnbal() {
        runner.evaluateInfix("(10 + 2");
    }

    /**
     * Test evaluatePostfix() with operand left over
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPostfixTooManyOperands() {
        QueueADT<String> q = Factory.makeQueueArrayList();
        q.enqueue("10");
        q.enqueue("20");
        runner.evaluatePostfix(q);
    }

    /**
     * Test evaluatePostfix() with an empty queue
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPostfixEmpty() {
        QueueADT<String> q = Factory.makeQueueArrayList();
        runner.evaluatePostfix(q);
    }

    /**
     * Test evaluatePostfix() with an operator and no operands
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEvaluatePostfixNoOperands() {
        runner.evaluatePostfix(runner.infix2Postfix("+"));
    }

    /**
     * Test evaluatePostfix() with an operator and one operand
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEvaluatePostfixOneOperand() {
        runner.evaluatePostfix(runner.infix2Postfix("10 +"));
    }
}
