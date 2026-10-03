import itsc2214.Node;

/**
 * A class that implements a chain of lights for
 * the holidays. Each chain contains initially 5
 * but you can change some by search for particular
 * colors.
 *
 * TODO Change your EMAIL below
 * TODO Add your team members EMAILS for group submission
 * 
 * @author mperez19@charlotte.edu
 * @author rvellan1@charlotte.edu
 * @author mspitz1@charlotte.edu
 * @version Sep 25, 2024
 */

public class LightChain {

    /**
     * Use these constants in the main program to pass
     * a color to the routines in this file. When you
     * print this string, the output will set the
     * background of the appropriate color.
     */

    /** LightChain.RED. */
    final static String RED = "\u001b[41m Red  \u001b[0m";

    /** LightChain.GREEN. */
    final static String GREEN = "\u001b[42mGreen \u001b[0m";

    /** LightChain.YELLOW. */
    final static String YELLOW = "\u001b[43mYellow\u001b[0m";

    /** LightChain.BLUE. */
    final static String BLUE = "\u001b[44m Blue \u001b[0m";

    /** LightChain.ORANGE. */
    final static String ORANGE = "\u001b[41mOrange\u001b[0m";

    /** LightChain.PURPLE. */
    final static String PURPLE = "\u001b[45mPurple\u001b[0m";

    /** LightChain.WHITE. */
    final static String WHITE = "White";

    /**
     * The reference to the beginning light node of this chain.
     */
    protected Node<String> headOfLightString;

    /**
     * Constructor, build a chain of lights: Red,Green,Blue,Yellow,White,
     * initializing the instance variable, headOfLightString
     */
    public LightChain() {
        // TCreate a linked list of holiday lights as a chain of lights,
        // using the following colors: Red, Green, Blue, Yellow, and White. 
        Node<String> red = new Node<String>(RED);
        Node<String> green = new Node<String>(GREEN);
        Node<String> blue = new Node<String>(BLUE);
        Node<String> yellow = new Node<String>(YELLOW);
        Node<String> white = new Node<String>(WHITE);

        red.setNext(green);
        green.setNext(blue);
        blue.setNext(yellow);
        yellow.setNext(white);

        headOfLightString = red;
    }

    /**
     * Returns the color of the light bulb at
     * position x.
     * 
     * @param pos position of the bulb to get
     * @return color
     */
    public String get(int pos) {
        int idx = pos;
        Node<String> cur = headOfLightString;
        while (cur != null && idx > 0) {
            idx--;
            cur = cur.getNext();
        }
        if (cur != null) {
            return cur.getData();
        } else {
            return null;
        }
    }

    /**
     * Join two LightChains by connecting anotherPiece
     * to the end of this one.
     * 
     * @param anotherPiece a LightChain to append to this one
     * @return this chain, appropriate for doing multiple chaining
     */
    public LightChain join(LightChain anotherPiece) {
        Node<String> cur = headOfLightString;
        while (cur.getNext() != null) {
            cur = cur.getNext();
        }
        cur.setNext(anotherPiece.headOfLightString);
        return this;
    }

    /**
     * Traverse the light chain, printing the node
     * value for each.
     */
    public void traverseLightChain() {
        // Traverse the chain of lights and print out.
        // the color of each light in order.
        Node<String> cur = headOfLightString;
        while (cur != null) {
            System.out.print(cur.getData() + " ");
            cur = cur.getNext();
        }
        System.out.println();
    }

    /**
     * Insert the newColor after another color (afterColor).
     * 
     * @param newColor   the color to be inserted
     * @param afterColor insert it after this color
     */
    public void insertAfter(String newColor, String afterColor) {
        // Adds a new light (newColor) after lights. 
        Node<String> cur = headOfLightString;
        while (cur != null) {
            if (cur.getData().equals(afterColor)) {
                Node<String> newNode = new Node<String>(newColor);
                newNode.setNext(cur.getNext()); // new node points to the rest first
                cur.setNext(newNode); // then link the old node to it
                return; // stop after the first match
            }
            cur = cur.getNext();
        }
    }

    /**
     * Replace all of the colors aColor with the given anotherColor.
     * 
     * @param aColor       the color to be replaced.
     * @param anotherColor the color that will replace it.
     */
    public void replaceAll(String aColor, String anotherColor) {
        // Replaces all lightbulbs of a color light with another light.
        Node<String> cur = headOfLightString;
        while (cur != null) {
            if (cur.getData().equals(aColor)) {
                cur.setData(anotherColor);
            }
            cur = cur.getNext();
        }
    }

}
