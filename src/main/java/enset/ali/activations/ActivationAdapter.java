package enset.ali.activations;

public class ActivationAdapter implements ActivationFunction {
    private LegacyActivation legacyFunction;

    public ActivationAdapter(LegacyActivation legacyFunction) {
        this.legacyFunction = legacyFunction;
    }

    @Override
    public double activate(double input) {
        return legacyFunction.compute(input);
    }
}