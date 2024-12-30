package enset.ali.activations;

public class Sigmoid implements ActivationFunction {
    @Override
    public double activate(double input) {
        return 1 / (1 + Math.exp(-input));
    }
}

