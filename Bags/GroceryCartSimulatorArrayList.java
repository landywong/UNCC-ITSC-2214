import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroceryCartSimulatorArrayList {
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
    private ArrayList<String> items;

    public Bag() {
        items = new ArrayList<>();
    }

    /**
     * Adds item to grocery list
     * 
     * @param item string of food item
     */
    public void add(String item) {
        items.add(item);
    }

    /**
     * Pick and return a random item name from among those stored in the bag.
     * Return null if the bag is empty.
     */
    Random rand = new Random();

    public String grabRandomItem() {
        if (items.isEmpty()) {
            return null;
        } else {
            return items.get(rand.nextInt(items.size()));
        }
    }

    /**
     * For each unique item stored in the bag, display the item and its frequency.
     */
    public void displayInventory() {
        ArrayList<String> counted = new ArrayList<>();

        for (String item : items) {
            if (counted.contains(item)) {
                continue; // already displayed this item
            }
            counted.add(item);

            int count = 0;
            for (String other : items) {
                if (other.equals(item)) {
                    count++;
                }
            }

            System.out.println(item + " x" + count);
        }
    }
}