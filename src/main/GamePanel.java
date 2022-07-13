package main;

import entity.Player;
import tile.TileManger;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GamePanel extends JPanel implements Runnable{

    //Screen & tile setting
    int originalTileSize = 16; // 16x16 tile
    int scale = 3;
    public int tileSize = originalTileSize*scale;

    //set how many tile are displayed on screen
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;

    //World setting. similar to variables above.
    // this need exact numbers of tiles. should change in the future.
    public final int maxWorldCol = 16;
    public final int maxWorldRow = 12;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //Performance (FPS) (Ram)
    int FPS = 60;
    long megabyte = (1024L * 1024L);
    Long Ram = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / megabyte;
    TileManger tileM = new TileManger(this);

    //Player
    MouseHandler mH = new MouseHandler(this);
    KeyHandler kH = new KeyHandler();
    public Player player = new Player(this, kH);
    public double speedMod = worldWidth/4;

    //Thread of the game
    Thread gameThread;

    //gamePanel constructor
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // improve rendering performance
        this.addKeyListener(kH);
        this.addMouseWheelListener(mH);
        this.setFocusable(true);
    }

    //zoom in & out function of the game
    public void zoomFunction(int i) {

        int oldWordWidth = tileSize * maxWorldCol;
        int max = 48;
        int min = 30;

        if (tileSize > max){
            tileSize = max;
        } else if (tileSize < min) {
            tileSize = min;
        }

        tileSize += i;

        int newWorldWidth = tileSize * maxWorldCol;
        double multi = (double) newWorldWidth/oldWordWidth;

        double newPlayerWorldX = player.worldX * multi;
        double newPlayerWorldY = player.worldY * multi;

        // without this the player with have infinity speed o-o
        player.speed = (double) newWorldWidth / speedMod;
        player.worldX = newPlayerWorldX;
        player.worldY = newPlayerWorldY;
    }

    // starts a thread
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
                System.out.println("Ram: " + Ram + "MB");

                // runs garbage collection
                Runtime.getRuntime().gc();
                drawCount = 0;
                timer = 0;
            }

        }


    }

    //update information
    public void update() {

        player.update();

    }


    //Standard method to draw on jpanel
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // draws character currently & cast to graphic 2d.
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);


        g2.dispose(); // saves memory by releasing system resources that it was using

    }

}
