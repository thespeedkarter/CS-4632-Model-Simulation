import java.util.Random;
import java.util.Scanner;

public class Simulation {
    public static void main(String[] args) {
        Random rand = new Random();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer Count: ");
        int customers= scanner.nextInt();

        // Create checkouts
        SelfCheckout selfCheckout1 = new SelfCheckout(2.0);
        CashierCheckout cashier1 = new CashierCheckout(1.5);

        // Arrival of 10 customers
        for (int i = 0; i < 10; i++) {
            double arrivalTime = i * 10 + rand.nextDouble() * 5; // Arrival times
            double transactionTime = 30 + rand.nextDouble() * 20; // Transaction time
            boolean error = rand.nextBoolean(); // Random assign errors

            Customer customer = new Customer(i, arrivalTime, transactionTime, error);

            // Random assign customers to check out type
            if (rand.nextBoolean()) {
                System.out.println("Customer " + customer.getId() + " arrives at self-checkout at " + arrivalTime + " seconds.");
                selfCheckout1.addCustomer(customer);
            } else {
                System.out.println("Customer " + customer.getId() + " arrives at cashier at " + arrivalTime + " seconds.");
                cashier1.addCustomer(customer);
            }
        }

        // Simulate serving customers at both checkout types
        System.out.println("\nServing customers at self-checkout:");
        for (int i = 0; i < customers; i++) {
            selfCheckout1.serveCustomer();
        }

        System.out.println("\nServing customers at cashier:");
        for (int i = 0; i < customers; i++) {
            cashier1.serveCustomer();
        }
    }
}
