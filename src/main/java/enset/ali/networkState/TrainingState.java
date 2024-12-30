package enset.ali.networkState;
import enset.ali.neuralNetwork.NeuralNetwork;

public class TrainingState implements NetworkState {
    @Override
    public void train(NeuralNetwork network) {
        System.out.println("Already training");
    }

    @Override
    public void fit(NeuralNetwork network) {
        throw new IllegalStateException("Cannot fit while training");
    }

    @Override
    public void predict(NeuralNetwork network, double[] input) {
        throw new IllegalStateException("Cannot predict while training");
    }

    public void completeTraining(NeuralNetwork network) {
        network.setState(new TrainedState());
    }
}