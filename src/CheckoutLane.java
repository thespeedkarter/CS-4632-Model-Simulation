import java.util.LinkedList;
import java.util.Queue;

public abstract class CheckoutLane {
    protected Queue<Customer> queue;
    protected double serviceRate;
    protected String type;

    public CheckoutLane(double serviceRate, String type) {
        this.queue = new LinkedList<>();
        this.serviceRate = serviceRate;
        this.type = type;
    }

    public abstract void serveCustomer();

    public void addCustomer(Customer customer) {
        queue.add(customer);
    }

    public int getQueueLength() {
        return queue.size();
    }

    public String getType() {
        return type;
    }
}
