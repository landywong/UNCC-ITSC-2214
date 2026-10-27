import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Main program for the SimpleMetaphone class.
 * 
 * @author mpq
 * @version Jul 04, 2025
 */
public class SimpleMetaphoneMain {
    public static void main(String[] args) throws FileNotFoundException {
        StringBuilder fileContents = new StringBuilder();

        // Read the dictionary into a long string with new lines separating the words
        Scanner fileScanner = new Scanner(new File("dictionary.txt"));
        while (fileScanner.hasNextLine()) {
            fileContents.append(fileScanner.nextLine()).append("\n");
        }
        fileScanner.close();

        // Build a hashmap that maps from metaphone to word list
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        Scanner scanner = new Scanner(fileContents.toString());
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine().trim();
            String metaphone = SimpleMetaphone.simpleMetaphone(word);

            // Add the pair metaphone, word to the hashtable. The
            // hash table should have the metaphone as key and
            // all of the words that map to the same metaphone
            // in the list paired with the metaphone key.

            if (map.getOrDefault(metaphone, null) == null) {
                // Key is not found, so you have to create a new entry
                // with a list of just one word
                // Key: metaphone, Value: list of 'word'
                ArrayList<String> list = new ArrayList<>();
                list.add(word);
                map.put(metaphone, list);
            } else { // metaphone is already on the list
                // "get" the list from the hashmap
                ArrayList<String> list = map.get(metaphone);
                // add a new word to that list
                list.add(word);
                // put the list back in the hash table at key: metaphone
                map.put(metaphone, list);
            }
        }
        scanner.close();

        // Now, print all the entries that have more than 10 words tied to
        // a single metaphone and find the metaphone with the most words
        int max = -1;
        String largestMap = "";
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            String mp = entry.getKey();
            ArrayList<String> list = entry.getValue();
            if (list.size() > max) {
                max = list.size();
                largestMap = mp;
            }

            if (list.size() > 18)
                System.out.println("Metaphone: " + mp + " has " + list.size() + " words.");
        }

        System.out.println("\nMetaphone with the longest mapping: " + largestMap + " with " + max + " words");
        ArrayList<String> words = map.get(largestMap);
        for (String w : words) {
            System.out.println("-> " + w);
        }
    }
}
