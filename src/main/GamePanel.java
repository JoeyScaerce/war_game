package main;

import entity.Player;
import tile.TileManger;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GamePanel extends JPanel implements Runnable{

    //Screen setting
    int originalTileSize = 16; // 16x16 tile
    int scale = 3;

    public int tileSize = originalTileSize*scale;

    // set how many tile are displayed on screen
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize*maxScreenCol;
    public int screenHeight = tileSize*maxScreenRow;

    //frame rate (FPS)
    int FPS = 60;
    TileManger tileM = new TileManger(this);

    //player
    MouseHandler mouseH = new MouseHandler();
    Player player = new Player(this, mouseH);
    // set player position
    int playerX = 100;
    int playerY = 100;

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
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
        double delta = 0;
        long lastTime = System.nanoTime();
        long CurrentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            CurrentTime = System.nanoTime();
            delta += (CurrentTime - lastTime) / drawInterval;

            timer += CurrentTime - lastTime;
            lastTime = CurrentTime;

            if (delta >= 1) {
                update();
                repaint(); // how you call paintComponent
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }


    public void update() {

        player.update();


    }


    //Standard method to draw on jpanel
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // cast to graphic 2d
        // draws character currently
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);


        g2.dispose(); // saves memory by releasing system resources that it was using

    }

}
