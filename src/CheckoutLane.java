import java.util.LinkedList;
import java.util.Queue;

public abstract class CheckoutLane {
    protected Queue<Customer> queue;
    protected double serviceRate;
    protected String type;
    protected boolean busy; // To indicate if the lane is currently serving a customer

    public CheckoutLane(double serviceRate, String type) {
        this.queue = new LinkedList<>();
        this.serviceRate = serviceRate;
        this.type = type;
        this.busy = false;
    }

    public abstract Customer serveCustomer(double currentTime);

    public void addCustomer(Customer customer) {
        queue.add(customer);
    }

    public int getQueueLength() {
        return queue.size();
    }

    public String getType() {
        return type;
    }

    public boolean isIdle() {
        return !busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
