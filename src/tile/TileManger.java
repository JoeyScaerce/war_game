package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManger {
    GamePanel gp;
    public Tile[] tiles;
    public int mapTileNum[][];

    public TileManger(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[15]; // how many tiles images we want to store
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        //
        loadMap("/res/map/map01.txt");
    }

    // get image from res & set collision if true
    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/GrassTile.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/MountainTile.png")));
            // set tile collision
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/tile2.png")));
            tiles[2].collision = true;

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    // gets information from world map text file
    public void loadMap(String path) {
        try {
            // gets the map from resources maps
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" "); // give regular expression. making it so we get the number one by one

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                    if (col == gp.maxWorldCol) {
                        col = 0;
                        row++;
                    }

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Draws the world map
    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

            int tileNum = mapTileNum[col][row];

            // position on the map
            int worldX = col * gp.tileSize;
            int worldY = row * gp.tileSize;
            // where on screen we draw tile to offset & have the player at the center of the screen
            double ScreenX = worldX - gp.player.worldX + gp.player.screenX;
            double ScreenY = worldY - gp.player.worldY + gp.player.screenY;

            // renders world around the player. improve performance for bigger worlds
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tiles[tileNum].image, (int) ScreenX, (int)ScreenY, gp.tileSize, gp.tileSize, null);
            }


            col++;
            //x+= gp.tileSize;

            if (col == gp.maxWorldCol) {
                col = 0;
                //x = 0;
                row++;
                //y += gp.tileSize;
            }

        }
    }


}
