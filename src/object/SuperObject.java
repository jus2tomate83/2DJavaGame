package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tilesSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tilesSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tilesSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tilesSize < gp.player.worldY + gp.player.screenY){

            g2.drawImage(image,screenX,screenY,gp.tilesSize,gp.tilesSize,null);
        }
    }

}
