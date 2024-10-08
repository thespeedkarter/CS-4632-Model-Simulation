public class SelfCheckout extends CheckoutLane {

    public SelfCheckout(double serviceRate) {
        super(serviceRate, "Self-Checkout");
    }

    @Override
    public void serveCustomer() {
        if (!queue.isEmpty()) {
            Customer currentCustomer = queue.poll();
            double transactionTime = currentCustomer.getTransactionTime();

            if (currentCustomer.isError()) {
                transactionTime *= 1.5;
                System.out.println("Error occurred at self-checkout for customer " + currentCustomer.getId() + ". Transaction took longer.");
            }

            System.out.println("Customer " + currentCustomer.getId() + " served at " + type + " in " + transactionTime + " seconds.");
        }
    }
}
