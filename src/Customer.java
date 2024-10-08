public class Customer {
    private int id;
    private double arrivalTime;
    private double transactionTime;
    private boolean isError;

    public Customer(int id, double arrivalTime, double transactionTime, boolean isError) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.transactionTime = transactionTime;
        this.isError = isError;
    }

    public int getId() {
        return id;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getTransactionTime() {
        return transactionTime;
    }

    public boolean isError() {
        return isError;
    }
}
