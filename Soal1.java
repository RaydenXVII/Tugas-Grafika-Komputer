import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class Soal1 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rounded Rectangle Example");
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.addWindowListener(new MyFinishWindow());
            frame.add(new RoundedRectPanel());
            frame.setVisible(true);
        });
    }

    private static class RoundedRectPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Create a GeneralPath
            GeneralPath path = new GeneralPath();

            // Define the coordinates of the rectangle with rounded corners
            int x = 30;
            int y = 30;
            int width = 200;
            int height = 100;
            int arcWidth = 30; // Width of the arc
            int arcHeight = 30; // Height of the arc

            // Move to the starting point
            path.moveTo(x + arcWidth, y);

            // Draw the top line
            path.lineTo(x + width - arcWidth, y);

            // Draw the top-right corner
            path.quadTo(x + width, y, x + width, y + arcHeight);

            // Draw the right line
            path.lineTo(x + width, y + height - arcHeight);

            // Draw the bottom-right corner
            path.quadTo(x + width, y + height, x + width - arcWidth, y + height);

            // Draw the bottom line
            path.lineTo(x + arcWidth, y + height);

            // Draw the bottom-left corner
            path.quadTo(x, y + height, x, y + height - arcHeight);

            // Draw the left line
            path.lineTo(x, y + arcHeight);

            // Draw the top-left corner
            path.quadTo(x, y, x + arcWidth, y);

            // Close the path
            path.closePath();

            // Draw the shape
            g2d.draw(path);
        }
    }
}
