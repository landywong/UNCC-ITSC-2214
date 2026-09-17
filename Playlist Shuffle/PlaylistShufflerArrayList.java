import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaylistShufflerArrayList {
    public static void main(String[] args) {
        List<String> playlist = new ArrayList<>();
        playlist.add("Song A");
        playlist.add("Song B");
        playlist.add("Song C");
        playlist.add("Song D");
        playlist.add("Song E");

        // Prints original playlist
        System.out.println("Original playlist order:");
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println(playlist.get(i));
        }

        shuffle(playlist);

        // Prints shuffled playlist
        System.out.println("Shuffled playlist order:");
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println(playlist.get(i));
        }
    }

    // An in-place shuffle algorithm without using Collections.shuffle()
    public static void shuffle(List<String> list) {
        Random rand = new Random();

        // Fischer-Yates shuffle
        for (int i = list.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }
}
