public class Event implements Comparable<Event> {
    public static final int ARRIVAL = 0;
    public static final int DEPARTURE = 1;

    private double time;
    private int type; // ARRIVAL or DEPARTURE
    private Customer customer;
    private CheckoutLane lane;

    public Event(double time, int type, Customer customer, CheckoutLane lane) {
        this.time = time;
        this.type = type;
        this.customer = customer;
        this.lane = lane;
    }

    public double getTime() {
        return time;
    }

    public int getType() {
        return type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CheckoutLane getLane() {
        return lane;
    }

    @Override
    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }
}
