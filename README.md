# CS-4632-Model-Simulation
### Project Overview:

In this simulation, a retail checkout environment is replicated in order to examine the operational efficiency, cost effectiveness and customer satisfaction levels of self-checkout and cashier checkout systems. Users are also able to simulate a variety of circumstances such as different customer load, error rates and customer types by changing certain parameters of the model.

### Features:

**Discrete Event Simulation:** Implements DES to model customer arrivals, service times, queue management, and event scheduling.

**Customer Demographics:** Incorporates customer behavior based on demographic characteristics affecting lane preferences and error rates.

**Adjustable Parameters:** Allows users to modify simulation parameters, including customer count, error rates, number of lanes, and demographic distribution.

**Statistical Analysis:** Collects detailed data on customer wait times, transaction times, error occurrences, and lane utilization.

**Graphical User Interface (GUI):** Provides an interactive GUI for inputting parameters and viewing simulation results.

**Data Export:** Enables exporting of simulation results to CSV files for further analysis.

### Simulation Parameters:

**Customer Count:** Total number of customers to simulate.

**Error Rate:** Base error rate for self-checkout lanes (value between 0.0 and 1.0).

**Number of Self-Checkout Lanes:** Total self-checkout lanes available.

**Number of Cashier Lanes:** Total cashier-operated lanes available.

### Modeling Techniques:

**Arrival Process:** Modeled as a Poisson process to simulate random customer arrivals.
Service Times:

>**Self-Checkout Lanes:** Log-normal distribution to account for variability due to errors and user familiarity.

>**Cashier Lanes:** Normal distribution reflecting consistent service times.

**Customer Demographics:**

>**Tech-Savvy Customers:** Higher preference for self-checkout lanes, lower error rates.

>**Less Tech-Savvy Customers:** Higher preference for cashier lanes, higher error rates in self-checkout.

**Error Handling:**

>Errors occur based on specified probabilities.

>Additional service time added to simulate error resolution.

**Event Scheduling:** Uses the Next-Event Time Advance approach.

**Queue Management:** First-Come, First-Served discipline with separate queues for each lane type.

## Assumptions and Limitations:

**Assumptions:**

>Customer arrivals are random and independent.
>Service times vary based on lane type and customer demographics.
>Error rates are influenced by customer tech-savviness.

**Limitations:**
>Does not model customers abandoning queues or switching lanes.
>Technical failures of systems are not explicitly modeled.
>Staff interventions are implicitly included in error resolution times.
>Learning effects over time are not considered.

