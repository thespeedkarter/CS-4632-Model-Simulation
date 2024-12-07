# CS-4632-Model-Simulation

### Project Overview

This simulation seeks to duplicate a retail checkout environment to compare a self-service mechanic's efficiency, cost-effectiveness, and customer satisfaction with a cashier checkout system. By simulating different customer loads, error frequencies, and types of customers, users can access a variety of different system models.

---

### Features

- **Discrete Event Simulation (DES):** Models customer arrivals, service times, queue management, and event scheduling.
- **Customer Demographics:** Simulates customer behavior based on demographic characteristics affecting lane preferences and error rates.
- **Adjustable Parameters:** Modify simulation parameters like customer count, error rates, number of lanes, and demographics.
- **Statistical Analysis:** Detailed data on wait times, transaction times, error occurrences, and lane utilization.
- **Graphical User Interface (GUI):** Interactive GUI for setting parameters and viewing simulation results.
- **Data Export:** Export simulation results to CSV files for further analysis.

---

### Simulation Parameters

- **Customer Count:** Total number of customers to simulate.
- **Error Rate:** Base error rate for self-checkout lanes (between 0.0 and 1.0).
- **Self-Checkout Lanes:** Number of available self-checkout lanes.
- **Cashier Lanes:** Number of available cashier-operated lanes.

---

## Installation Instructions

### Prerequisites

Ensure you have the following installed:

1. **Java Development Kit (JDK) 17** or later.  
   Download from [Oracle's JDK Download Page](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

2. **Java Swing Library** (Included in standard JDK).

3. **IDE (Optional):**
    - IntelliJ IDEA
    - Eclipse
    - NetBeans

### Steps to Install

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/CS-4632-Model-Simulation.git
   ```

2. **Navigate to the Project Directory:**

   ```bash
   cd CS-4632-Model-Simulation
   ```

3. **Compile the Code:**

   If using the command line, compile the code using `javac`:

   ```bash
   javac *.java
   ```

   Alternatively, import the project into your preferred IDE and build the project.

---

## Running the Simulation

1. **Run the Simulation:**

   Execute the `Simulation` class:

   ```bash
   java Simulation
   ```

2. **GUI Input:**

    - Enter the number of customers, self-checkout lanes, cashier lanes, and error rate.
    - Click the **"Run Simulation"** button to start the simulation.
    - Monitor progress through the progress bar and view detailed results in the output area.

3. **Example Parameters:**

    - **Number of Customers:** 100
    - **Number of Self-Checkout Lanes:** 3
    - **Number of Cashier Lanes:** 2
    - **Error Rate:** 0.05 (5% error rate)

---

## Requirements

- **Operating System:**
    - Windows 10/11
    - macOS
    - Linux

- **Hardware:**
    - Minimum 4GB RAM
    - Dual-core processor

- **Libraries:**
    - Standard Java libraries (Swing for GUI).

---

## Assumptions and Limitations

### Assumptions

- Customer arrivals are random and independent.
- Service times vary based on lane type and customer demographics.
- Error rates are influenced by customer tech-savviness.

### Limitations

- Customers do not abandon queues or switch lanes.
- Technical failures are not explicitly modeled.
- Learning effects over time are not considered.

---

## Exporting Results

Simulation results can be copied from the output area or exported to a CSV file for analysis.

---

## License

This project is licensed under the MIT License.
