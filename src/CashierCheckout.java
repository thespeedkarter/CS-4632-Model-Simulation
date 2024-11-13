public class CashierCheckout extends CheckoutLane {

    public CashierCheckout(double serviceRate) {
        super(serviceRate, "Cashier Checkout");
    }

    @Override
    public Customer serveCustomer(double currentTime) {
        if (!queue.isEmpty()) {
            Customer currentCustomer = queue.poll();
            currentCustomer.setServiceStartTime(currentTime);
            double serviceTime = currentCustomer.getTransactionTime();
            currentCustomer.setServiceEndTime(currentTime + serviceTime);
            return currentCustomer;
        }
        return null;
    }
}
