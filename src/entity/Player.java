package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler kh;

    public final int screenX;
    public final int screenY;


    // player constructor
    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        // screen
        //set the view to half point of the screen
        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        // set player collision box
        // x & y will be broken if tile size is bigger because it wouldn't be the center. need to find better way then having static numbers
        collisionBox = new Rectangle(8, 16, gp.tileSize - gp.originalTileSize,  gp.tileSize - gp.originalTileSize);

        setDefaultvalues();
        getPlayerImage();
        //connectToServer();
    }

    // changes speed base on zoom in & out
    public double speedMulti() {
        return (double) gp.worldWidth/4;
    }

    // sets the default value of players position, speed & direction facing at spawn.
    public void setDefaultvalues() {
        // if you want to start on a specific tile do (gp.tile * x of map text) & (gp.tile * y of map text)
        worldX = 100;
        worldY = 100;

        speed = gp.worldWidth/speedMulti();
        direction = "down";

    }

    // check collision
    public void collisionCheck() {

    }

    // keyboard input
    public void update() {
        keyControl();
        collisionCheck();
    }

    // detect key input from keyboard. moves the player. & checks for collison
    // update mouse input when I get a chance. unable to move diagonal any more only at angles
    public void keyControl() {
        collisionOn = false;
        gp.cD.checkTileCollision(this);

        if (kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed) {
            if (kh.upPressed) {
                direction = "up";
            }
            if (kh.downPressed) {
                direction = "down";
            }
            if (kh.leftPressed) {
                direction = "left";
            }
            if (kh.rightPressed) {
                direction = "right";
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

            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
        }





    }

        /*if (!collisionOn) {
            switch (direction) {
                case "up":
                    break;
                case "down":
                    break;
                case "left":
                    break;
                case "right":
                    break;
            }
        }*/

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
        // switch sprite base on direction the key pressed
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


}
