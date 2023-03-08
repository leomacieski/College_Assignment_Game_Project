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
public class Player extends Entity{
    
    KeyHandler keyHandler;
    int imageUpdateSpeed; //determines the rate of image update depending on whether the player is running or not
    
    public final int screenX;
    public final int screenY;
    
    public Player(GamePanel gp, KeyHandler keyHandler){
        
        super(gp); //we call the constructor of the super class and pass it into gp
        this.keyHandler = keyHandler;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        worldX = gp.tileSize;
        worldY = gp.tileSize*7;
        
        solidArea = new Rectangle(8,16,gp.tileSize/2,gp.tileSize/2);
        
        solidAreaDefX = solidArea.x;
        solidAreaDefY = solidArea.y;
        setDefaultValues();
        getImage();
        
    }
    
    public void setDefaultValues(){
        //where the player is drawn on the screen when the game starts
        worldX = gp.tileSize*16;
        worldY = gp.tileSize*47;
        speed = 5;
        direction = "down";
        
    }
    
    public void getImage(){
        
        
        up1 = setup("/warrior/Walk_Up_1");
        up2 = setup("/warrior/Walk_Up_2");
        up3 = setup("/warrior/Walk_Up_3");
        down1 = setup("/warrior/Walk_Down_1");
        down2 = setup("/warrior/Walk_Down_2");
        down3 = setup("/warrior/Walk_Down_3");
        left1 = setup("/warrior/Walk_Left_1");
        left2 = setup("/warrior/Walk_Left_2");
        left3 = setup("/warrior/Walk_Left_3");
        right1 = setup("/warrior/Walk_Right_1");
        right2 = setup("/warrior/Walk_Right_2");
        right3 = setup("/warrior/Walk_Right_3");
       
        
    }

    
    
    public void update(){
        //The player runs/moves faster when space is pressed
        if(keyHandler.shiftPressed == true){
            speed = 15;
        }else{speed = 5;}
        
        if(keyHandler.upPressed == true || keyHandler.downPressed == true  //only animates the sprite when a key is pressed
                || keyHandler.leftPressed == true || keyHandler.rightPressed == true){
            
            
            if(keyHandler.upPressed == true){
                direction = "up";
            }else if(keyHandler.downPressed == true){
                direction = "down";
            }else if(keyHandler.leftPressed == true){
                direction = "left";
            }else if(keyHandler.rightPressed == true){
                direction = "right";
            }
            
            
            
            //this checks if there is tile collision and if it happpens the player cannot move in that direction
            collisionOn = false;
            gp.cCheck.checkTile(this);
            
            //this checks if there is an object collision and prevents the player to walk through it
            int objectIndex = gp.cCheck.checkObject(this, true);
            
            //checks npc collision
            int npcIndex = gp.cCheck.checkEntity(this, gp.wise);
            interactNPC(npcIndex);
            
            if(keyHandler.actionPressed == true){ //do an action if the action key is pressed
                System.out.println(key);
                interactObject(objectIndex); 
            }
            

            
            if(collisionOn == false){
                
                switch(direction){
                    case"up":
                        worldY -= speed;
                        break;
                    case"down":
                        worldY += speed;
                        break;
                    case"left":
                        worldX -= speed;
                        break;
                    case"right":
                        worldX += speed;
                        break;
                        
                }
            }
           
            spriteCounter++;
            
            //checks if shift is pressed and doubles the animation speed and rate of sound effect playback if true
            if(keyHandler.shiftPressed == true){
                imageUpdateSpeed = 5;
            }else{
                imageUpdateSpeed = 10;
            }
            if(spriteCounter > imageUpdateSpeed){
                switch(spriteNum){
                    case 1:
                        spriteNum = 2;
                        gp.playSoundEffect(1); //adds footstep sound
                        break;
                    case 2:
                        spriteNum = 3;
                        break;
                    case 3:
                        spriteNum = 4;
                        gp.playSoundEffect(1);
                        break;
                    case 4:
                        spriteNum = 1;
                        break;
                }
                spriteCounter = 0;
            }
            
        }
        
        
       
    }
    
    //Interacts with the object depending what object is interacted with
    public void interactObject(int i){
        
        if(i!=999){  //999 is just a default value we put to determine that no object is touched
            
            
            String objName = gp.object[gp.currentMap][i].name;
            
            switch(objName){
                case "Chest": //opens a chest and collects a key 
                    if(gp.object[gp.currentMap][i].isOpen() == false){
                        try{
                        gp.object[gp.currentMap][i].image = ImageIO.read(getClass().getResourceAsStream("/Objects/Obj_Chest_Opened.png"));
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                        gp.ui.showMessage("You obtained a key!");
                        gp.object[gp.currentMap][i].setOpen(true);
                        
                        key++;
                    }
                    
                    break;
                case "Gate": //Opens a gate only if the player has enough key
                    if(key>0 && gp.object[gp.currentMap][i].isOpen() == false){
                        gp.ui.showMessage("Gate unlocked!");
                        gp.object[gp.currentMap][i].setCollision(false);
                        try{
                            gp.object[gp.currentMap][i].image = ImageIO.read(getClass().getResourceAsStream("/Objects/Obj_Gate_Opened.png"));
                            gp.object[gp.currentMap][i].setOpen(true);
                            
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                        
                        
                        key--;
                    }else if(key<1 && gp.object[gp.currentMap][i].isOpen() == false) {
                        gp.ui.showMessage("The gate is locked!");
                    }
                    break;
                
            }
            
            
        }
        
    }
    
    //checks if the player interacts with the npc
    public void interactNPC(int i){
        
        if(i!=999){
            
            gp.gameState = gp.dialogueState;
            gp.wise.speak();
        }
       
        
    }
    
    public void draw(Graphics2D g2){
        

        BufferedImage image = null;
        
        switch(direction){
            case "up": 
            if(keyHandler.upPressed == true){ //check if the keys are pressed and sets the image to a default pose if unpressed.
                switch(spriteNum){
                    case 1:
                        image = up1;
                        break;
                    case 2:
                        image = up2;
                        break;
                    case 3:
                        image = up1;   
                        break; 
                    case 4:
                        image = up3;
                        break;
                }
            }else{image = up1;}
            break;
            
            case "down": 
                if(keyHandler.downPressed == true){ //check if the keys are pressed and sets the image to a default pose if unpressed.
                switch(spriteNum){
                    case 1:
                        image = down1;
                        break;
                    case 2:
                        image = down2;
                        break;
                    case 3:
                        image = down1;   
                        break; 
                    case 4:
                        image = down3;
                        break;
                }
            }else{image = down1;}
            break;
            case "left":
                if(keyHandler.leftPressed == true){ //check if the keys are pressed and sets the image to a default pose if unpressed.
                switch(spriteNum){
                    case 1:
                        image = left1;
                        break;
                    case 2:
                        image = left2;
                        break;
                    case 3:
                        image = left1;   
                        break; 
                    case 4:
                        image = left3;
                        break;
                }
                }else{image = left1;}
                break;
            case "right":
                if(keyHandler.rightPressed == true){ //check if the keys are pressed and sets the image to a default pose if unpressed.
                switch(spriteNum){
                    case 1:
                        image = right1;
                        break;
                    case 2:
                        image = right2;
                        break;
                    case 3:
                        image = right1;   
                        break; 
                    case 4:
                        image = right3;
                        break;
                }
            }else{image = right1;}
                break;
            
            
        }
        
        int x = screenX;
        int y = screenY;
        
        if(screenX > worldX){
            x = worldX;
        }
        if(screenY > worldY){
            y = worldY;
        }
        int rightOffset = gp.screenWidth - screenX;
        if(rightOffset>gp.worldWidth - worldX){
            x = gp.screenWidth - (gp.worldWidth - worldX);

        }
        int bottomOffset = gp.screenHeight - screenY;
        if(bottomOffset>gp.worldHeight - worldY){
            y = gp.screenHeight - (gp.worldHeight - worldY);

        }
        
        
        
        g2.drawImage(image, x, y, null);
        
        
        
        
    }
    
    
}
