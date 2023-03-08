 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import gameca.GamePanel;
import gameca.UtilityTool;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author gmcoa
 */
public class Entity {
    
    GamePanel gp; 
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage up1, up2, up3, left1, left2, left3, right1,right2, right3, down1, down2, down3;
    public BufferedImage NPCAnim1, NPCAnim2;
    public String direction;
    int key = 0;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 80, 80);
    public int solidAreaDefX, solidAreaDefY;
    public boolean collisionOn = false;
    public boolean action = false;
    
    //method to fetch and save the image, we put it in the super class (Entity) so other subclass can use it without typing it again
    public BufferedImage setup(String imagePath){
        
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try{
            
            image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return image;
    }
    
    public void speak(){}
    
    public int getKey() {
        return key;
    }
    
    public Entity(GamePanel gp){
        
        this.gp = gp;
    }
}
