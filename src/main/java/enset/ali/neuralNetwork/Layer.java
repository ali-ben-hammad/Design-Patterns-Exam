package enset.ali.neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<Neuron> neurons = new ArrayList<>();

    public void addNeuron(Neuron neuron) {
        neurons.add(neuron);
    }
}