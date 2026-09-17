import java.util.Random;

public class GroceryCartSimulatorArray {
    public static void main(String[] args) {
        Bag cart = new Bag();

        // Testing the Bag implementation
        cart.add("Apple");
        cart.add("Banana");
        cart.add("Apple"); // Duplicate entry
        cart.add("Milk");

        System.out.println("--- Current Cart Inventory ---");
        cart.displayInventory();

        System.out.println("\nRandom item grabbed: " + cart.grabRandomItem());
    }
}

class Bag {
    private String[] items;
    private int size;
    private static final int DEFAULT_CAPACITY = 20;

    public Bag() {
        items = new String[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Add String to fixed array
     * 
     * @param item string of food items
     */
    public void add(String item) {
        items[size++] = item;
    }

    /**
     * Pick and return a random item name from among those stored in the bag.
     * Return null if the bag is empty.
     */
    Random rand = new Random();

    public String grabRandomItem() {
        if (size == 0) {
            return null;
        }
        return items[rand.nextInt(size)];
    }

    /**
     * For each unique item stored in the bag, display the item and its frequency.
     */
    public void displayInventory() {
        boolean[] counted = new boolean[size];

        for (int i = 0; i < size; i++) {
            if (counted[i]) {
                continue; // already displayed this item earlier in the loop
            }

            String currentItem = items[i];
            int count = 1;

            // look ahead for duplicates of this item
            for (int j = i + 1; j < size; j++) {
                if (items[j].equals(currentItem)) {
                    count++;
                    counted[j] = true;
                }
            }

            System.out.println(currentItem + " x" + count);
        }
    }
}