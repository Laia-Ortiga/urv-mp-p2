package main;

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

    @Override
    public int compareTo(FuzzyInteger o) {
        return Integer.compare(value, o.value);
    }

    @Override
    public String toString() {
        return "{" + value + ", " + membership + "}";
    }
}
