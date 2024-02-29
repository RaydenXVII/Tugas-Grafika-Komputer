import java.awt.*;
import java.awt.geom.*;

public class Soal6 extends Frame {

    // Constructor
    public Soal6() {
        // Enable the closing of the window.
        addWindowListener(new MyFinishWindow());
    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        // Use antialiasing to have nicer lines.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // The lines should have a thickness of 3.0 instead of 1.0.
        BasicStroke bs = new BasicStroke(3.0f);
        g2d.setStroke(bs);

        // Definition and drawing of the curves that define the letters.

        // Letter D
        int xd1 = 250;
        int yd1 = 50;

        int xd2 = 250;
        int yd2 = 450;

        int xd3 = 450;
        int yd3 = 250;

        int xd4 = 250;
        int yd4 = 450;

        int xd5 = 250;
        int yd5 = 250;
        QuadCurve2D.Double d1 = new QuadCurve2D.Double(xd1, yd1, xd3, yd3, xd2, yd2);
        g2d.draw(d1);
        QuadCurve2D.Double d2 = new QuadCurve2D.Double(xd1, yd1, xd5, yd5, xd4, yd4);
        g2d.draw(d2);

        // Letter M
        int xm1 = 500, ym1 = 400;
        int xm2 = 500, ym2 = 100;
        int xm3 = 600, ym3 = 250;
        int xm4 = 700, ym4 = 100;
        int xm5 = 700, ym5 = 400;
        g2d.drawLine(xm1, ym1, xm2, ym2);
        g2d.drawLine(xm2, ym2, xm3, ym3);
        g2d.drawLine(xm3, ym3, xm4, ym4);
        g2d.drawLine(xm4, ym4, xm5, ym5);
    }

    public static void main(String[] argv) {
        Soal6 f = new Soal6();
        f.setTitle("Letters: D, M");

        f.setSize(750, 500);
        f.setVisible(true);
    }
}
