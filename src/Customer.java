public class Customer {
    private int id;
    private double arrivalTime;
    private double transactionTime;
    private boolean isError;
    private double serviceStartTime;
    private double serviceEndTime;
    private String laneType;

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

    public void setServiceStartTime(double serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public double getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceEndTime(double serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public double getServiceEndTime() {
        return serviceEndTime;
    }

    public void setLaneType(String laneType) {
        this.laneType = laneType;
    }

    public String getLaneType() {
        return laneType;
    }
}
