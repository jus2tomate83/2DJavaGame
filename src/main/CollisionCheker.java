package main;

import entity.Entity;

public class CollisionCheker {
    GamePanel gp;

    public CollisionCheker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTiles(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tilesSize;
        int entityRightCol = entityRightWorldX/gp.tilesSize;
        int entityTopRaw = entityTopWorldY/gp.tilesSize;
        int entityBottomRaw = entityTopWorldY/gp.tilesSize;

        int tilNum1;
        int tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRaw = (entityTopWorldY - entity.speed)/gp.tilesSize;
                tilNum1 =gp.tileM.mapTileNum[entityLeftCol][entityTopRaw];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRaw];
                if (gp.tileM.tile[tilNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRaw = (entityBottomWorldY - entity.speed)/gp.tilesSize;
                tilNum1 =gp.tileM.mapTileNum[entityLeftCol][entityBottomRaw];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRaw];
                if (gp.tileM.tile[tilNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tilesSize;
                tilNum1 =gp.tileM.mapTileNum[entityLeftCol][entityTopRaw];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRaw];
                if (gp.tileM.tile[tilNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tilesSize;
                tilNum1 =gp.tileM.mapTileNum[entityRightCol][entityTopRaw];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRaw];
                if (gp.tileM.tile[tilNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }



    }

}
