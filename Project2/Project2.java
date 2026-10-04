
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import itsc2214.*;

/**
 * Project2 extends from Document, creates a list of Strings based on dictonary.
 */
public class Project2 extends Document {

    /** File name. */
    private String fileName;

    /** Contexts of doc. */
    private String contents;

    /** Data structures to store the file contents. */
    private ListADT<String> docWords;
    private BagADT<String> misspelledWords;
    private SetADT<String> dictionary;

    /** Unqiue words in doc. */
    private SetADT<String> uniqueWords;
    private int uniqueCount;

    /**
     * Default constructor for the class, creates the initial data structures (bag,
     * set, and list), and initializes everything as if it had an empty document by
     * calling loadFromString("").
     */
    public Project2() {
        fileName = "";
        contents = "";
        dictionary = new SetArrayList<String>();
        loadDictionary();
        loadFromString("");
    }

    /**
     * Loads dictionary.txt into set, all as lowercase.
     * If the file is missing the dictionary will be empty.
     */
    private void loadDictionary() {
        try {
            java.util.List<String> lines = Files.readAllLines(Paths.get("dictionary.txt"));
            for (String line : lines) {
                String l = line.trim().toLowerCase();
                if (l.length() > 0) {
                    dictionary.add(l);
                }
            }
        } catch (IOException e) {
            // leaves dictonary empty
            System.out.println("Dictonary left empty");
        }
    }

    /**
     * Returns the fileName as passed in the first argument of the constructor. If
     * there is no file name, it just return an empty string.
     * 
     * @return the name of the file used
     */
    @Override
    public String getFilename() {
        if (fileName == null) {
            return "";
        }
        return fileName;
    }

    /**
     * This routine should open the file named in the argument, load the full
     * content into a string and then call the loadFromString() method to process
     * the string. This routine captures a FileNotFoundException and simply call
     * loadFromString("") with an empty string. This should initialize the data
     * structures to have no words. In effect, a file not found should behave
     * identically to an empty file. It returns true if it was able to load the
     * file, or false if an error occurred. Either way, the content of the class
     * (fileName, data structures, etc.) should be left in a usable state (possibly
     * with no content).
     * 
     * @param fileName is the name of the file to read in
     * @return true if the file is processed correctly, false otherwise.
     */
    public boolean loadFromFile(String fileName) {
        try {
            this.fileName = fileName;
            java.util.List<String> lines = Files.readAllLines(Paths.get(fileName));
            StringBuilder k = new StringBuilder();
            for (String line : lines) {
                k.append(line).append(" ");
            }

            return loadFromString(k.toString().trim());
        } catch (IOException e) {
            loadFromString("");
            return false;
        }
    }

    /**
     * This method breaks a string into tokens separated by white space (blank, tab,
     * newlines) and by punctuations (anything that is not a letter or a digit). The
     * non-alphanumeric characters are used as separator and ignored. The remaining
     * tokens (words) should be processed by calling addWord(word) (see below). It
     * returns true unless some unexpected situation is found in which case it
     * should return false.
     * 
     * @param data is the string being processed
     * @return true if the string is processed correctly, false otherwise.
     */
    public boolean loadFromString(String data) {
        try {
            docWords = new ListArray<String>();
            misspelledWords = new BagArrayList<String>();
            uniqueWords = new SetArrayList<String>();
            uniqueCount = 0;

            if (data == null) {
                contents = "";
                return true;
            }

            contents = data;
            String[] tokens = data.split("[^\\p{L}\\p{N}]+");
            for (String token : tokens) {
                addWord(token);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method implements the algorithm to add the word to the list and the
     * mispelled words. The steps are as follows:
     * - If the string w is null, do nothing.
     * - Trim (trim()) the string and turn it into lower case.
     * - If the string is empty (length 0), or its length is less than 3 characters,
     * do nothing.
     * - Add the w to the end of the list of words in the document. Then your code
     * should check if the word is mispelled or not by looking it up in the
     * dictionary. If it does not exits in the dictionary (i.e. it is mispelled),
     * store it in the bag of mispelled words.
     * 
     * @param w word to be added to the list
     */
    public void addWord(String w) {
        if (w == null) {
            return;
        }
        String word = w.trim().toLowerCase();
        if (word.length() < 3) {
            return;
        }
        docWords.add(word);

        if (!uniqueWords.contains(word)) {
            uniqueWords.add(word);
            uniqueCount++;
        }
        if (!dictionary.contains(word)) {
            misspelledWords.add(word);
        }
    }

    /**
     * This method returns the total number of words, not just unique ones. So it
     * returns the size of the bag that contains the contents of the file.
     * 
     * @return count of total number of words
     */
    public int numWords() {
        return docWords.size();
    }

    /**
     * This method returns the number of unique words in the document. You have to
     * keep track of this number in the addWords() method. 
     * 
     * @return count of unique words
     */
    public int numUniqueWords() {
        return uniqueCount;
    }

    /**
     * Returns a bag of misspelled words from the document.
     * 
     * @return a BagADT with the misspelled words
     */
    public BagADT<String> getMisspelledWords() {
        return misspelledWords;
    }

    /**
     * This is not needed for the project but must be implemented.
     *
     * Returns the full contents of the file in a String object.
     * 
     * @return string representation of the file.
     */

    public String getContents() {
        return contents;
    }

    /**
     * This is not needed for the project but must be implemented.
     *
     * Returns the number of syllabus in the contents of
     * the file. One way to compute it is to iterate over
     * all the words in the file and compute the number of
     * syllables of each word, calling countSyllables(),
     * and accumulating the counts to get to the total number
     * of syllables in the document.
     * 
     * @return number of syllables in the document
     */
    public int numOfSyllables() {
        int total = 0;
        for (int i = 0; i < docWords.size(); i++) {
            total += countSyllables(docWords.get(i));
        }
        return total;
    }

    /**
     * This is not needed for the project but must be implemented.
     *
     * Returns the number of polysyllabic words in the
     * document. This needs to be computed when called and
     * not saved, as it depends on how many syllables should
     * be used to count as polysyllabic. This returns the
     * number of words whose number of syllables is larger
     * or equal to parameter x.
     * 
     * @param arg0 threshold for syllables to count as polysyllabic
     * @return number of words that have less than or equal x syllables
     */
    public int numOfPolySyllabic(int arg0) {
        int count = 0;
        for (int i = 0; i < docWords.size(); i++) {
            if (countSyllables(docWords.get(i)) >= arg0) {
                count++;
            }
        }
        return count;
    }
}
