package main;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GamePanel extends JPanel implements Runnable{

    //Screen setting
    int originalTileSize = 16; // 16x16 tile
    int scale = 3;

    int tileSize = originalTileSize*scale;

    // set how many tile are displayed on screen
    int maxScreenCol = 16;
    int maxScreenRow = 12;
    int screenWidth = tileSize*maxScreenCol;
    int screenHeight = tileSize*maxScreenRow;

    //frame rate (FPS)
    int FPS = 60;
    Rectangle playerContainer = new Rectangle();
    //player
    MouseHandler mouseH = new MouseHandler();
    Rectangle2D r;

    // set player position
    int playerX = 100;
    int playerY = 100;

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true); // improve rendering performance
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //draw screen & character with updated information
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {


            update();
            repaint(); // how you call paintComponent
        }

    }

    public void update() {
        if (mouseH.mouseOnePressed && r.contains(mouseH.getMousePosition())) {
            playerX = mouseH.getX();
            playerY = mouseH.getY();
            System.out.println(r);
            //System.out.println("mouse is pressed");
        }
    }


    //Standard method to draw on jpanel
    public void paintComponent(Graphics g) {
        r = new Rectangle();

        r.setRect(playerX, playerY, tileSize, tileSize);

        super.paintComponent(g);
        // parse to graphic 2d
        // draws character currently
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.red);
        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.draw(r);
        //playerContainer.setRect(playerX, playerY, tileSize, tileSize);


        g2.dispose(); // saves memory by releasing system resources that it was using

    }

}
