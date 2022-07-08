package main;

import entity.Player;

import java.awt.*;
import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener {
    public boolean mouseOnePressed = false;
    public int mousePhase = 0;
    private int x, y;

    Point p;
    Player player;
    GamePanel gp;


    public int getMousePhase() {
        return mousePhase;
    }

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

        if (code == MouseEvent.BUTTON1 ) {
            mouseOnePressed = true;
            x = e.getX();
            y = e.getY();
            //System.out.println(p);

            /*if (player.r.contains(x, y)) {

            }*/

            System.out.println(mousePhase++);

        }

        if (mousePhase == 2) {
            mouseOnePressed = false;
            mousePhase = 0;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        /*int code = e.getButton();
        if (code == MouseEvent.BUTTON1) {
            mouseOnePressed = false;
        }*/
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
            x = e.getX();
            y = e.getY();
            //System.out.println(e.getComponent());
            //System.out.println(e.getPoint());
    }

}
