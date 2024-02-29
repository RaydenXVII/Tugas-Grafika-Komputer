import javax.swing.*;
import java.awt.*;

public class Soal4 extends JFrame implements Runnable {
    private double planetAngle = 0; // Angle of the planet in radians
    private double planetOrbitRadius = 200; // Radius of planet's orbit
    private double planetRotationSpeed = 2 * Math.PI / 365; // Speed of rotation of planet around the sun
    
    public Soal4() {
        // Enable the closing of the window.
        addWindowListener(new MyFinishWindow());

        // Set preferred size of the frame
        setPreferredSize(new Dimension(600, 400));

        // Add the panel to the frame
        add(new SolarSystemPanel());

        // Start the animation thread
        Thread animationThread = new Thread(this);
        animationThread.start();
    }

    private class SolarSystemPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Set rendering hints
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Translate to the center of the panel
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            g2d.translate(centerX, centerY);

            // Draw the sun at the origin
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(-20, -20, 40, 40);

            // Calculate the position of the planet
            double planetX = planetOrbitRadius * Math.cos(planetAngle);
            double planetY = planetOrbitRadius * Math.sin(planetAngle);

            // Draw the planet
            g2d.setColor(Color.BLUE);
            g2d.fillOval((int) (planetX - 10), (int) (planetY - 10), 20, 20);

            // Draw the orbit of the planet
            g2d.setColor(Color.GRAY);
            g2d.drawOval((int) (planetOrbitRadius - 10), (int) (planetOrbitRadius - 10),
                    (int) (2 * planetOrbitRadius), (int) (2 * planetOrbitRadius));
        }
    }

    @Override
    public void run() {
        while (true) {
            // Update the angle of the planet
            planetAngle += planetRotationSpeed;
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
            JFrame frame = new Soal4();
            frame.setTitle("Solar System Animation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
