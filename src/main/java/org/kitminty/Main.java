package org.kitminty;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
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
    double tickCount = 0;
    private final Point2D.Double circle1 = new Point2D.Double(150, 150);
    private Point2D.Double circle2 = new Point2D.Double(150, 150);
    private Point2D.Double circle3 = new Point2D.Double(150, 150);
    private Point2D.Double circle4 = new Point2D.Double(150, 150);
    private Point2D.Double circle5 = new Point2D.Double(150, 150);
    private Point2D.Double circle6 = new Point2D.Double(150, 150);
    private Point2D.Double circle7 = new Point2D.Double(150, 150);
    private Point2D.Double circle8 = new Point2D.Double(150, 150);
    private Point2D.Double circle9 = new Point2D.Double(150, 150);
    private Point2D.Double circle10 = new Point2D.Double(150, 150);
    private Point2D.Double circle11 = new Point2D.Double(150, 150);
    private Point2D.Double circle12 = new Point2D.Double(150, 150);
    private Point2D.Double circle13 = new Point2D.Double(150, 150);
    private Point2D.Double circle14 = new Point2D.Double(150, 150);
    private Point2D.Double circle15 = new Point2D.Double(150, 150);
    private Point2D.Double circle16 = new Point2D.Double(150, 150);
    private Point2D.Double circle17 = new Point2D.Double(150, 150);
    private Point2D.Double circle18 = new Point2D.Double(150, 150);
    private Point2D.Double circle19 = new Point2D.Double(150, 150);
    private Point2D.Double circle20 = new Point2D.Double(150, 150);
    private Point2D.Double circle21 = new Point2D.Double(150, 150);
    private Point2D.Double circle22 = new Point2D.Double(150, 150);
    private Point2D.Double circle23 = new Point2D.Double(150, 150);
    private Point2D.Double circle24 = new Point2D.Double(150, 150);
    private Point2D.Double circle25 = new Point2D.Double(150, 150);
    private Point2D.Double circle26 = new Point2D.Double(150, 150);
    private Point2D.Double circle27 = new Point2D.Double(150, 150);

    public ImageFollowingMousePanel() {
        addMouseMotionListener(this);

        Runnable circlerestrain = () -> {
            if (mousePoint != null) {
                distancerestraint(circle1, circle2);
                distancerestraint(circle2, circle3);
                distancerestraint(circle3, circle4);
                distancerestraint(circle4, circle5);
                distancerestraint(circle5, circle6);
                distancerestraint(circle6, circle7);
                distancerestraint(circle7, circle8);
                distancerestraint(circle8, circle9);
                distancerestraint(circle9, circle10);
                distancerestraint(circle10, circle11);
                distancerestraint(circle11, circle12);
                distancerestraint(circle12, circle13);
                distancerestraint(circle13, circle14);
                distancerestraint(circle14, circle15);
                distancerestraint(circle15, circle16);
                distancerestraint(circle16, circle17);
                distancerestraint(circle17, circle18);
                distancerestraint(circle18, circle19);
                distancerestraint(circle19, circle20);
                distancerestraint(circle20, circle21);
                distancerestraint(circle21, circle22);
                distancerestraint(circle22, circle23);
                distancerestraint(circle23, circle24);
                distancerestraint(circle24, circle25);
                distancerestraint(circle25, circle26);
                distancerestraint(circle26, circle27);
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

             */
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
        circle2.y=circle2.y+gspeed;
        circle3.y=circle3.y+gspeed;
        circle4.y=circle4.y+gspeed;
        circle5.y=circle5.y+gspeed;
        circle6.y=circle6.y+gspeed;
        circle7.y=circle7.y+gspeed;
        circle8.y=circle8.y+gspeed;
        circle9.y=circle9.y+gspeed;
        circle10.y=circle10.y+gspeed;
        circle11.y=circle11.y+gspeed;
        circle12.y=circle12.y+gspeed;
        circle13.y=circle13.y+gspeed;
        circle14.y=circle14.y+gspeed;
        circle15.y=circle15.y+gspeed;
        circle16.y=circle16.y+gspeed;
        circle17.y=circle17.y+gspeed;
        circle18.y=circle18.y+gspeed;
        circle19.y=circle19.y+gspeed;
        circle20.y=circle20.y+gspeed;
        circle21.y=circle21.y+gspeed;
        circle22.y=circle22.y+gspeed;
        circle23.y=circle23.y+gspeed;
        circle24.y=circle24.y+gspeed;
        circle25.y=circle25.y+gspeed;
        circle26.y=circle26.y+gspeed;
        circle27.y=circle27.y+gspeed;
    }

    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        drawcircle(gr, 50, 50, circle1);
        drawcircle(gr, 50, 50, circle2);
        drawcircle(gr, 50, 50, circle3);
        drawcircle(gr, 50, 50, circle4);
        drawcircle(gr, 50, 50, circle5);
        drawcircle(gr, 50, 50, circle6);
        drawcircle(gr, 50, 50, circle7);
        drawcircle(gr, 50, 50, circle8);
        drawcircle(gr, 50, 50, circle9);
        drawcircle(gr, 50, 50, circle10);
        drawcircle(gr, 50, 50, circle11);
        drawcircle(gr, 50, 50, circle12);
        drawcircle(gr, 50, 50, circle13);
        drawcircle(gr, 50, 50, circle14);
        drawcircle(gr, 50, 50, circle15);
        drawcircle(gr, 50, 50, circle16);
        drawcircle(gr, 50, 50, circle17);
        drawcircle(gr, 50, 50, circle18);
        drawcircle(gr, 50, 50, circle19);
        drawcircle(gr, 50, 50, circle20);
        drawcircle(gr, 50, 50, circle21);
        drawcircle(gr, 50, 50, circle22);
        drawcircle(gr, 50, 50, circle23);
        drawcircle(gr, 50, 50, circle24);
        drawcircle(gr, 50, 50, circle25);
        drawcircle(gr, 50, 50, circle26);
        drawcircle(gr, 50, 50, circle27);
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