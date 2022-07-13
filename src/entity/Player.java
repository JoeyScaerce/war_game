package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler kh;

    private Socket socket;
    private int playerId;
    public final int screenX;
    public final int screenY;


    // player constructor
    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        // screen
        //half point of the screen
        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        setDefaultvalues();
        getPlayerImage();
        //connectToServer();
    }

    // sets the default value of players position, speed & direction facing at spawn.
    public void setDefaultvalues() {
        // if you want to start on a specific tile do (gp.tile * x of map text) & (gp.tile * y of map text)
        worldX = 100;
        worldY = 100;

        speed = gp.worldWidth/gp.speedMod;
        direction = "down";

    }

    public void update() {
        keyControl();
    }

    // detect key input from keyboard
    public void keyControl() {
        if (kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed) {
            if (kh.upPressed) {
                direction = "up";
                worldY -= speed;
            }

            if (kh.downPressed) {
                direction = "down";
                worldY += speed;
            }

            if (kh.leftPressed) {
                direction = "left";
                worldX -= speed;
            }

            if (kh.rightPressed) {
                direction = "right";
                worldX += speed;
            }


            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


    }

    // for when we have a sprites
    public void getPlayerImage() {
        
        /*try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    public void draw(Graphics2D g2) {
        //r.setRect(x, y, gp.tileSize, gp.tileSize);
        g2.setColor(Color.red);
        g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, (int)worldX, (int)worldY, gp.tileSize, gp.tileSize, null);
    }

    // multiplayer compatibility
    private void connectToServer() {
        try {
            socket = new Socket("localHost", 1000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutput out = new DataOutputStream(socket.getOutputStream());
            playerId = in.readInt();
            if (playerId == 1) {
                System.out.println("waiting for players to connect...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
