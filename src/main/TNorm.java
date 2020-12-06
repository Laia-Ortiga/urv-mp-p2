package main;

import java.util.function.BiFunction;

/**
 * Class that defines a triangular norm.
 */
public class TNorm {
    private final BiFunction<Double, Double, Double> conjunction;

    public TNorm(BiFunction<Double, Double, Double> conjunction) {
        this.conjunction = conjunction;
    }

    public double apply(double x, double y) {
        return conjunction.apply(x, y);
    }
}
