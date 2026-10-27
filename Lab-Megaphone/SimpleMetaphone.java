public class SimpleMetaphone {

    public static String simpleMetaphone(String word) {
        word = word.toLowerCase();

        // Step 1: Drop silent starting letters (remove 1st letter)
        // Simplify silent starting letters: "gn", "pn", "ae", "wr"
        if (word.startsWith("kn")) {
            word = word.substring(1);
        }
        if (word.startsWith("gn")) {
            word = word.substring(1);
        }
        if (word.startsWith("pn")) {
            word = word.substring(1);
        }
        if (word.startsWith("ae")) {
            word = word.substring(1);
        }
        if (word.startsWith("wr")) {
            word = word.substring(1);
        }

        // Step 2: Collapse similar sounding letters
        // TODO: handle all of these ph -> f, ck -> k, gh -> ""
        word = word.replace("ph", "f");
        word = word.replace("ck", "k");
        word = word.replace("gh", "");

        // Step 3: replace some letters for others to simplify processing
        // TODO: handle all of these q -> k, x -> ks, z -> s
        word = word.replace("q", "k");
        word = word.replace("x", "ks");
        word = word.replace("z", "s");

        // Step 4: Replace c depending on following letter
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            if (current == 'c') {
                if (i + 1 < word.length()) {
                    char next = word.charAt(i + 1);
                    // Soft vs hard C
                    if (next == 'e' || next == 'i' || next == 'y') {
                        // Soft c e = cease, celebrity
                        // Soft c i = cider, civil, citrus
                        // Soft c y = cyclone, cylinder, cynic
                        sb.append('s');
                    } else {
                        // Hard c a = cabby, cantina, candid
                        // Hard c o = cohesive, coalesce, incoherent
                        // Hard c u = cutlery, cute, curve
                        // cl, cm, cp, etc.
                        sb.append('k');
                    }
                } else {
                    // C at the end of the word
                    // chronic, panic
                    sb.append('k');
                }
            } else {
                sb.append(current);
            }
        }
        word = sb.toString();

        // Step 5: Drop vowels (except first char)
        sb = new StringBuilder();
        sb.append(word.charAt(0)); // Keep first letter
        for (int i = 1; i < word.length(); i++) {
            char ch = word.charAt(i);
            if ("aeiou".indexOf(ch) == -1) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}