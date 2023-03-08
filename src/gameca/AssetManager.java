/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameca;

import Object.ObjCastle;
import Object.ObjChest;
import Object.ObjGate;
import Object.ObjVillageHouse;
import entity.NPCWise;
/**
 *
 * @author gmcoa
 */
public class AssetManager {
    
    GamePanel gp;
    
    public AssetManager(GamePanel gp){
        
        this.gp = gp;
    }
    
    //Manages the objects like creating one and putting them somwhere on the map
    public void setObj(){
        
        int mapNum = 0;
        
        gp.object[mapNum][0] = new ObjCastle();
        gp.object[mapNum][0].worldX = (13/2) * gp.tileSize;
        gp.object[mapNum][0].worldY = 3 * gp.tileSize;
        
        gp.object[mapNum][1] = new ObjChest();
        gp.object[mapNum][1].worldX = 40 * gp.tileSize;
        gp.object[mapNum][1].worldY = 21 * gp.tileSize;
        
        gp.object[mapNum][2] = new ObjGate();
        gp.object[mapNum][2].worldX = 8 * gp.tileSize;
        gp.object[mapNum][2].worldY = 14 * gp.tileSize;
        
        gp.object[mapNum][3] = new ObjVillageHouse(1);
        gp.object[mapNum][3].worldX = 13 * gp.tileSize;
        gp.object[mapNum][3].worldY = 40 * gp.tileSize;
        
        gp.object[mapNum][4] = new ObjVillageHouse(1);
        gp.object[mapNum][4].worldX = 20 * gp.tileSize;
        gp.object[mapNum][4].worldY = 45 * gp.tileSize;
        
        gp.object[mapNum][5] = new ObjVillageHouse(1);
        gp.object[mapNum][5].worldX = 17 * gp.tileSize;
        gp.object[mapNum][5].worldY = 37 * gp.tileSize;
        
        gp.object[mapNum][6] = new ObjVillageHouse(2);
        gp.object[mapNum][6].worldX = 20 * gp.tileSize;
        gp.object[mapNum][6].worldY = 41 * gp.tileSize;
        
        gp.object[mapNum][7] = new ObjVillageHouse(2);
        gp.object[mapNum][7].worldX = 12 * gp.tileSize;
        gp.object[mapNum][7].worldY = 36 * gp.tileSize;
        
        gp.object[mapNum][8] = new ObjVillageHouse(2);
        gp.object[mapNum][8].worldX = 15 * gp.tileSize;
        gp.object[mapNum][8].worldY = 44 * gp.tileSize;
    }
    
    
}
