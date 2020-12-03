package main;

public class FuzzyInteger implements Comparable<FuzzyInteger> {

    private final int value;
    private double membership;

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

    public void setMembership(double newMembership) { this.membership = newMembership; }

    @Override
    public int compareTo(FuzzyInteger o) {
        return Integer.compare(value, o.value);
    }
}
