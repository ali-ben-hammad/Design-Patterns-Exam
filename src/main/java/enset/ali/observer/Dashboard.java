package enset.ali.observer;

public class Dashboard implements NetworkObserver {
    @Override
    public void update(String state, double accuracy) {
        System.out.println("Dashboard updated - State: " + state + ", Accuracy: " + accuracy);
    }
}
