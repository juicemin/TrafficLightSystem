# 🚦 Smart Traffic Light Control System

## 📌 Overview

This project implements a **Smart Traffic Light Control System** using Java. It simulates traffic control at a four-way intersection and demonstrates key concepts of **real-time systems and concurrency**.

The system is designed as a **prototype** to visualize how traffic lights operate safely when handling multiple inputs such as simultaneous traffic requests and emergency situations.

---

## ⚙️ Features

* ⏱️ **Timer-Based State Transitions**

  * Automatically cycles traffic lights (Green → Yellow → All-Red → Next Direction)

* 🔄 **Simultaneous Input Handling**

  * Handles traffic requests from both directions at the same time
  * Uses scheduling logic to resolve conflicts safely

* 🔐 **Mutual Exclusion**

  * Ensures only one direction has a green light at any time

* 🚑 **Emergency Override**

  * Allows priority handling for emergency vehicles
  * Uses safe ALL RED transition before switching

* 🖥️ **GUI Visualization**

  * Displays traffic lights, timer, and system state in real time

---

## 🛠️ Requirements

* Java JDK 8 or above
* Any Java IDE (VS Code / NetBeans / IntelliJ)

---

## ▶️ How to Run the System (VS Code)

### Step 1: Install Requirements

* Install Java JDK
* Install VS Code
* Install **Extension Pack for Java**

---

### Step 2: Open Project

* Open VS Code
* Click **File → Open Folder**
* Select your project folder

---

### Step 3: Compile Code (if needed)

Open terminal:

```bash
javac *.java
```

---

### Step 4: Run Program

Option 1:

* Open `Main.java`
* Click **Run ▶️**

Option 2:

```bash
java Main
```

---

### Step 5: Launch GUI

* The traffic light simulation window will appear

---

## ▶️ How to Run the System (NetBeans)

### Step 1: Create Project

* Open NetBeans
* File → New Project → Java Application

---

### Step 2: Add Files

Create and paste code into:

* Main.java
* TrafficLightGUI.java
* SimulationPanel.java

---

### Step 3: Set Main Class

Set main class to:

```
Main
```

---

### Step 4: Run

* Click **Run ▶️** or press **F6**

---

## 🎮 How to Use the System

Once the GUI opens, you can interact using buttons.

---

## 🔘 Control Buttons

### ▶️ Start

* Starts automatic traffic light cycle

---

### ⏹️ Stop

* Pauses simulation

---

### 🔄 Reset

* Resets system to initial state
* Clears all requests

---

## 🚗 Sensor Buttons

### NS Sensor

* Simulates traffic from **North–South**
* System will serve NS when safe

---

### EW Sensor

* Simulates traffic from **East–West**
* System will serve EW when scheduled

---

### Both Sensors

* Simulates **simultaneous traffic**

👉 System behavior:

* detects both requests
* applies scheduling logic
* allows only one direction at a time

---

## 🚑 Emergency Buttons

### Emergency NS

* Immediately prioritizes North–South

Process:

1. Switch to ALL RED
2. Then NS GREEN

---

### Emergency EW

* Immediately prioritizes East–West

Process:

1. Switch to ALL RED
2. Then EW GREEN

---

## 🧠 System Logic

### 🔁 Finite State Machine (FSM)

Traffic flows in this cycle:

* NS_GREEN → NS_YELLOW → ALL_RED
* EW_GREEN → EW_YELLOW → ALL_RED
* Repeat

---

### ⚙️ Concurrent Handling

* Multiple requests can occur simultaneously
* System evaluates all requests before assigning green

If both directions request:

* System alternates based on last served direction
* Ensures fairness and prevents starvation

---

### 🔐 Safety Mechanism

* ALL RED state ensures safe switching
* Prevents conflicting green lights

---

## 📂 Project Structure

```
Main.java                  → Entry point
TrafficLightGUI.java      → Core logic and control
SimulationPanel.java      → GUI visualization
```

---

## ⚠️ Common Issues

**GUI not showing**

* Ensure you run `Main.java`

**Compilation error**

* Ensure all `.java` files are in same folder/package

**Java not recognized**

* Check JDK installation and PATH

---

## ⚠️ Notes

* This is a **prototype system**
* Real-world systems would include:

  * hardware sensors
  * multi-threading
  * advanced traffic optimization

---
