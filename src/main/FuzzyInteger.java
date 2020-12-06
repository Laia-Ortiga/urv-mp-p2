package main;

/**
 * Class that defines a FuzzyInteger encapsulating two values corresponding to a value and a membership
 * so as to be stored together as elements of a list.
 */
public class FuzzyInteger implements Comparable<FuzzyInteger> {

    private final int value;
    private final double membership;

    public FuzzyInteger(int value, double membership) {
        this.value = value;
        this.membership = membership;
    }

    public int getValue() {
        return value;
    }

    public double getMembership() {
        return membership;
    }

    /**
     * Method that compares two fuzzy integers only taking into account its value.
     * @param o fuzzy integer to be compared with
     * @return 1 if this fuzzy integer value is less than o value, 0 if they are equal, -1 otherwise
     */
    @Override
    public int compareTo(FuzzyInteger o) {
        return Integer.compare(value, o.value);
    }

    /**
     * String containing both the value and the membership of this fuzzy integer.
     * @return String containing both the value and the membership of this fuzzy integer
     */
    @Override
    public String toString() {
        return "{" + value + ", " + membership + "}";
    }
}
