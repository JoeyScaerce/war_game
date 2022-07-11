package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler kh;


    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;
        setDefaultvalues();
        getPlayerImage();
    }

    public void setDefaultvalues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";

    }

    public void update() {
        keyControl();
    }

    public void keyControl() {
        if (kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed) {
            if (kh.upPressed) {
                direction = "up";
                y -= speed;
            }

            if (kh.downPressed) {
                direction = "down";
                y += speed;
            }

            if (kh.leftPressed) {
                direction = "left";
                x -= speed;
            }

            if (kh.rightPressed) {
                direction = "right";
                x += speed;
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
        
        try {
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
        }

    }

    public void draw(Graphics2D g2) {
        //r.setRect(x, y, gp.tileSize, gp.tileSize);
        g2.setColor(Color.red);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
