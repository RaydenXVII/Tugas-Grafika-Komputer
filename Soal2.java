import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Soal2 extends JFrame implements Runnable {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int SUN_RADIUS = 30;
    private static final int PLANET_RADIUS = 20;
    private static final int ORBIT_RADIUS = 150;
    private static final int ROTATION_SPEED = 2; // in degrees per frame
    private static final Color SUN_COLOR = Color.YELLOW;
    private static final Color PLANET_COLOR = Color.BLUE;

    private double planetAngle = 0; // Angle of the planet in degrees
    private Point2D.Double sunPosition; // Position of the sun
    private Point2D.Double planetPosition; // Position of the planet

    public Soal2() {
        // Enable the closing of the window.
        addWindowListener(new MyFinishWindow());

        // Set preferred size of the frame
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Initialize positions
        sunPosition = new Point2D.Double(WIDTH / 2, HEIGHT / 2);
        planetPosition = new Point2D.Double(WIDTH / 2 + ORBIT_RADIUS, HEIGHT / 2);

        // Create an instance of SolarSystemPanel and add it to the frame
        SolarSystemPanel panel = new SolarSystemPanel();
        getContentPane().add(panel);

        // Start the animation thread
        Thread animationThread = new Thread(this);
        animationThread.start();
    }

    private class SolarSystemPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw sun
            g2d.setColor(SUN_COLOR);
            g2d.fill(new Ellipse2D.Double(sunPosition.x - SUN_RADIUS, sunPosition.y - SUN_RADIUS, 2 * SUN_RADIUS, 2 * SUN_RADIUS));

            // Calculate the direction vector from sun to planet
            double dx = planetPosition.x - sunPosition.x;
            double dy = planetPosition.y - sunPosition.y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            dx /= distance;
            dy /= distance;

            // Determine the angle between the direction vector and the vertical axis
            double cosTheta = -dy; // cos(180 degrees) = -dy
            double sinTheta = dx;  // sin(180 degrees) = dx

            // Draw planet
            g2d.setColor(getShadedColor(PLANET_COLOR, cosTheta, sinTheta));
            g2d.fill(new Ellipse2D.Double(planetPosition.x - PLANET_RADIUS, planetPosition.y - PLANET_RADIUS, 2 * PLANET_RADIUS, 2 * PLANET_RADIUS));
        }
    }

    private Color getShadedColor(Color color, double cosTheta, double sinTheta) {
        // Adjust brightness based on the angle between the surface and the direction of light
        float brightness = (float) (0.5 + 0.5 * cosTheta); // Range from 0.0 (facing away) to 1.0 (facing towards)
        int red = (int) (color.getRed() * brightness);
        int green = (int) (color.getGreen() * brightness);
        int blue = (int) (color.getBlue() * brightness);
        return new Color(red, green, blue);
    }

    @Override
    public void run() {
        while (true) {
            // Update planet position
            planetAngle += ROTATION_SPEED;
            if (planetAngle >= 360) {
                planetAngle -= 360;
            }
            double radians = Math.toRadians(planetAngle);
            planetPosition.x = sunPosition.x + ORBIT_RADIUS * Math.cos(radians);
            planetPosition.y = sunPosition.y + ORBIT_RADIUS * Math.sin(radians);

            // Repaint the panel
            repaint();

            // Sleep for a short interval to control the animation speed
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new Soal2();
            frame.setTitle("Solar System Animation");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
