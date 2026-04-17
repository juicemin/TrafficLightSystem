import javax.swing.*;
import java.awt.*;

public class TrafficLightGUI extends JFrame {
    private final SimulationPanel simulationPanel;
    private final JLabel stateLabel;
    private final JLabel timerLabel;
    private final JLabel requestLabel;

    private String currentState = "NS_GREEN";
    private String nextAfterAllRed = "EW_GREEN";
    private String lastServedDirection = "NS";

    private int secondsRemaining = 10;
    private Timer timer;

    // Simulated sensor requests
    private boolean nsRequest = false;
    private boolean ewRequest = false;

    public TrafficLightGUI() {
        setTitle("Smart Traffic Light Control System");
        setSize(950, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        simulationPanel = new SimulationPanel();
        add(simulationPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());

        stateLabel = new JLabel("Current State: NS_GREEN");
        timerLabel = new JLabel("Time Remaining: 10s");
        requestLabel = new JLabel("Requests: NS = No, EW = No");

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");

        JButton nsSensorButton = new JButton("NS Sensor");
        JButton ewSensorButton = new JButton("EW Sensor");
        JButton bothSensorButton = new JButton("Both Sensors");

        JButton emergencyNSButton = new JButton("Emergency NS");
        JButton emergencyEWButton = new JButton("Emergency EW");

        bottomPanel.add(stateLabel);
        bottomPanel.add(timerLabel);
        bottomPanel.add(requestLabel);
        bottomPanel.add(startButton);
        bottomPanel.add(stopButton);
        bottomPanel.add(resetButton);
        bottomPanel.add(nsSensorButton);
        bottomPanel.add(ewSensorButton);
        bottomPanel.add(bothSensorButton);
        bottomPanel.add(emergencyNSButton);
        bottomPanel.add(emergencyEWButton);

        add(bottomPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> startSimulation());
        stopButton.addActionListener(e -> stopSimulation());
        resetButton.addActionListener(e -> resetSimulation());

        nsSensorButton.addActionListener(e -> {
            nsRequest = true;
            updateRequestLabel();
        });

        ewSensorButton.addActionListener(e -> {
            ewRequest = true;
            updateRequestLabel();
        });

        bothSensorButton.addActionListener(e -> {
            nsRequest = true;
            ewRequest = true;
            updateRequestLabel();
        });

        emergencyNSButton.addActionListener(e -> triggerEmergency("NS"));
        emergencyEWButton.addActionListener(e -> triggerEmergency("EW"));

        updatePanelState();
        setVisible(true);
    }

    private void startSimulation() {
        if (timer != null && timer.isRunning()) {
            return;
        }

        timer = new Timer(1000, e -> {
            secondsRemaining--;
            timerLabel.setText("Time Remaining: " + secondsRemaining + "s");

            if (secondsRemaining <= 0) {
                advanceState();
            }
        });

        timer.start();
    }

    private void stopSimulation() {
        if (timer != null) {
            timer.stop();
        }
    }

    private void resetSimulation() {
        stopSimulation();

        currentState = "NS_GREEN";
        nextAfterAllRed = "EW_GREEN";
        lastServedDirection = "NS";
        secondsRemaining = 10;

        nsRequest = false;
        ewRequest = false;

        updatePanelState();
    }

    private void advanceState() {
        switch (currentState) {
            case "NS_GREEN":
                // Clear current served request
                nsRequest = false;
                currentState = "NS_YELLOW";
                secondsRemaining = 3;
                break;

            case "NS_YELLOW":
                currentState = "ALL_RED";
                nextAfterAllRed = chooseNextDirection();
                secondsRemaining = 2;
                break;

            case "EW_GREEN":
                // Clear current served request
                ewRequest = false;
                currentState = "EW_YELLOW";
                secondsRemaining = 3;
                break;

            case "EW_YELLOW":
                currentState = "ALL_RED";
                nextAfterAllRed = chooseNextDirection();
                secondsRemaining = 2;
                break;

            case "ALL_RED":
                setSafeGreenState(nextAfterAllRed);
                secondsRemaining = 10;
                break;
        }

        updatePanelState();
    }

    // Handles simultaneous sensor input and fairness
    private String chooseNextDirection() {
        if (nsRequest && ewRequest) {
            // Alternate fairly when both directions request at the same time
            if (lastServedDirection.equals("NS")) {
                return "EW_GREEN";
            } else {
                return "NS_GREEN";
            }
        } else if (nsRequest) {
            return "NS_GREEN";
        } else if (ewRequest) {
            return "EW_GREEN";
        } else {
            // No request: continue alternating normally
            if (lastServedDirection.equals("NS")) {
                return "EW_GREEN";
            } else {
                return "NS_GREEN";
            }
        }
    }

    // Explicit mutual exclusion logic: only one green direction allowed
    private void setSafeGreenState(String nextState) {
        if (nextState.equals("NS_GREEN")) {
            currentState = "NS_GREEN";
            lastServedDirection = "NS";
        } else if (nextState.equals("EW_GREEN")) {
            currentState = "EW_GREEN";
            lastServedDirection = "EW";
        } else {
            currentState = "ALL_RED";
        }
    }

    private void triggerEmergency(String direction) {
        currentState = "ALL_RED";
        secondsRemaining = 2;

        if (direction.equals("NS")) {
            nextAfterAllRed = "NS_GREEN";
            nsRequest = true;
        } else {
            nextAfterAllRed = "EW_GREEN";
            ewRequest = true;
        }

        updatePanelState();
    }

    private void updateRequestLabel() {
        String nsText = nsRequest ? "Yes" : "No";
        String ewText = ewRequest ? "Yes" : "No";
        requestLabel.setText("Requests: NS = " + nsText + ", EW = " + ewText);
    }

    private void updatePanelState() {
        stateLabel.setText("Current State: " + currentState);
        timerLabel.setText("Time Remaining: " + secondsRemaining + "s");
        updateRequestLabel();

        simulationPanel.setCurrentState(currentState);
        simulationPanel.repaint();
    }
}