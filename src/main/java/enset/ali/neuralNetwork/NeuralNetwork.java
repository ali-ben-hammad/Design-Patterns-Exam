package enset.ali.neuralNetwork;

import enset.ali.networkState.ConstructionState;
import enset.ali.networkState.NetworkState;
import enset.ali.observer.NetworkObserver;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private List<Layer> layers = new ArrayList<>();
    private NetworkState state;
    private List<NetworkObserver> observers = new ArrayList<>();

    private NeuralNetwork() {
        this.state = new ConstructionState();
    }

    public void setState(NetworkState state) {
        this.state = state;
        notifyObservers();
    }

    public NetworkState getState() {
        return this.state;
    }

    public void addObserver(NetworkObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        double mockAccuracy = 0.95; // Mock accuracy for demonstration
        for (NetworkObserver observer : observers) {
            observer.update(state.getClass().getSimpleName(), mockAccuracy);
        }
    }

    public void train() {
        state.train(this);
    }

    public void fit() {
        state.fit(this);
    }

    public void predict(double[] input) {
        state.predict(this, input);
    }

    // Builder
    public static class Builder {
        private NeuralNetwork network;

        public Builder() {
            network = new NeuralNetwork();
        }

        public Builder addLayer(Layer layer) {
            network.layers.add(layer);
            return this;
        }

        public NeuralNetwork build() {
            if (network.layers.size() < 3) {
                throw new IllegalStateException("Network must have at least 3 layers");
            }
            return network;
        }
    }
}