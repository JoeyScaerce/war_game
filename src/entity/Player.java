package entity;

import main.GamePanel;
import main.MouseHandler;

import java.awt.*;

public class Player extends Entity{

    GamePanel gp;
    MouseHandler mh;
    public Rectangle r = new Rectangle();


    public Player(GamePanel gp, MouseHandler mh) {
        this.gp = gp;
        this.mh = mh;

        setDefaultvalues();
    }

    public void setDefaultvalues() {
        x = 100;
        y = 100;

    }

    public void update() {
        if (mh.mouseOnePressed /*&& r.contains(mh.getX(), mh.getY())*/) {
            x = mh.getX() - 25;
            y = mh.getY() - 25;

            //System.out.print("\r" + mh.getX() + "\n");
            //System.out.print("\r" + mh.getY());

            //g2.setColor(Color.green);
            //System.out.println(r);
            //System.out.println("mouse is pressed");
        }
    }

    /*public int doesRectangleContainPlayer() {
        int YesNo = 0;


        if (mh.mouseOnePressed && r.contains(mh.getX(), mh.getY())) {
            YesNo = 0;
        } else if (!(r.contains(mh.getX(), mh.getY())) && mh.){
            YesNo = 1;
        }

        return YesNo;
    }*/

    public void draw(Graphics2D g2) {
        r.setRect(x, y, gp.tileSize, gp.tileSize);
        g2.setColor(Color.red);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        g2.draw(r);

        /*if (r.contains(mh.getX(), mh.getY())) {
            System.out.println("hey");
        }*/

        //playerContainer.setRect(playerX, playerY, tileSize, tileSize);

    }

}
