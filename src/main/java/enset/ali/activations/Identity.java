package enset.ali.activations;

public class Identity implements ActivationFunction {
    @Override
    public double activate(double input) {
        return input;
    }
}