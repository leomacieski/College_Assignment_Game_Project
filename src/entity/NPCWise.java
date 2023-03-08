/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import gameca.GamePanel;
import gameca.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author gmcoa
 */
public final class NPCWise extends Entity{
    
    
    public int screenX;
    public int screenY;
    
    public NPCWise(GamePanel gp){
        
        super(gp);
        
        solidAreaDefX = solidArea.x;
        solidAreaDefY = solidArea.y;
        collisionOn = true;
        
        setDefaultValues();
        getImage();
        
    }
    // text to be displayed whenever the player hits the NPC
    public void speak(){
    
        gp.ui.currentDialogue = "Get a Key from a chest in order to open the gate";
    }
    public void getImage(){
        
        try{
            NPCAnim1 = ImageIO.read(getClass().getResourceAsStream("/NPCWise/NPC_Anim_1.png"));
            NPCAnim2 = ImageIO.read(getClass().getResourceAsStream("/NPCWise/NPC_Anim_2.png"));
          
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    public void setDefaultValues(){
        //where the player is drawn on the screen when the game starts
        worldX = gp.tileSize*7;
        worldY = gp.tileSize*15;
        
    }
    
    public void update(){
        
        spriteCounter++;
        if(spriteCounter > 15){
            switch(spriteNum){
                case 1:
                    spriteNum = 2;
                    break;
                case 2:
                    spriteNum = 1;
                    break;
            }
            spriteCounter = 0;
        }

    }
    
    public void draw(Graphics2D g2, int A, int B){
       
        screenX = worldX - gp.player.worldX + gp.player.screenX;
        screenY = worldY - gp.player.worldY + gp.player.screenY;

        BufferedImage image = null;
        
        
        if(spriteNum == 1){
            image = NPCAnim1;
        }
        if(spriteNum == 2){
            image = NPCAnim2;
        }
        
        //Keeps the NPC in place when the player go past the middle part of the screen
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
        
        g2.drawImage(image, screenX, screenY, A , B, null);
        
        
        
        
    }
        
}
        
