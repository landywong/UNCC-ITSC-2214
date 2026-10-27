import java.util.Arrays;
// import java.util.StringTokenizer; commented out since unused (for WebCAT)
import itsc2214.*;

/**
 * Project 3 Setup
 * ITSC 2214 Fall 2026
 * 
 * @author Landon Lwea, 801349148
 */
public class Project3 implements ExpressionEvaluator {

    /**
     * Tokenize() method takes the string.
     * Then converts into array
     * 
     * @param expr is the string being read.
     * @return expr as an array without whitespaces
     */
    public String[] tokenize(String expr) {
        // modified expr for param assignment webCAT
        String expr2 = expr.replaceAll("\\s+", ""); // remove whitespace first
        String[] tokens = expr2.split("(?<=[-+*/()])|(?=[-+*/()])");
        return Arrays.stream(tokens)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    // Override of all the methods from ExpressionEvaluator
    // here

    /**
     * balancedParenthesis() scans the expression for parenthesis.
     * Returns true if parenthesis are found.
     * 
     * @param expr is the string being read.
     */
    @Override
    public boolean balancedParenthesis(String expr) {
        StackADT<Character> stack = Factory.makeStackArrayList();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    /**
     * infix2Postfix() converts infix to postfix.
     * Returns expression as postfix based on ITSC2214.
     * 
     * @param expr is the string being read.
     */
    @Override
    public QueueADT<String> infix2Postfix(String expr) {
        QueueADT<String> out = Factory.makeQueueArrayList();
        StackADT<String> ops = Factory.makeStackArrayList();

        String[] tokens = tokenize(expr);
        for (String t : tokens) {
            if (t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")) {
                boolean pop = true;
                while (pop && !ops.isEmpty()) {
                    String top = ops.peek();
                    if (top.equals("(")) {
                        pop = false;
                    } else {
                        out.enqueue(ops.pop());
                    }
                }
                ops.push(t);

            } else if (t.equals("(")) {
                ops.push(t);
            } else if (t.equals(")")) {
                while (!ops.isEmpty() && !ops.peek().equals("(")) {
                    out.enqueue(ops.pop());
                }
                if (ops.isEmpty()) {
                    throw new IllegalArgumentException("Parenthesis Error");
                }
                ops.pop();
            } else {
                out.enqueue(t);
            }
        }

        while (!ops.isEmpty()) {
            String op = ops.pop();
            if (op.equals("(")) {
                throw new IllegalArgumentException("Parenthesis Error");
            }
            out.enqueue(op);
        }

        return out;
    }

    /**
     * evaluatePostfix() scans wether the postfix divideds by 0.
     * Returns last result value.
     * 
     * @param list is the postfix expression being scanned.
     */
    @Override
    public int evaluatePostfix(QueueADT<String> list)
            throws ArithmeticException, IllegalArgumentException {
        StackADT<Integer> vals = Factory.makeStackArrayList();

        while (!list.isEmpty()) {
            String t = list.dequeue();

            if (t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")) {
                if (vals.isEmpty()) {
                    throw new IllegalArgumentException("Operand Error");
                }
                int right = vals.pop();
                if (vals.isEmpty()) {
                    throw new IllegalArgumentException("Operand Error");
                }
                int left = vals.pop();

                // Evaluates operands
                if (t.equals("+")) {
                    vals.push(left + right);
                } else if (t.equals("-")) {
                    vals.push(left - right);
                } else if (t.equals("*")) {
                    vals.push(left * right);
                } else {
                    if (right == 0) {
                        throw new ArithmeticException("Error: divide by zero");
                    }
                    vals.push(left / right);
                }
            } else {
                vals.push(Integer.parseInt(t));
            }
        }

        if (vals.isEmpty()) {
            throw new IllegalArgumentException("Empty expression");
        }
        int result = vals.pop();
        if (!vals.isEmpty()) {
            throw new IllegalArgumentException("Too many operands");
        }

        return result;
    }

    /**
     * evaluateInfix() scans wether the paranthesis found in the string are valid.
     * All paranthesis must be closed bracket ().
     * 
     * @param expr is the string being read.
     */
    @Override
    public int evaluateInfix(String expr) throws ArithmeticException, IllegalArgumentException {
        if (!balancedParenthesis(expr)) {
            throw new IllegalArgumentException("Parenthesis Error");
        }
        return evaluatePostfix(infix2Postfix(expr));
    }
}