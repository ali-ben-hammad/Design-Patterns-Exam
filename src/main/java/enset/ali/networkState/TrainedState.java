package enset.ali.networkState;
import enset.ali.neuralNetwork.NeuralNetwork;

public
class TrainedState implements NetworkState {
    @Override
    public void train(NeuralNetwork network) {
        System.out.println("Already trained");
    }

    @Override
    public void fit(NeuralNetwork network) {
        throw new IllegalStateException("Already trained");
    }

    @Override
    public void predict(NeuralNetwork network, double[] input) {
        // Actual prediction logic would go here
        System.out.println("Predicting...");
    }
}