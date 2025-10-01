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
    double gspeed = 0.00008;
    int segments = 20;
    int segmentiterator = 0;
    double tickCount = 0;
    private final ArrayList<Point2D.Double> Circles = new ArrayList<>();

    public ImageFollowingMousePanel() {
        addMouseMotionListener(this);
        while (segmentiterator <= segments) {
            Circles.add(new Point2D.Double(150, 150));
            segmentiterator++;
        }

        Runnable circlerestrain = () -> {
            if (mousePoint != null) {
                for (int i = 0; i < segments; i++) {
                    distancerestraint(Circles.get(i), Circles.get(i+1));
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
        double magx = (restrainedpoint.x-restrainingpoint.x)/sqrt(pow(restrainedpoint.x-restrainingpoint.x,2)+pow(restrainedpoint.y-restrainingpoint.y,2));
        double magy = (restrainedpoint.y-restrainingpoint.y)/sqrt(pow(restrainedpoint.x-restrainingpoint.x,2)+pow(restrainedpoint.y-restrainingpoint.y,2));
        double diffx = (restrainedpoint.x-restrainingpoint.x)-distance*magx;
        double diffy = (restrainedpoint.y-restrainingpoint.y)-distance*magy;

        if (sqrt(pow(restrainedpoint.x-restrainingpoint.x,2)+pow(restrainedpoint.y-restrainingpoint.y,2)) > distance) {
            restrainedpoint.x=restrainedpoint.x-diffx;
            restrainedpoint.y=restrainedpoint.y-diffy;
        }
    }

    public void gravity() {
        for (int i = 1; i < segments; i++) {
            Circles.get(i).y=Circles.get(i).y+gspeed;
        }
    }

    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        for (int i = 0; i < segments; i++) {
            drawcircle(gr, 50, 50, Circles.get(i));
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