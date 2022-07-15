package main;

import entity.Entity;

public class CollisionDetector {

    GamePanel gp;

    public CollisionDetector(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTileCollision(Entity entity) {

        double entityLeftWorldX = (entity.worldX + entity.collisionBox.x);
        double entityRightWorldX =  (entity.worldX + entity.collisionBox.x + entity.collisionBox.width);
        double entityTopWorldY =  (entity.worldY + entity.collisionBox.y);
        double entityBottomWorldY =  (entity.worldY + entity.collisionBox.y + entity.collisionBox.height);

        double entityLeftCol = entityLeftWorldX/gp.tileSize;
        double entityRightCol = entityRightWorldX/gp.tileSize;
        double entityTopRow = entityTopWorldY/gp.tileSize;
        double entityBottomRow = entityBottomWorldY/ gp.tileSize;

        double tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                tileNum1 = gp.tileM.mapTileNum[(int) entityLeftCol][(int) entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[(int) entityRightCol][(int) entityTopRow];

                if (gp.tileM.tiles[(int) tileNum1].collision || gp.tileM.tiles[(int) tileNum2].collision) {
                    entity.collisionOn = true;
                }

                break;
            case "down":
                tileNum1 = gp.tileM.mapTileNum[(int) entityLeftCol][(int) entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[(int) entityRightCol][(int) entityBottomRow];

                if (gp.tileM.tiles[(int) tileNum1].collision || gp.tileM.tiles[(int) tileNum2].collision) {
                    entity.collisionOn = true;
                }

                break;
            case"left":
                tileNum1 = gp.tileM.mapTileNum[(int) entityLeftCol][(int) entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[(int) entityLeftCol][(int) entityBottomRow];

                if (gp.tileM.tiles[(int) tileNum1].collision || gp.tileM.tiles[(int) tileNum2].collision) {
                    entity.collisionOn = true;
                }

                break;
            case"right":
                tileNum1 = gp.tileM.mapTileNum[(int) entityRightCol][(int) entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[(int) entityRightCol][(int) entityBottomRow];

                if (gp.tileM.tiles[(int) tileNum1].collision || gp.tileM.tiles[(int) tileNum2].collision) {
                    entity.collisionOn = true;
                }

                break;
        }
    }
}
