import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimulationGUI extends JFrame {

    private JTextField customerCountField;
    private JTextField errorRateField;
    private JTextField selfCheckoutField;
    private JTextField cashierCheckoutField;
    private JButton startButton;
    private JTextArea outputArea;
    private JProgressBar progressBar;

    public SimulationGUI() {
        setTitle("Checkout Lane Simulation");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
    }

    private void initializeComponents() {
        // Create components
        JLabel customerCountLabel = new JLabel("Customer Count:");
        customerCountField = new JTextField(10);

        JLabel errorRateLabel = new JLabel("Error Rate (0.0 - 1.0):");
        errorRateField = new JTextField(10);

        JLabel selfCheckoutLabel = new JLabel("Number of Self-Checkout Lanes:");
        selfCheckoutField = new JTextField(10);

        JLabel cashierCheckoutLabel = new JLabel("Number of Cashier Lanes:");
        cashierCheckoutField = new JTextField(10);

        startButton = new JButton("Start Simulation");
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        // Set layout
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2)); // Adjusted to fit new inputs
        inputPanel.add(customerCountLabel);
        inputPanel.add(customerCountField);
        inputPanel.add(errorRateLabel);
        inputPanel.add(errorRateField);
        inputPanel.add(selfCheckoutLabel);
        inputPanel.add(selfCheckoutField);
        inputPanel.add(cashierCheckoutLabel);
        inputPanel.add(cashierCheckoutField);
        inputPanel.add(new JLabel()); // Empty cell
        inputPanel.add(startButton);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);

        // Add action listener to the button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation();
            }
        });
    }

    private void startSimulation() {
        // Clear the output area
        outputArea.setText("");

        // Get inputs
        int customers;
        double errorRate;
        int numSelfCheckouts;
        int numCashierCheckouts;

        try {
            customers = Integer.parseInt(customerCountField.getText());
            if (customers <= 0) {
                throw new NumberFormatException("Customer count must be positive.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive integer for customer count.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            errorRate = Double.parseDouble(errorRateField.getText());
            if (errorRate < 0.0 || errorRate > 1.0) {
                throw new NumberFormatException("Error rate must be between 0.0 and 1.0.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid error rate between 0.0 and 1.0.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            numSelfCheckouts = Integer.parseInt(selfCheckoutField.getText());
            if (numSelfCheckouts < 0) {
                throw new NumberFormatException("Number of self-checkout lanes cannot be negative.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of self-checkout lanes.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            numCashierCheckouts = Integer.parseInt(cashierCheckoutField.getText());
            if (numCashierCheckouts < 0) {
                throw new NumberFormatException("Number of cashier lanes cannot be negative.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of cashier lanes.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (numSelfCheckouts == 0 && numCashierCheckouts == 0) {
            JOptionPane.showMessageDialog(this, "There must be at least one checkout lane.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Reset progress bar
        progressBar.setValue(0);
        progressBar.setMaximum(100);

        // Disable start button to prevent multiple clicks
        startButton.setEnabled(false);

        // Run the simulation in a new thread to keep the GUI responsive
        new Thread(() -> {
            Simulation simulation = new Simulation(customers, numSelfCheckouts, numCashierCheckouts, errorRate, outputArea, progressBar);

            simulation.runSimulation();

            // Re-enable start button after simulation is done
            SwingUtilities.invokeLater(() -> startButton.setEnabled(true));
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimulationGUI gui = new SimulationGUI();
            gui.setVisible(true);
        });
    }
}
