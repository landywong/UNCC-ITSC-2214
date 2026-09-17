import java.util.Random;

public class PlaylistShufflerArray {
    public static void main(String[] args) {
        String[] playlist = { "Song A", "Song B", "Song C", "Song D", "Song E" };

        // Prints original playlist
        System.out.println("Original playlist order:");
        for (int i = 0; i < playlist.length; i++) {
            System.out.println(playlist[i]);
        }

        shuffle(playlist);

        // Prints shuffled playlist
        System.out.println("Shuffled playlist order:");
        for (int i = 0; i < playlist.length; i++) {
            System.out.println(playlist[i]);
        }
    }

    // Implements an in-place shuffle algorithm without using Collections.shuffle()
    public static void shuffle(String[] array) {
        Random rand = new Random();

        String[] order = new String[array.length];
        boolean[] used = new boolean[array.length];
        int filled = 0;
        int x = rand.nextInt(array.length);

        while (filled < array.length) {
            if (!used[x]) {
                order[filled] = array[x];
                used[x] = true;
                filled++;
            }
            x = rand.nextInt(array.length);
        }

        // copy the new order back into the original array in place
        for (int i = 0; i < array.length; i++) {
            array[i] = order[i];
        }
    }
}
