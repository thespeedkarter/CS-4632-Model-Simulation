public class SelfCheckout extends CheckoutLane {

    public SelfCheckout(double serviceRate) {
        super(serviceRate, "Self-Checkout");
    }

    @Override
    public Customer serveCustomer(double currentTime) {
        if (!queue.isEmpty()) {
            Customer currentCustomer = queue.poll();
            currentCustomer.setServiceStartTime(currentTime);
            double serviceTime = currentCustomer.getTransactionTime();

            if (currentCustomer.isError()) {
                serviceTime *= 1.5;
            }

            currentCustomer.setServiceEndTime(currentTime + serviceTime);
            return currentCustomer;
        }
        return null;
    }
}
