package main;

import java.awt.*;
import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener {
    public boolean mouseOnePressed = false;
    private int x, y;
    Point p;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getMousePosition() {
        return p;
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        int code = e.getButton();
        boolean clickedObject = false;

        if (code == MouseEvent.BUTTON1 && clickedObject == true) {

        }

        if (code == MouseEvent.BUTTON1) {
            mouseOnePressed = true;
            x = e.getX();
            y = e.getY();
            p = e.getPoint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int code = e.getButton();
        if (code == MouseEvent.BUTTON1) {
            mouseOnePressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mouseOnePressed) {
            x = e.getX();
            y = e.getY();
            //System.out.println(e.getComponent());
            //System.out.println(e.getPoint());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
