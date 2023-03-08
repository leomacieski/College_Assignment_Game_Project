/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import gameca.GamePanel;
import gameca.UtilityTool;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author claud
 */
public class Object {
    
    //This class acts as a blueprint for all the objects that will be used in the game
    
    public BufferedImage image;
    public String name;
    public boolean open = false;
    public boolean collision = false;
    public int worldX, worldY,screenX,screenY,size;
    
    public Rectangle solidArea = new Rectangle(0,0,80,80);
    
    //default values to be reset after the collision loop
    public int solidAreaDefX = solidArea.x;
    public int solidAreaDefY = solidArea.y;
    public int solidAreaDefHeigh = solidArea.height;
    public int solidAreaDefWidth = solidArea.width;
    
    UtilityTool uTool = new UtilityTool();
    
    
    public void Draw(GamePanel gp, Graphics2D g2){
    
        screenX = worldX - gp.player.worldX + gp.player.screenX;
        screenY = worldY - gp.player.worldY + gp.player.screenY;
        size = gp.tileSize;
         
        //Stops the camera from moving when at the edge
        if(gp.player.screenX > gp.player.worldX){
            screenX = worldX;
        }
        if(gp.player.screenY > gp.player.worldY){

            screenY = worldY;
        }
        int rightOffset = gp.screenWidth - gp.player.screenX;
        if(rightOffset>gp.worldWidth - gp.player.worldX){
            screenX = gp.screenWidth - (gp.worldWidth - worldX);

        }
        int bottomOffset = gp.screenHeight - gp.player.screenY;
        if(bottomOffset>gp.worldHeight - gp.player.worldY){
            screenY = gp.screenHeight - (gp.worldHeight - worldY);

        }
        
        //same draw method as the tile manager 
           
        if(
                worldX + gp.tileSize > (gp.player.worldX)- (gp.player.screenX) && 
                worldX - gp.tileSize < (gp.player.worldX) + (gp.player.screenX) && 
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){ 

            g2.drawImage(image, screenX, screenY, size, size, null);


        }else if(gp.player.screenX > gp.player.worldX || gp.player.screenY > gp.player.worldY ||
                rightOffset > (gp.worldWidth - gp.player.worldX) || bottomOffset > (gp.worldHeight - gp.player.worldY)){

            g2.drawImage(image, screenX, screenY, size, size, null);
        }
    }

    public boolean isCollision() {
        return collision;
    }
    
    
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    
    
    
}
