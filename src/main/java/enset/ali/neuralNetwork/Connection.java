package enset.ali.neuralNetwork;

public class Connection {
    private Neuron source;
    private Neuron destination;
    private double weight;

    public Connection(Neuron source, Neuron destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public double getWeightedInput() {
        return weight * source.compute();
    }
}