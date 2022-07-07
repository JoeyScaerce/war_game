package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    //Screen setting
    int originalTileSize = 16; // 16x16 tile
    int scale = 3;

    int tileSize = originalTileSize*scale;

    // set how many tile are displayed on screen
    int maxScreenCol = 16;
    int maxScreenRow = 12;
    int screenWidth = tileSize*maxScreenCol;
    int screenHeight = tileSize*maxScreenRow;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true); // improve rendering performance
    }
}
