package main;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
        testListSetContains();

        testListSetContainsAllRetainsAll();

        testListSetRemove();

        testListSetRemoveAll();

        testListSetAddAll();


        testListFuzzySetRemove();

        testListFuzzySetRemoveAll();

        testListFuzzySetContains();

        testListFuzzySetContainsAll();

        testListFuzzySetRetainAll();

        testListFuzzySetAddAll();
    }

    /**
     * Prints the given string underscored.
     *
     * @param title String to underscore
     */
    static void printTitle(String title) {
        System.out.println("\n" + title);
        System.out.println("―".repeat(title.length()));
    }

    static void testListSetContains() {
        printTitle("ListSet@contains");

        ListSet a = new ListSet();
        a.add(3);
        a.add(5);
        a.add(6);
        System.out.println("The set A contains 6: " + a.contains(6));
        System.out.println("The set A contains 9 before insertion: " + a.contains(9));
        a.add(9);
        System.out.println("The set A contains 9 after insertion: " + a.contains(9));
        a.add(10);
        a.add(30);
    }

    static void testListSetContainsAllRetainsAll() {
        printTitle("ListSet@containsAll & ListSet@retainAll");
        System.out.println("B ⊆ A and A := A ∩ B");

        ListSet a = new ListSet();
        ListSet b = new ListSet();
        a.add(3);
        a.add(5);
        a.add(6);

        b.add(5);
        System.out.println("The set A contains B as a subset: " + a.containsAll(b) + "\n");

        System.out.println("Calculating the intersection of A " + a + " and B " + b + " returns the boolean: ");
        System.out.println(a.retainAll(b));
        System.out.println("\nAfter the operation the set A is " + a + "\n");

        b.add(6);
        System.out.println("B is now " + b);
        System.out.println("The set A contains B as a subset: " + a.containsAll(b) + "\n");

        a.add(3);
        a.add(5);
        a.add(6);

        System.out.println("Calculating the intersection of A " + a + " and B " + b + " returns the boolean: ");
        System.out.println(a.retainAll(b));
        System.out.println("\nAfter the operation the set A is " + a + "\n");

        b.add(7);
        b.add(33);
        System.out.println("B is now " + b);
        System.out.println("The set A contains B as a subset: " + a.containsAll(b) + "\n");

        a.add(3);
        a.add(5);
        a.add(6);

        System.out.println("Calculating the intersection of A " + a + " and B " + b + " returns the boolean: ");
        System.out.println(a.retainAll(b));
        System.out.println("\nAfter the operation the set A is " + a + "\n");
    }

    static void testListSetRemove() {
        printTitle("ListSet@remove");
        System.out.println("A: A\\{a}");
        int numberToTest = 2;

        ListSet a = new ListSet();
        a.add(1);
        a.add(3);
        a.add(numberToTest);

        System.out.println("ListSet A: " + a + ", number to delete: " + numberToTest);
        System.out.println("Result: A has" + (a.remove(numberToTest) ? "" : "n't") + " changed");
        System.out.println("ListSet A: " + a + "\n");

        System.out.println("ListSet A: " + a + ", number to delete: " + numberToTest);
        System.out.println("Result: A has" + (a.remove(numberToTest) ? "" : "n't") + " changed");
        System.out.println("ListSet A: " + a + "\n");
    }

    static void testListSetRemoveAll() {
        printTitle("ListSet@removeAll");
        System.out.println("A: A\\B");

        ListSet a = new ListSet();
        ListSet b = new ListSet();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        b.add(2);
        b.add(4);

        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: A has" + (a.removeAll(b) ? "" : "n't") + " changed");
        System.out.println("ListSet A: " + a + "\n");

        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: A has" + (a.removeAll(b) ? "" : "n't") + " changed");
        System.out.println("ListSet A: " + a + "\n");
    }

    static void testListSetAddAll() {
        printTitle("ListSet@addAll");
        System.out.println("A: A ∪ B");

        ListSet a = new ListSet();
        ListSet b = new ListSet();
        a.add(0);
        a.add(1);
        a.add(2);
        a.add(4);
        b.add(3);
        b.add(4);
        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: A has" + (a.addAll(b) ? "" : "n't") + " changed");
        System.out.println("ListSet A: " + a + "\n");
        a.clear();
        b.clear();

        a.add(0);
        a.add(1);
        b.add(0);
        b.add(1);
        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: A has" + (a.addAll(b) ? "" : "n't") + " changed");
        System.out.println("ListSet A: " + a + "\n");
    }


    static void testListFuzzySetRemove() {
        printTitle("ListFuzzySet@remove");
        System.out.println("A: A\\{a}");


        FuzzyInteger numberToTest = new FuzzyInteger(2,  1);
        double previousMembershipResult;

        ListFuzzySet a = new ListFuzzySet();
        a.add(new FuzzyInteger(1, 0.5));
        a.add(new FuzzyInteger(2, 0.5));
        a.add(new FuzzyInteger(3, 0.7));

        System.out.println("ListSet A: " + a + ", number to delete: " + numberToTest);
        previousMembershipResult = a.remove(numberToTest);
        System.out.println("Result: A has" + (previousMembershipResult != 0.0 ? "" : "n't") + " changed");
        System.out.println("Membership from removed element equals to: " + previousMembershipResult);
        System.out.println("ListSet A: " + a + "\n");

        System.out.println("ListSet A: " + a + ", number to delete: " + numberToTest);
        System.out.println("Result: A has" + (a.remove(numberToTest) != 0.0 ? "" : "n't") + " changed");
        System.out.println("ListSet A: " + a + "\n");
    }

    static void testListFuzzySetRemoveAll() {
        printTitle("ListFuzzySet@removeAll");
        System.out.println("A: A\\B");

        ListFuzzySet a = new ListFuzzySet();
        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(2, 0.7));
        a.add(new FuzzyInteger(3, 0.1));
        ListFuzzySet b = new ListFuzzySet();
        b.add(new FuzzyInteger(2, 0.4));

        System.out.println("ListSet A: " + a + ", numbers to delete: " + b);
        double minimumMembershipFromDeletedValues = a.removeAll(b);
        System.out.println("Result: A has" + (minimumMembershipFromDeletedValues != 0.0 ? "" : "n't") + " changed");
        System.out.println("Membership from removed element equals to: " + minimumMembershipFromDeletedValues);
        System.out.println("ListSet A: " + a + "\n");
    }

    static void testListFuzzySetContains() {
        printTitle("ListFuzzySet@contains");
        System.out.println("a ∊ A");

        ListFuzzySet z = new ListFuzzySet();
        z.add(new FuzzyInteger(3, 0.1));
        z.add(new FuzzyInteger(5, 0.9));
        z.add(new FuzzyInteger(7, 0.3));
        System.out.println("A: "+z);
        FuzzyInteger w1 = new FuzzyInteger(3, 0.05);
        System.out.println("a: "+ w1);
        double prova1 = z.contains(w1);
        System.out.println(prova1);
        FuzzyInteger w2 = new FuzzyInteger(3, 0.95);
        System.out.println("a: "+ w2);
        double prova2 = z.contains(w2);
        System.out.println(prova2);
        FuzzyInteger w3 = new FuzzyInteger(8, 0.1);
        System.out.println("a: "+ w3);
        double prova3 = z.contains(w3);
        System.out.println(prova3);
    }

    static void testListFuzzySetContainsAll() {

        printTitle("ListFuzzySet@containsAll");
        System.out.println("B ⊆ A");

        ListFuzzySet z = new ListFuzzySet();
        z.add(new FuzzyInteger(3, 0.1));
        z.add(new FuzzyInteger(5, 0.9));
        z.add(new FuzzyInteger(7, 0.3));
        System.out.println("A: "+ z);
        ListFuzzySet w = new ListFuzzySet();
        w.add(new FuzzyInteger(3, 0.01));
        w.add(new FuzzyInteger(5, 0.01));
        w.add(new FuzzyInteger(7, 0.5));
        System.out.println("B: "+ w);
        double prova1 = z.containsAll(w);
        System.out.println(prova1);
        w.clear();
        w.add(new FuzzyInteger(3, 0.01));
        w.add(new FuzzyInteger(5, 0.01));
        w.add(new FuzzyInteger(7, 0.2));
        System.out.println("B: "+ w);
        double prova2 = z.containsAll(w);
        System.out.println(prova2);
        w.clear();
        w.add(new FuzzyInteger(3, 0.1));
        w.add(new FuzzyInteger(5, 0.9));
        w.add(new FuzzyInteger(7, 0.3));
        System.out.println("B: "+ w);
        double prova3 = z.containsAll(w);
        System.out.println(prova3);
        w.clear();
        w.add(new FuzzyInteger(3, 0.1));
        w.add(new FuzzyInteger(7, 0.3));
        System.out.println("B: "+ w);
        double prova4 = z.containsAll(w);
        System.out.println(prova4);
        w.clear();
        System.out.println("B: "+ w);
        double prova5 = z.containsAll(w);
        System.out.println(prova5);
        z.clear();
        System.out.println("A: "+ z);
        System.out.println("B: "+ w);
        double prova6 = z.containsAll(w);
        System.out.println(prova6);
        z.add(new FuzzyInteger(3, 0.1));
        z.add(new FuzzyInteger(4, 0.75));
        w.add(new FuzzyInteger(4, 0.75));
        System.out.println("A: "+ z);
        System.out.println("B: "+ w);
        double prova7 = z.containsAll(w);
        System.out.println(prova7);
    }

    static void testListFuzzySetRetainAll() {
        printTitle("ListFuzzySet@retainAll");
        System.out.println("A := A ∩ B");

        ListFuzzySet a = new ListFuzzySet();
        ListFuzzySet b = new ListFuzzySet();
        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(5, 0.5));
        a.add(new FuzzyInteger(3, 0.3));
        a.add(new FuzzyInteger(6, 0.6));
        b.add(new FuzzyInteger(3, 0.2));
        b.add(new FuzzyInteger(4, 0.4));
        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: " + a.retainAll(b));
        System.out.println("ListSet A: " + a + "\n");
        a.clear();
        b.clear();

        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(5, 0.5));
        a.add(new FuzzyInteger(3, 0.3));
        a.add(new FuzzyInteger(6, 0.6));
        b.add(new FuzzyInteger(4, 0.4));
        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: " + a.retainAll(b));
        System.out.println("ListSet A: " + a + "\n");
        a.clear();
        b.clear();

        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(5, 0.5));
        a.add(new FuzzyInteger(3, 0.3));
        a.add(new FuzzyInteger(6, 0.6));
        b.add(new FuzzyInteger(3, 0.2));
        b.add(new FuzzyInteger(5, 0.5));
        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: " + a.retainAll(b));
        System.out.println("ListSet A: " + a + "\n");
        a.clear();
        b.clear();

        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(5, 0.5));
        a.add(new FuzzyInteger(3, 0.3));
        a.add(new FuzzyInteger(6, 0.6));
        b.add(new FuzzyInteger(3, 0.3));
        b.add(new FuzzyInteger(5, 0.5));
        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: " + a.retainAll(b));
        System.out.println("ListSet A: " + a + "\n");

        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(5, 0.5));
        a.add(new FuzzyInteger(3, 0.3));
        a.add(new FuzzyInteger(6, 0.6));
        b.add(new FuzzyInteger(1, 0.2));
        b.add(new FuzzyInteger(5, 0.6));
        b.add(new FuzzyInteger(3, 0.4));
        b.add(new FuzzyInteger(6, 0.7));
        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: " + a.retainAll(b));
        System.out.println("ListSet A: " + a + "\n");
        a.clear();
        b.clear();

        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(5, 0.5));
        a.add(new FuzzyInteger(3, 0.3));
        a.add(new FuzzyInteger(6, 0.6));
        b.add(new FuzzyInteger(1, 0.1));
        b.add(new FuzzyInteger(5, 0.5));
        b.add(new FuzzyInteger(3, 0.3));
        b.add(new FuzzyInteger(6, 0.6));
        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: " + a.retainAll(b));
        System.out.println("ListSet A: " + a + "\n");
        a.clear();
        b.clear();
    }

    static void testListFuzzySetAddAll() {
        printTitle("ListFuzzySet@addAll");
        System.out.println("A: A ∪ B");

        ListFuzzySet a = new ListFuzzySet();
        ListFuzzySet b = new ListFuzzySet();
        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(5, 0.5));
        a.add(new FuzzyInteger(3, 0.3));
        a.add(new FuzzyInteger(6, 0.6));
        b.add(new FuzzyInteger(2, 0.2));
        b.add(new FuzzyInteger(4, 0.4));
        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: " + a.addAll(b));
        System.out.println("ListSet A: " + a + "\n");

        a = new ListFuzzySet();
        b = new ListFuzzySet();

        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(2, 0.2));
        b.add(new FuzzyInteger(1, 0.1));
        b.add(new FuzzyInteger(2, 0.4));
        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: " + a.addAll(b));
        System.out.println("ListSet A: " + a + "\n");

        a = new ListFuzzySet();
        b = new ListFuzzySet();

        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(2, 0.4));
        b.add(new FuzzyInteger(1, 0.1));
        b.add(new FuzzyInteger(2, 0.2));
        System.out.println("ListSet A: " + a + ", ListSet B: " + b);
        System.out.println("Result: " + a.addAll(b));
        System.out.println("ListSet A: " + a + "\n");
    }
}
