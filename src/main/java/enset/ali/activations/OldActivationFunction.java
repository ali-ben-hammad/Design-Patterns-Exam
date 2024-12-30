package enset.ali.activations;

public class OldActivationFunction implements LegacyActivation {
    @Override
    public double compute(double value) {
        return Math.tanh(value);
    }
}