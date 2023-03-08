/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import gameca.GamePanel;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author gmcoa
 */
public class ObjVillageHouse extends Object{
    
    int tempX;
    int tempY;
    
    public ObjVillageHouse(int i) {
        
        name = "Village_House";
        
        if (i == 1){
        
            tempX=210;
            tempY=210;
            try{

                image = ImageIO.read(getClass().getResourceAsStream("/objects/Obj_VillageHouse_1.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }else if (i == 2){
            tempX=180;
            tempY=220;
            try{

                image = ImageIO.read(getClass().getResourceAsStream("/objects/Obj_VillageHouse_2.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
        
        
        collision = true;
    }

    @Override
    //changes the way this object is drawn to the screen
    public void Draw(GamePanel gp, Graphics2D g2) {
        
        screenX = worldX - gp.player.worldX + gp.player.screenX;
        screenY = worldY - gp.player.worldY + gp.player.screenY;
        size = gp.tileSize *7;
        solidArea.x = solidArea.x *5;
        solidArea.y = solidArea.y *5;

        solidArea.height = tempY;
        solidArea.width = tempX;

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
        
        //draws to the map

        g2.drawImage(image, screenX, screenY, size, size, null);


        }
    
    
}
