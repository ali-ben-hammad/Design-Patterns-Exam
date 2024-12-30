package enset.ali.neuralNetwork;

import enset.ali.activations.ActivationFunction;

import java.util.ArrayList;
import java.util.List;

public class Neuron {
    private List<Connection> inputConnections = new ArrayList<>();
    private ActivationFunction activationFunction;

    public Neuron(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public void addInputConnection(Connection connection) {
        inputConnections.add(connection);
    }

    public double compute() {
        double sum = inputConnections.stream()
            .mapToDouble(Connection::getWeightedInput)
            .sum();
        return activationFunction.activate(sum);
    }
}