package enset.ali;

import enset.ali.aspects.RequiresAuthentication;
import enset.ali.neuralNetwork.Layer;
import enset.ali.neuralNetwork.NeuralNetwork;
import enset.ali.neuralNetwork.Neuron;
import enset.ali.activations.*;
import enset.ali.networkState.TrainingState;
import enset.ali.observer.Dashboard;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "enset.ali")
public class App {

    @RequiresAuthentication
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(App.class)) {

            System.out.println("\n🧠 NEURAL NETWORK IMPLEMENTATION DEMONSTRATION");
            System.out.println("================================================");

            // Create and display activation functions
            System.out.println("\n📊 Available Activation Functions:");
            System.out.println("--------------------------------");
            ActivationFunction sigmoid = new Sigmoid();
            ActivationFunction identity = new Identity();
            LegacyActivation oldFunction = new OldActivationFunction();
            ActivationFunction adaptedFunction = new ActivationAdapter(oldFunction);

            System.out.println("1. Sigmoid Function");
            System.out.println("   Test values: -1: " + sigmoid.activate(-1) + ", 0: " + sigmoid.activate(0) + ", 1: " + sigmoid.activate(1));
            System.out.println("2. Identity Function");
            System.out.println("   Test values: -1: " + identity.activate(-1) + ", 0: " + identity.activate(0) + ", 1: " + identity.activate(1));
            System.out.println("3. Adapted Legacy Function (tanh)");
            System.out.println("   Test values: -1: " + adaptedFunction.activate(-1) + ", 0: " + adaptedFunction.activate(0) + ", 1: " + adaptedFunction.activate(1));

            // Create layers with neurons
            System.out.println("\n🏗️ Creating Neural Network Structure");
            System.out.println("--------------------------------");

            // Input Layer
            Layer inputLayer = new Layer();
            System.out.println("\n📥 Input Layer:");
            for (int i = 0; i < 3; i++) {
                Neuron neuron = new Neuron(identity);
                inputLayer.addNeuron(neuron);
                System.out.println("   - Input Neuron " + (i+1) + " (Identity Activation)");
            }

            // Hidden Layer
            Layer hiddenLayer = new Layer();
            System.out.println("\n🔄 Hidden Layer:");
            for (int i = 0; i < 5; i++) {
                Neuron neuron = new Neuron(sigmoid);
                hiddenLayer.addNeuron(neuron);
                System.out.println("   - Hidden Neuron " + (i+1) + " (Sigmoid Activation)");
            }

            // Output Layer
            Layer outputLayer = new Layer();
            System.out.println("\n📤 Output Layer:");
            for (int i = 0; i < 2; i++) {
                Neuron neuron = new Neuron(adaptedFunction);
                outputLayer.addNeuron(neuron);
                System.out.println("   - Output Neuron " + (i+1) + " (Adapted Legacy Activation)");
            }

            // Build network
            System.out.println("\n🔨 Building Neural Network");
            System.out.println("------------------------");
            NeuralNetwork network = new NeuralNetwork.Builder()
                .addLayer(inputLayer)
                .addLayer(hiddenLayer)
                .addLayer(outputLayer)
                .build();
            System.out.println("✅ Network built successfully with:");
            System.out.println("   - Input Layer: 3 neurons");
            System.out.println("   - Hidden Layer: 5 neurons");
            System.out.println("   - Output Layer: 2 neurons");

            // Add observer
            Dashboard dashboard = new Dashboard();
            network.addObserver(dashboard);
            System.out.println("\n👀 Dashboard Observer added");

            // Network lifecycle simulation
            System.out.println("\n🔄 Network Lifecycle Simulation");
            System.out.println("----------------------------");

            // Test prediction in construction state
            System.out.println("\n📊 Testing Prediction in Construction State:");
            try {
                double[] testInput = {1.0, 0.5, 0.8};
                System.out.println("   Input: " + Arrays.toString(testInput));
                network.predict(testInput);
            } catch (IllegalStateException e) {
                System.out.println("   ❌ Expected error: " + e.getMessage());
            }

            // Training phase
            System.out.println("\n🎯 Starting Training Phase:");
            network.train();

            // Complete training
            System.out.println("\n✅ Simulating Training Completion:");
            ((TrainingState)network.getState()).completeTraining(network);

            // Test prediction in trained state
            System.out.println("\n📊 Testing Prediction in Trained State:");
            double[] testInput = {1.0, 0.5, 0.8};
            System.out.println("   Input: " + Arrays.toString(testInput));
            network.predict(testInput);

            // Try training again
            System.out.println("\n🔄 Attempting to Train Again:");
            network.train();

            System.out.println("\n✨ Demonstration Complete");
            System.out.println("================================================");
        }
    }
}