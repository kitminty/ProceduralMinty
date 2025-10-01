package org.kitminty;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static java.lang.Math.*;

public class Main {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new ImageFollowingMousePanel());
        f.setSize(1270, 1270);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}

class ImageFollowingMousePanel extends JPanel implements MouseMotionListener {
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private Point mousePoint;
    double distance = 10;
    double speed = 0.1;
    Double gspeed = 0.00008;
    int segments = 20;
    double tickCount = 0;
    private final ArrayList<Point2D.Double> Circles = new ArrayList<>();

    public ImageFollowingMousePanel() {  //this happens once
        addMouseMotionListener(this);
        int i = 0;
        while (i <= segments) {
            Circles.add(new Point2D.Double(150, 150));
            i++;
        }


        Runnable circlerestrain = () -> {
            if (mousePoint != null) {
                for (int i2 = 0; i2 < segments; i2++) {
                    distancerestraint(Circles.get(i2), Circles.get(i2+1));
                }
                repaint();
            }
            //gravity();
        };
        Runnable headcircle = () -> {
            tickCount=tickCount+0.01;

            if (mousePoint != null) {
                Circles.getFirst().x = mousePoint.x-25;
                Circles.getFirst().y = mousePoint.y-25;
            }

            /*
            int lemsize = 500;
            double lemx = lemsize*((2*cos(tickCount))/(3-cos(2*tickCount)));
            double lemz = lemsize*((sin(2*tickCount))/(3-cos(2*tickCount)));
            circle1.x = lemx+500;
            circle1.y = lemz+500;
            System.out.println(tickCount);

             */
        };
        scheduler.scheduleAtFixedRate(circlerestrain, 0, 1, TimeUnit.MICROSECONDS); //this was nanoseconds but it probably shouldn't be
        scheduler.scheduleAtFixedRate(headcircle, 0, 1, TimeUnit.MICROSECONDS);
    }

    public void distancerestraint(Point2D.Double restrainingpoint, Point2D.Double restrainedpoint) {
        double veccirx = (restrainedpoint.x)-restrainingpoint.x;
        double vecciry = (restrainedpoint.y)-restrainingpoint.y;
        double magnitude = sqrt(pow(veccirx,2)+pow(vecciry,2));
        double normalisedvectorx = veccirx/magnitude;
        double normalisedvectory = vecciry/magnitude;

        if (((pow(restrainingpoint.x-restrainedpoint.x, 2) + pow(restrainingpoint.y-restrainedpoint.y, 2)) > pow(distance, 2))) {
            restrainedpoint.x=restrainedpoint.x-normalisedvectorx*speed;
            restrainedpoint.y=restrainedpoint.y-normalisedvectory*speed;
        }
    }

    public void gravity() {
        for (int i2 = 1; i2 < segments; i2++) {
            Circles.get(i2).y=Circles.get(i2).y+gspeed;
        }
    }

    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        for (int i2 = 0; i2 < segments; i2++) {
            drawcircle(gr, 50, 50, Circles.get(i2));
        }
    }

    public void drawcircle(Graphics gr, int width, int height, Point2D.Double circle) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        AffineTransform oldAT = g.getTransform();
        g.translate(circle.x, circle.y);
        g.drawOval(0, 0, width, height);
        g.setTransform(oldAT);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePoint = e.getPoint();
        repaint();
    }

}

/*package org.kitminty;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new ImageFollowingMousePanel());
        f.setSize(1270, 1270);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}

class ImageFollowingMousePanel extends JPanel implements MouseMotionListener {
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private Point mousePoint;
    double distance = 10;
    double speed = 0.1;
    double gspeed = 0.00008;
    double tickCount = 0;
    int i = 3;
    private final Point2D.Double circle1 = new Point2D.Double(150, 150);
    List<Point2D.Double> circles = new ArrayList<>();

    public ImageFollowingMousePanel() {
        addMouseMotionListener(this);
        circles.add(new Point2D.Double(150, 150));
        circles.add(new Point2D.Double(150, 150));
        circles.add(new Point2D.Double(150, 150));
        circles.add(new Point2D.Double(150, 150));
        circles.add(new Point2D.Double(150, 150));
        circles.add(new Point2D.Double(150, 150));
        circles.add(new Point2D.Double(150, 150));

        Runnable circlerestrain = () -> {
            if (mousePoint != null) {
                circles.set(1, distancerestraint(circle1, circles.get(1)));
                if (i==circles.size()) {
                    i=3;
                } else {
                    i++;
                }
                System.out.println(circles.get(1));
                //.set(i, distancerestraint(circles.get(i), circles.get(i+1)));

                repaint();
            }
            //gravity();
        };
        Runnable headcircle = () -> {
            tickCount=tickCount+0.01;

            if (mousePoint != null) {
                circle1.x = mousePoint.x-25;
                circle1.y = mousePoint.y-25;
            }

            /*
            int lemsize = 500;
            double lemx = lemsize*((2*cos(tickCount))/(3-cos(2*tickCount)));
            double lemz = lemsize*((sin(2*tickCount))/(3-cos(2*tickCount)));
            circle1.x = lemx+500;
            circle1.y = lemz+500;
            System.out.println(tickCount);

             *
        };
        scheduler.scheduleAtFixedRate(circlerestrain, 0, 1, TimeUnit.NANOSECONDS);
        scheduler.scheduleAtFixedRate(headcircle, 0, 100, TimeUnit.MICROSECONDS);
    }

    public Point2D.Double distancerestraint(Point2D.Double restrainingpoint, Point2D.Double restrainedpoint) {
        double veccirx = (restrainedpoint.x)-restrainingpoint.x;
        double vecciry = (restrainedpoint.y)-restrainingpoint.y;
        double magnitude = sqrt(pow(veccirx,2)+pow(vecciry,2));
        double normalisedvectorx = veccirx/magnitude;
        double normalisedvectory = vecciry/magnitude;

        if (((pow(restrainingpoint.x-restrainedpoint.x, 2) + pow(restrainingpoint.y-restrainedpoint.y, 2)) > pow(distance, 2))) {
            restrainedpoint.x=restrainedpoint.x-normalisedvectorx*speed;
            restrainedpoint.y=restrainedpoint.y-normalisedvectory*speed;
        }
        return new Point2D.Double(restrainedpoint.x, restrainedpoint.y);
    }

    public void gravity() {
        for (int i = 2; i<circles.size(); i++) {
            circles.get(i).y=circles.get(i).y+gspeed;
        }
    }

    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        drawcircle(gr, 50, 50, circle1);
        for (int i = 2; i<circles.size(); i++) {
            drawcircle(gr, 50, 50, circles.get(i));
        }
    }

    public void drawcircle(Graphics gr, int width, int height, Point2D.Double circle) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        AffineTransform oldAT = g.getTransform();
        g.translate(circle.x, circle.y);
        g.drawOval(0, 0, width, height);
        g.setTransform(oldAT);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePoint = e.getPoint();
        repaint();
    }

}
*/