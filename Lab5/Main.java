import itsc2214.*;
public class Main {
    public static void main(String[] args) {
        integerExample();
        // stringExample();
    }

    /**
     * This is an example of a generic method. This method can work
     * on any type of E. Note that the generic is defined for the
     * method alone and not the full class.
     * 
     * @param <E> generic parameter
     * @param msg message to be printed
     * @param s set of values to be printed
     */
    static <E> void print(String msg, SetADT<E> s)
    {
        System.out.println(msg+" has "+s.size()+" elements");
        System.out.print("  ");
        for (E u : s) {
            System.out.print(u+" ");
        }
        System.out.println("");
    }

    /**
     * Example with integers.
     */
    static void integerExample()
    {
        Integer[] first = {10, 20, 30, 40, 50};
        Integer[] second = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};

        SetOps<Integer> ops = new SetOps<Integer>();

        SetADT<Integer> aSet= Factory.makeSetArrayList();
        for (Integer h : first) {
            aSet.add(h);
        }

        SetADT<Integer> bSet = Factory.makeSetArrayList();
        for (Integer g : second) {
            bSet.add(g);
        }

        print("A set", aSet);
        print("B set", bSet);
        print("A ∪ B", ops.union(aSet, bSet));
        print("A ∩ B", ops.intersection(aSet, bSet));
        print("A - B", ops.difference(aSet, bSet));
        print("B - A", ops.difference(bSet, aSet));
        print("symmetric diff A, B", ops.symmetricDifference(aSet, bSet));
        print("symmetric diff B, A", ops.symmetricDifference(bSet, aSet));
    }

    /**
     * Example with Strings.
     */
    static void stringExample()
    {
        String[] first = {
            "Hola", "Bonjour", "Guten Tag, Hallo", "Ciao, Salve", "Konnichiwa",
            "你好 (Nǐ hǎo)", "Здравствуйте (Zdravstvuyte)", "नमस्ते (Namaste)", 
            "مرحباً (Marhabaan)", "안녕하세요 (Annyeonghaseyo)", "Olá", "Γεια (Ya)", 
            "שלום (Shalom)", "Merhaba", "Jambo", "Xin chào"
        };

        String[] second = {
            "Goodbye", "Bye", "Farewell", "Au Revoir", "Adios", "Arrivederci", 
            "Ciao", "Adeus", "Auf Wiedersehen", "Do svidaniya", "Zai Jian", 
            "Sayonara", "Annyeong", "Yasou", "Tot ziens", "Żegnaj", "Güle güle", 
            "Alvida", "Ma'a as-salaama", "Shalom", "Viszlát", "Sbohem", 
            "Selamat tinggal", "Tạm biệt"
        };
        SetOps<String> ops = new SetOps<String>();

        SetADT<String> aSet= Factory.makeSetArrayList();
        for (String h : first) {
            aSet.add(h);
        }

        SetADT<String> bSet = Factory.makeSetArrayList();
        for (String g : second) {
            bSet.add(g);
        }

        print("A set", aSet);
        print("B set", bSet);
        print("A ∪ B", ops.union(aSet, bSet));
        print("A ∩ B", ops.intersection(aSet, bSet));
        print("A - B", ops.difference(aSet, bSet));
        print("B - A", ops.difference(bSet, aSet));
        print("symmetric diff A, B", ops.symmetricDifference(aSet, bSet));
        print("symmetric diff B, A", ops.symmetricDifference(bSet, aSet));

    }
}
/*
 * Copyright: This programming assignment specification and the
 * provided sample code are protected by copyright. The professor
 * is the exclusive owner of copyright of this material. You are
 * encouraged to take notes and make copies of the specification
 * and the source code for your own educational use. However, you
 * may not, nor may you knowingly allow others to reproduce or
 * distribute the materials publicly without the express written
 * consent of the professor. This includes providing materials to
 * commercial course material suppliers such as CourseHero and
 * other similar services. Students who publicly distribute or
 * display or help others publicly distribute or display copies or
 * modified copies of this material may be in violation of
 * University Policy 406, The Code of Student Responsibility
 * https://legal.uncc.edu/policies/up-406.
 */
