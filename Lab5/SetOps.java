import itsc2214.*;

/**
 * SetOps - set operations class, part of the ITSC 2214 labs.
 * 
 * @param <E> generic parameter
 */
public class SetOps<E> {
    /**
     * The union of two sets, A and B (denoted as A ∪ B),
     * is a set containing all elements that are in either
     * 
     * A or B, or both.
     * - create a new empty set
     * - for each element in set a
     * if not in set b then add it to new set
     * - for each element in set b
     * if not in set a then add it to new set
     * - return the new set
     * 
     * @param a one set
     * @param b another set
     * @return set with the result of the operation
     */

    public SetADT<E> union(SetADT<E> a, SetADT<E> b) {
        SetADT<E> set = Factory.makeSetArrayList();
        for (E e : a) { // A
            set.add(e);
        }
        for (E e : b) { // B
            set.add(e);
        }
        return set;
    }

    /**
     * The intersection of two sets, A and B (denoted
     * as A ∩ B), is a set containing only the elements
     * that are common to both A and B.
     * 
     * - create a new empty set
     * - for each element in set a
     * if element in set b then add it to new set
     * - return the new set
     * 
     * @param a one set
     * @param b another set
     * @return set with the result of the operation
     */

    public SetADT<E> intersection(SetADT<E> a, SetADT<E> b) {
        SetADT<E> set = Factory.makeSetArrayList();
        for (E e : a) { // A
            if (b.contains(e)) { // A & B (intersection)
                set.add(e);
            }
        }
        return set;
    }

    /**
     * The difference of set A and B (denoted as A - B)
     * is a set containing elements that are in A but not in B.
     * 
     * - create a new empty set
     * - for each element in set a
     * if not in set b then add it to new set
     * - return the new set
     * 
     * @param a one set
     * @param b another set
     * @return set with the result of the operation
     */

    public SetADT<E> difference(SetADT<E> a, SetADT<E> b) {
        SetADT<E> set = Factory.makeSetArrayList();
        for (E e : a) { // A
            if (!b.contains(e)) { // checks if exists
                set.add(e); // adds if no exists
            }
        }
        return set;
    }

    /**
     * The symmetric difference of two sets, A and B,
     * includes elements that are in either A or B, but not
     * in both.
     * 
     * @param a one set
     * @param b another set
     * @return set with the result of the operation
     */
    public SetADT<E> symmetricDifference(SetADT<E> a, SetADT<E> b) {
        SetADT<E> set = Factory.makeSetArrayList();
        for (E e : a) { // A
            if (!b.contains(e)) {
                set.add(e);
            }
        }
        for (E e : b) { // B
            if (!a.contains(e)) {
                set.add(e);
            }
        }
        return set;
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
