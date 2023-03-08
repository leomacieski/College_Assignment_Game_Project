/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameca;

import Object.ObjKey;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author gmcoa
 */
public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font lato_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message, currentDialogue = "";
    public int messageCount = 0;
    boolean gameEnd = false;
    
    public UI(GamePanel gp){
        
        this.gp = gp;
        lato_40 = new Font("Lato", Font.ROMAN_BASELINE, 40);
        
        ObjKey key = new ObjKey();
        keyImage = key.image;
        
    }
    
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2){
        
        this.g2 = g2;
        
        g2.setFont(lato_40);
        g2.setColor(Color.white);
        
      
        if(gameEnd == true){
            
            drawTBCScreen();
            
        }else{

        //play state
        if(gp.gameState == gp.playState){
                
                g2.drawImage(keyImage, 20, 15, 48 ,48, null);
                g2.drawString("x " + gp.player.getKey(), 60, 50);
                
                if(messageOn == true){

                    drawMessage(message);

                }
                
            }
            //pause state
            if(gp.gameState == gp.pauseState){
                    
                drawPauseScreen();

            }
            // Dialogue
            if(gp.gameState == gp.dialogueState ){
                    
                drawDialogueScreen();

            }
            
        }
        
        
    }
    
    public void drawDialogueScreen(){
    
        int x = 52;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int  height = gp.tileSize *2;
       
       
        drawDialogue(x, y, height, width);
        
         //displays the text inside the window
        y+= gp.tileSize;
        x+= gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString(currentDialogue,x,y);
                
    }
    
    public void drawDialogue(int x, int y, int height,int width){
        
        // sets the colour and opacity as well as the roundness of the rectangule
        Color c = new Color(0,0,0,150);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 30, 30);
    
    }
    public void drawMessage(String message){
        
        int messageLength = getXCenter(message);
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString(message, gp.screenWidth/2 + gp.tileSize - messageLength/2, gp.screenHeight/2 - gp.tileSize);


        messageCount++;

        if(messageCount > 120){
            messageCount = 0;
            messageOn = false;
        }

        
    }
    
    public void drawPauseScreen(){
        
        String text = "PAUSED";
        int x = getXCenter(text);
        int y = gp.screenHeight/2;
        
        g2.drawString(text, x, y);

    }
    
    public void drawTBCScreen(){
        
        String text = "To be continued...";
        int x = getXCenter(text);
        int y = gp.screenHeight/2;
        
        g2.drawString(text, x, y);

    }
    
    //Getting the center coordinate of the screen; We might use it repeatedly so making a constructor for it saves time
    public int getXCenter(String text){
        
        System.out.println(text);
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
        
    }
    
    
}
