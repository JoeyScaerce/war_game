package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManger {
    GamePanel gp;
    Tile[] tiles;

    public TileManger(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10]; // how many tiles images we want to store
        getTileImage();
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/TestTile.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/TestTile.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/TestTile.png")));

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            g2.drawImage(tiles[1].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x+= gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }

        }
    }
}
