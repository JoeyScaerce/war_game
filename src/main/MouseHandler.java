package main;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseHandler implements MouseWheelListener {
    GamePanel gp;


    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int c = e.getWheelRotation();

        if (c == -1) {
            gp.zoomFunction(1);
        }

        if (c == 1) {
            gp.zoomFunction(-1);
        }

    }
}
