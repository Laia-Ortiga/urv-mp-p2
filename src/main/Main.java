package main;

import java.sql.SQLOutput;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        printTitle("contains method");
        ListSet a = new ListSet();
        a.add(3);
        a.add(5);
        a.add(6);
        System.out.println("The set a contains 6: " + a.contains(6));
        System.out.println("The set a contains 9 before insertion: " + a.contains(9));
        a.add(9);
        System.out.println("The set a contains 9 after insertion: " + a.contains(9));
        a.add(10);
        a.add(30);
        ListSet b = new ListSet();

        a.add(3);
        a.add(5);
        a.add(6);

        b.add(5);

        printTitle("containsAll method");
        System.out.println("The set a contains b as a subset: "+ a.containsAll(b) +"\n");

        System.out.println("Calculating the intersection of a " + a + " and b " + b+ " returns the boolean: ");
        System.out.println(a.retainAll(b));
        System.out.println("\nAfter the operation the set a is "+ a + "\n");

        a.add(3);
        a.add(5);
        a.add(6);

        System.out.println("Calculating the intersection of a " + a + " and b " + b+ " returns the boolean: ");
        System.out.println(a.retainAll(b));
        System.out.println("\nAfter the operation the set a is "+ a + "\n");

        b.add(6);
        System.out.println("The set a contains b as a subset: "+ a.containsAll(b) +"\n");

        a.add(3);
        a.add(5);
        a.add(6);

        System.out.println("Calculating the intersection of a " + a + " and b " + b+ " returns the boolean: ");
        System.out.println(a.retainAll(b));
        System.out.println("\nAfter the operation the set a is "+ a + "\n");

        b.add(7);
        b.add(33);
        System.out.println("The set a contains b as a subset: "+ a.containsAll(b) +"\n");

        a.add(3);
        a.add(5);
        a.add(6);

        System.out.println("Calculating the intersection of a " + a + " and b " + b+ " returns the boolean: ");
        System.out.println(a.retainAll(b));
        System.out.println("\nAfter the operation the set a is "+ a + "\n");
        a.removeAll(a);
        b.removeAll(b);

        // addAll
        printTitle("addAll method");
        a.add(0);
        a.add(1);
        a.add(2);
        a.add(4);
        b.add(3);
        b.add(4);
        System.out.println("a := a ∪ b");
        System.out.println("ListSet a: " + a + ", ListSet b: "+ b);
        System.out.println("Result: a has" + (a.addAll(b) ? "" : "n't") + " changed");
        System.out.println("ListSet a: " + a + "\n");
        a.removeAll(a);
        b.removeAll(b);

        a.add(0);
        a.add(1);
        b.add(0);
        b.add(1);
        System.out.println("ListSet a: " + a + ", ListSet b: "+ b);
        System.out.println("Result: a has" + (a.addAll(b) ? "" : "n't") + " changed");
        System.out.println("ListSet a: " + a + "\n");



//        ArrayList<Integer> a = new ArrayList<>();
//        a.add(3);
//        a.add(5);
//        a.add(6);
//        a.add(9);
//        a.add(10);
//        a.add(30);
//        ArrayList<Integer> b = new ArrayList<>();
//        b.add(5);
//        b.add(6);
//        b.add(7);
//        b.add(33);
//        ListSet set = new ListSet(a);
//        System.out.println(set);
//        ListSet removalSet = new ListSet(b);
//        set.removeAll(removalSet);
//        System.out.println(set);
//        System.out.println(removalSet);
    }

    /**
     * Prints the given string underscored.
     * @param title String to underscore
     */
    static void printTitle(String title) {
        System.out.println("\n" + title);
        System.out.println("―".repeat(title.length()));
    }
}
