/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import gameca.GamePanel;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author claud
 */
public class ObjCastle extends Object {

    public ObjCastle() {
        
        name = "Castle";       
        try{
        
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Obj_Castle.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
        collision = true;
    }

    @Override
    //changes the way this object is drawn to the screen
    public void Draw(GamePanel gp, Graphics2D g2) {
        
        screenX = worldX - gp.player.worldX + gp.player.screenX;
        screenY = worldY - gp.player.worldY + gp.player.screenY;
        size = gp.tileSize *5;
        solidArea.x = solidArea.x *5;
        solidArea.y = solidArea.y *5;
        solidArea.height = 400;
        solidArea.width = 400 ;


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
