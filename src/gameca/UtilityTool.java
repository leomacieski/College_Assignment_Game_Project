/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameca;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author gmcoa
 */
public class UtilityTool {
    
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType()); //creates a blank canvass with the dimensions provided
        Graphics2D g2 = scaledImage.createGraphics(); //saves whatever the g2 draws into the scaledImage
        g2.drawImage(original, 0, 0, width, height, null);  //g2 draws the given image with the provided size and saves it into the scaled image
        g2.dispose();
        
        
        return scaledImage;
    }
    
    
}
