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
public class ObjChest extends Object{
    
    public ObjChest(){
        
        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/Obj_Chest_Closed.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
        collision = true;
        
    }

}
