import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends JPanel {
    private String currentState = "NS_GREEN";

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        g.setColor(new Color(34, 139, 34));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Roads
        g.setColor(Color.DARK_GRAY);
        g.fillRect(350, 0, 200, getHeight());   // vertical
        g.fillRect(0, 250, getWidth(), 200);    // horizontal

        // Lane markings
        g.setColor(Color.WHITE);
        for (int i = 0; i < getHeight(); i += 40) {
            g.fillRect(447, i, 6, 20);
        }
        for (int i = 0; i < getWidth(); i += 40) {
            g.fillRect(i, 347, 20, 6);
        }

        // Road labels
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("North", 425, 40);
        g.drawString("South", 425, 600);
        g.drawString("West", 40, 365);
        g.drawString("East", 800, 365);

        // Title + state
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Smart Traffic Light Intersection Simulation", 220, 30);

        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("System State: " + currentState, 20, 60);

        drawTrafficLights(g);
        drawCars(g);
    }

    private void drawTrafficLights(Graphics g) {
        // Casings
        g.setColor(Color.BLACK);
        g.fillRect(300, 220, 40, 100);
        g.fillRect(560, 380, 40, 100);
        g.fillRect(520, 200, 100, 40);
        g.fillRect(280, 460, 100, 40);

        boolean nsGreen = currentState.equals("NS_GREEN");
        boolean nsYellow = currentState.equals("NS_YELLOW");
        boolean ewGreen = currentState.equals("EW_GREEN");
        boolean ewYellow = currentState.equals("EW_YELLOW");
        boolean allRed = currentState.equals("ALL_RED");

        drawVerticalLight(g, 310, 230, nsGreen, nsYellow, allRed);
        drawVerticalLight(g, 570, 390, nsGreen, nsYellow, allRed);

        drawHorizontalLight(g, 530, 210, ewGreen, ewYellow, allRed);
        drawHorizontalLight(g, 290, 470, ewGreen, ewYellow, allRed);
    }

    private void drawVerticalLight(Graphics g, int x, int y, boolean green, boolean yellow, boolean allRed) {
        g.setColor((!green && !yellow) || allRed ? Color.RED : Color.GRAY);
        g.fillOval(x, y, 20, 20);

        g.setColor(yellow ? Color.YELLOW : Color.GRAY);
        g.fillOval(x, y + 30, 20, 20);

        g.setColor(green ? Color.GREEN : Color.GRAY);
        g.fillOval(x, y + 60, 20, 20);
    }

    private void drawHorizontalLight(Graphics g, int x, int y, boolean green, boolean yellow, boolean allRed) {
        g.setColor((!green && !yellow) || allRed ? Color.RED : Color.GRAY);
        g.fillOval(x, y, 20, 20);

        g.setColor(yellow ? Color.YELLOW : Color.GRAY);
        g.fillOval(x + 30, y, 20, 20);

        g.setColor(green ? Color.GREEN : Color.GRAY);
        g.fillOval(x + 60, y, 20, 20);
    }

    private void drawCars(Graphics g) {
        // Static cars (no movement)
        g.setColor(Color.BLUE);
        g.fillRect(390, 120, 30, 50);
        g.fillRect(480, 500, 30, 50);

        g.setColor(Color.ORANGE);
        g.fillRect(120, 290, 50, 30);
        g.fillRect(700, 380, 50, 30);
    }
}