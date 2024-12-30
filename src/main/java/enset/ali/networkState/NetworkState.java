package enset.ali.networkState;

import enset.ali.neuralNetwork.NeuralNetwork;

public interface NetworkState {
    void train(NeuralNetwork network);
    void fit(NeuralNetwork network);
    void predict(NeuralNetwork network, double[] input);
}