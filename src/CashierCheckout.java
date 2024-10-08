public class CashierCheckout extends CheckoutLane {

    public CashierCheckout(double serviceRate) {
        super(serviceRate, "Cashier Checkout");
    }

    @Override
    public void serveCustomer() {
        if (!queue.isEmpty()) {
            Customer currentCustomer = queue.poll();
            System.out.println("Customer " + currentCustomer.getId() + " served at " + type + " in " + currentCustomer.getTransactionTime() + " seconds.");
        }
    }
}
