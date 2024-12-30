package enset.ali.networkState;
import enset.ali.neuralNetwork.NeuralNetwork;

public class ConstructionState implements NetworkState {
    @Override
    public void train(NeuralNetwork network) {
        network.setState(new TrainingState());
        System.out.println("Starting training...");
    }

    @Override
    public void fit(NeuralNetwork network) {
        network.setState(new TrainedState());
        System.out.println("Direct fit to trained state");
    }

    @Override
    public void predict(NeuralNetwork network, double[] input) {
        throw new IllegalStateException("Cannot predict in construction state");
    }
}