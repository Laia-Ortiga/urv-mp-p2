package main;

public class Main {

    public static void main(String[] args) {
//        testListSetContains();
//
//        testListSetContainsAllRetainsAll();
//
//        testListSetRemove();
//
//        testListSetRemoveAll();
//
//        testListSetRetainAll();
//
//        testListSetAddAll();
//
//
//        testListFuzzySetRemove();
//
        testListFuzzySetRemoveAll();
//
//        testListFuzzySetContains();
//
//        testListFuzzySetContainsAll();
//
//        testListFuzzySetRetainAll();
//
//        testListFuzzySetAddAll();
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
        System.out.println("The set a contains 6: " + a.contains(6));
        System.out.println("The set a contains 9 before insertion: " + a.contains(9));
        a.add(9);
        System.out.println("The set a contains 9 after insertion: " + a.contains(9));
        a.add(10);
        a.add(30);
    }

    static void testListSetContainsAllRetainsAll() {
        printTitle("ListSet@containsAll & ListSet@retainAll");

        ListSet a = new ListSet();
        ListSet b = new ListSet();
        a.add(3);
        a.add(5);
        a.add(6);

        b.add(5);
        System.out.println("The set a contains b as a subset: " + a.containsAll(b) + "\n");

        System.out.println("Calculating the intersection of a " + a + " and b " + b + " returns the boolean: ");
        System.out.println(a.retainAll(b));
        System.out.println("\nAfter the operation the set a is " + a + "\n");

        b.add(6);
        System.out.println("b is now " + b);
        System.out.println("The set a contains b as a subset: " + a.containsAll(b) + "\n");

        a.add(3);
        a.add(5);
        a.add(6);

        System.out.println("Calculating the intersection of a " + a + " and b " + b + " returns the boolean: ");
        System.out.println(a.retainAll(b));
        System.out.println("\nAfter the operation the set a is " + a + "\n");

        b.add(7);
        b.add(33);
        System.out.println("b is now " + b);
        System.out.println("The set a contains b as a subset: " + a.containsAll(b) + "\n");

        a.add(3);
        a.add(5);
        a.add(6);

        System.out.println("Calculating the intersection of a " + a + " and b " + b + " returns the boolean: ");
        System.out.println(a.retainAll(b));
        System.out.println("\nAfter the operation the set a is " + a + "\n");
    }


    static void testListSetRemove() {
        printTitle("ListSet@remove");
        int numberToTest = 2;

        ListSet a = new ListSet();
        a.add(1);
        a.add(3);
        a.add(numberToTest);

        System.out.println("a := a/{x}");
        System.out.println("ListSet a: " + a + ", number to delete: " + numberToTest);
        System.out.println("Result: a has" + (a.remove(numberToTest) ? "" : "n't") + " changed");
        System.out.println("ListSet a: " + a + "\n");

        System.out.println("ListSet a: " + a + ", number to delete: " + numberToTest);
        System.out.println("Result: a has" + (a.remove(numberToTest) ? "" : "n't") + " changed");
        System.out.println("ListSet a: " + a + "\n");
    }

    static void testListSetRemoveAll() {
        printTitle("ListSet@removeAll");

        ListSet a = new ListSet();
        ListSet b = new ListSet();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        b.add(2);
        b.add(4);

        System.out.println("a := a/b");
        System.out.println("ListSet a: " + a + ", ListSet b: " + b);
        System.out.println("Result: a has" + (a.removeAll(b) ? "" : "n't") + " changed");
        System.out.println("ListSet a: " + a + "\n");

        System.out.println("ListSet a: " + a + ", ListSet b: " + b);
        System.out.println("Result: a has" + (a.removeAll(b) ? "" : "n't") + " changed");
        System.out.println("ListSet a: " + a + "\n");
    }

    static void testListSetRetainAll() {
        printTitle("ListSet@retainAll");
    }

    static void testListSetAddAll() {
        printTitle("ListSet@addAll");

        ListSet a = new ListSet();
        ListSet b = new ListSet();
        a.add(0);
        a.add(1);
        a.add(2);
        a.add(4);
        b.add(3);
        b.add(4);
        System.out.println("a := a ∪ b");
        System.out.println("ListSet a: " + a + ", ListSet b: " + b);
        System.out.println("Result: a has" + (a.addAll(b) ? "" : "n't") + " changed");
        System.out.println("ListSet a: " + a + "\n");
        a.clear();
        b.clear();

        a.add(0);
        a.add(1);
        b.add(0);
        b.add(1);
        System.out.println("ListSet a: " + a + ", ListSet b: " + b);
        System.out.println("Result: a has" + (a.addAll(b) ? "" : "n't") + " changed");
        System.out.println("ListSet a: " + a + "\n");
    }


    static void testListFuzzySetRemove() {
        printTitle("ListFuzzySet@remove");
        FuzzyInteger numberToTest = new FuzzyInteger(2,  1);
        double previousMembershipResult;

        ListFuzzySet a = new ListFuzzySet();
        a.add(new FuzzyInteger(1, 0.5));
        a.add(new FuzzyInteger(2, 0.5));
        a.add(new FuzzyInteger(3, 0.7));
        System.out.println("a := a/{x}");
        System.out.println("ListSet a: " + a + ", number to delete: " + numberToTest);
        previousMembershipResult = a.remove(numberToTest);
        System.out.println("Result: a has" + (previousMembershipResult != 0.0 ? "" : "n't") + " changed");
        System.out.println("Membership from removed element equals to: " + previousMembershipResult);
        System.out.println("ListSet a: " + a + "\n");

        System.out.println("ListSet a: " + a + ", number to delete: " + numberToTest);
        System.out.println("Result: a has" + (a.remove(numberToTest) != 0.0 ? "" : "n't") + " changed");
        System.out.println("ListSet a: " + a + "\n");
    }

    static void testListFuzzySetRemoveAll() {
        printTitle("ListFuzzySet@removeAll");

        ListFuzzySet a = new ListFuzzySet();
        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(2, 0.7));
        a.add(new FuzzyInteger(3, 0.1));
        ListFuzzySet b = new ListFuzzySet();
        b.add(new FuzzyInteger(2, 0.4));

        System.out.println("ListSet a: " + a + ", numbers to delete: " + b);
        double minimumMembershipFromDeletedValues = a.removeAll(b);
        System.out.println("Result: a has" + (minimumMembershipFromDeletedValues != 0.0 ? "" : "n't") + " changed");
        System.out.println("Membership from removed element equals to: " + minimumMembershipFromDeletedValues);
        System.out.println("ListSet a: " + a + "\n");
    }

    static void testListFuzzySetContains() {
        printTitle("ListFuzzySet@contains");

        ListFuzzySet z = new ListFuzzySet();
        z.add(new FuzzyInteger(3, 0.1));
        z.add(new FuzzyInteger(5, 0.9));
        z.add(new FuzzyInteger(7, 0.3));
    }

    static void testListFuzzySetContainsAll() {
        printTitle("ListFuzzySet@containsAll");
    }

    static void testListFuzzySetRetainAll() {
        printTitle("ListFuzzySet@retainAll");

        ListFuzzySet a = new ListFuzzySet();
        ListFuzzySet b = new ListFuzzySet();
        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(5, 0.5));
        a.add(new FuzzyInteger(3, 0.3));
        a.add(new FuzzyInteger(6, 0.6));
        b.add(new FuzzyInteger(3, 0.2));
        b.add(new FuzzyInteger(4, 0.4));
        //System.out.println("a := a ∪ b");
        System.out.println("ListSet a: " + a + ", ListSet b: " + b);
        System.out.println("Result: " + a.retainAll(b));
        System.out.println("ListSet a: " + a + "\n");
    }

    static void testListFuzzySetAddAll() {
        printTitle("ListFuzzySet@addAll");

        ListFuzzySet a = new ListFuzzySet();
        ListFuzzySet b = new ListFuzzySet();
        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(5, 0.5));
        a.add(new FuzzyInteger(3, 0.3));
        a.add(new FuzzyInteger(6, 0.6));
        b.add(new FuzzyInteger(2, 0.2));
        b.add(new FuzzyInteger(4, 0.4));
        System.out.println("a := a ∪ b");
        System.out.println("ListSet a: " + a + ", ListSet b: " + b);
        System.out.println("Result: " + a.addAll(b));
        System.out.println("ListSet a: " + a + "\n");

        a = new ListFuzzySet();
        b = new ListFuzzySet();

        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(2, 0.2));
        b.add(new FuzzyInteger(1, 0.1));
        b.add(new FuzzyInteger(2, 0.4));
        System.out.println("ListSet a: " + a + ", ListSet b: " + b);
        System.out.println("Result: " + a.addAll(b));
        System.out.println("ListSet a: " + a + "\n");

        a = new ListFuzzySet();
        b = new ListFuzzySet();

        a.add(new FuzzyInteger(1, 0.1));
        a.add(new FuzzyInteger(2, 0.4));
        b.add(new FuzzyInteger(1, 0.1));
        b.add(new FuzzyInteger(2, 0.2));
        System.out.println("ListSet a: " + a + ", ListSet b: " + b);
        System.out.println("Result: " + a.addAll(b));
        System.out.println("ListSet a: " + a + "\n");
    }
}
