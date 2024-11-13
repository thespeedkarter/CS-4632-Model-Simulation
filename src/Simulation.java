import java.util.*;
import javax.swing.*;
import java.io.*;

public class Simulation {
    private int numCustomers;
    private int numSelfCheckouts;
    private int numCashierCheckouts;
    private double errorRate; // Error rate for self-checkout lanes
    private PriorityQueue<Event> eventQueue;
    private List<SelfCheckout> selfCheckoutLanes;
    private List<CashierCheckout> cashierLanes;
    private double currentTime;
    private JTextArea outputArea;
    private JProgressBar progressBar;
    private List<Customer> processedCustomers; // List to store processed customers

    public Simulation(int numCustomers, int numSelfCheckouts, int numCashierCheckouts, double errorRate, JTextArea outputArea, JProgressBar progressBar) {
        this.numCustomers = numCustomers;
        this.numSelfCheckouts = numSelfCheckouts;
        this.numCashierCheckouts = numCashierCheckouts;
        this.errorRate = errorRate;
        this.eventQueue = new PriorityQueue<>();
        this.selfCheckoutLanes = new ArrayList<>();
        this.cashierLanes = new ArrayList<>();
        this.currentTime = 0;
        this.outputArea = outputArea;
        this.progressBar = progressBar;
        this.processedCustomers = new ArrayList<>(); // Initialize the list

        // Initialize self-checkout lanes
        for (int i = 0; i < numSelfCheckouts; i++) {
            selfCheckoutLanes.add(new SelfCheckout(1.0));
        }

        // Initialize cashier lanes
        for (int i = 0; i < numCashierCheckouts; i++) {
            cashierLanes.add(new CashierCheckout(1.0));
        }
    }

    public void runSimulation() {
        generateCustomerArrivals();

        int totalEvents = eventQueue.size();
        int processedEvents = 0;

        while (!eventQueue.isEmpty()) {
            Event event = eventQueue.poll();
            currentTime = event.getTime();

            if (event.getType() == Event.ARRIVAL) {
                processArrival(event);
            } else if (event.getType() == Event.DEPARTURE) {
                processDeparture(event);
            }

            // Update progress bar
            processedEvents++;
            int progress = (int) ((double) processedEvents / totalEvents * 100);
            progressBar.setValue(progress);
        }

        // Output simulation results
        outputResults();
    }

    private void generateCustomerArrivals() {
        Random rand = new Random();
        double arrivalTime = 0;

        for (int i = 0; i < numCustomers; i++) {
            // Exponential inter-arrival times
            arrivalTime += -Math.log(1 - rand.nextDouble());
            double transactionTime = 30 + rand.nextDouble() * 30; // Transaction time between 30 and 60 seconds
            boolean isError = rand.nextDouble() < errorRate; // Error occurs based on error rate

            Customer customer = new Customer(i, arrivalTime, transactionTime, isError);
            Event arrivalEvent = new Event(arrivalTime, Event.ARRIVAL, customer, null);
            eventQueue.add(arrivalEvent);
        }
    }

    private void processArrival(Event event) {
        Customer customer = event.getCustomer();

        // Assign lane to customer
        CheckoutLane lane = assignLane(customer);
        customer.setLaneType(lane.getType());

        // Add customer to lane queue
        lane.addCustomer(customer);

        // If lane is idle, start serving the customer immediately
        if (lane.isIdle()) {
            lane.setBusy(true);
            Customer servedCustomer = lane.serveCustomer(currentTime);
            Event departureEvent = new Event(servedCustomer.getServiceEndTime(), Event.DEPARTURE, servedCustomer, lane);
            eventQueue.add(departureEvent);
        }
    }

    private void processDeparture(Event event) {
        CheckoutLane lane = event.getLane();
        Customer customer = event.getCustomer();

        // Collect customer data
        processedCustomers.add(customer);

        // Output detailed information
        double waitTime = customer.getServiceStartTime() - customer.getArrivalTime();
        outputArea.append(String.format("Customer %d:\n", customer.getId()));
        outputArea.append(String.format("  Arrival Time: %.2f\n", customer.getArrivalTime()));
        outputArea.append(String.format("  Lane Type: %s\n", customer.getLaneType()));
        outputArea.append(String.format("  Service Start Time: %.2f\n", customer.getServiceStartTime()));
        outputArea.append(String.format("  Service End Time: %.2f\n", customer.getServiceEndTime()));
        outputArea.append(String.format("  Wait Time: %.2f seconds\n", waitTime));
        if (customer.isError() && customer.getLaneType().equals("Self-Checkout")) {
            outputArea.append("  Error occurred during transaction.\n");
        }
        outputArea.append("\n");

        // Serve next customer in queue, if any
        if (lane.getQueueLength() > 0) {
            Customer servedCustomer = lane.serveCustomer(currentTime);
            Event departureEvent = new Event(servedCustomer.getServiceEndTime(), Event.DEPARTURE, servedCustomer, lane);
            eventQueue.add(departureEvent);
        } else {
            lane.setBusy(false);
        }
    }

    private CheckoutLane assignLane(Customer customer) {
        // Assign customer to the lane with the shortest queue
        // Customers randomly choose between self-checkout and cashier lanes

        Random rand = new Random();
        CheckoutLane chosenLane = null;

        if (!selfCheckoutLanes.isEmpty() && !cashierLanes.isEmpty()) {
            // Randomly decide whether to use self-checkout or cashier lane
            boolean useSelfCheckout = rand.nextBoolean();

            if (useSelfCheckout) {
                // Choose self-checkout lane with shortest queue
                chosenLane = Collections.min(selfCheckoutLanes, Comparator.comparingInt(CheckoutLane::getQueueLength));
            } else {
                // Choose cashier lane with shortest queue
                chosenLane = Collections.min(cashierLanes, Comparator.comparingInt(CheckoutLane::getQueueLength));
            }
        } else if (!selfCheckoutLanes.isEmpty()) {
            // Only self-checkout lanes are available
            chosenLane = Collections.min(selfCheckoutLanes, Comparator.comparingInt(CheckoutLane::getQueueLength));
        } else if (!cashierLanes.isEmpty()) {
            // Only cashier lanes are available
            chosenLane = Collections.min(cashierLanes, Comparator.comparingInt(CheckoutLane::getQueueLength));
        } else {
            // No lanes available (should not happen)
            System.err.println("No checkout lanes available!");
            System.exit(1);
        }

        return chosenLane;
    }

    private void outputResults() {
        // Collect statistics and display results
        double totalWaitTime = 0;
        double totalTransactionTime = 0;
        int totalCustomers = numCustomers;
        int totalErrors = 0;
        int selfCheckoutCustomers = 0;
        int cashierCheckoutCustomers = 0;
        double selfCheckoutWaitTime = 0;
        double cashierCheckoutWaitTime = 0;

        for (Customer customer : processedCustomers) {
            double waitTime = customer.getServiceStartTime() - customer.getArrivalTime();
            totalWaitTime += waitTime;
            totalTransactionTime += customer.getTransactionTime();

            if (customer.getLaneType().equals("Self-Checkout")) {
                selfCheckoutCustomers++;
                selfCheckoutWaitTime += waitTime;
                if (customer.isError()) {
                    totalErrors++;
                }
            } else if (customer.getLaneType().equals("Cashier Checkout")) {
                cashierCheckoutCustomers++;
                cashierCheckoutWaitTime += waitTime;
            }
        }

        double averageWaitTime = totalWaitTime / totalCustomers;
        double averageTransactionTime = totalTransactionTime / totalCustomers;
        double errorRateObserved = selfCheckoutCustomers > 0 ? (double) totalErrors / selfCheckoutCustomers : 0;

        outputArea.append("\nSimulation Complete!\n");
        outputArea.append("Total Customers Served: " + totalCustomers + "\n");
        outputArea.append("Total Self-Checkout Customers: " + selfCheckoutCustomers + "\n");
        outputArea.append("Total Cashier Checkout Customers: " + cashierCheckoutCustomers + "\n");
        outputArea.append(String.format("Average Wait Time: %.2f seconds\n", averageWaitTime));
        outputArea.append(String.format("Average Transaction Time: %.2f seconds\n", averageTransactionTime));
        outputArea.append(String.format("Observed Error Rate in Self-Checkout: %.2f%%\n", errorRateObserved * 100));

        if (selfCheckoutCustomers > 0) {
            double avgSelfCheckoutWait = selfCheckoutWaitTime / selfCheckoutCustomers;
            outputArea.append(String.format("Average Wait Time in Self-Checkout Lanes: %.2f seconds\n", avgSelfCheckoutWait));
        }

        if (cashierCheckoutCustomers > 0) {
            double avgCashierWait = cashierCheckoutWaitTime / cashierCheckoutCustomers;
            outputArea.append(String.format("Average Wait Time in Cashier Lanes: %.2f seconds\n", avgCashierWait));
        }
    }
}
