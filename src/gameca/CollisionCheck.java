/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameca;

import entity.Entity;

/**
 *
 * @author claud
 */
public class CollisionCheck {
    
    GamePanel gp;

    //Implements a collision method on objects and certain tiles as well as npcs so the player won't run over them
    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity){
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;
        
        int tileNum1, tileNum2;
        // it basically checks is any of the sides has collided to an object and changes the boolean collisionON to true
        switch(entity.direction){
        
            case"up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.getMapTileNum()[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.getMapTileNum()[gp.currentMap][entityRightCol][entityTopRow];
                
                if(gp.tileManager.getTile()[tileNum1].collision == true || gp.tileManager.getTile()[tileNum2].collision == true){
                    entity.collisionOn = true;
                    System.out.println("collision up detected");
                }
                break;
            case"down":
                //the tile size/2 is added to stop the player before it hits the object as the coordinates are for the top left of the player tile
                entityBottomRow = (entityBottomWorldY + (gp.tileSize/2)- entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.getMapTileNum()[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.getMapTileNum()[gp.currentMap][entityRightCol][entityBottomRow];
                
                if(gp.tileManager.getTile()[tileNum1].collision == true || gp.tileManager.getTile()[tileNum2].collision == true){
                    entity.collisionOn = true;
                            System.out.println("collision down detected");
                    
                }
                break;
            case"left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.getMapTileNum()[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.getMapTileNum()[gp.currentMap][entityLeftCol][entityBottomRow];
                
                if(gp.tileManager.getTile()[tileNum1].collision == true || gp.tileManager.getTile()[tileNum2].collision == true){
                    entity.collisionOn = true;
                    System.out.println("collision left detected");
                }
                break;
            case"right":
                //the tile size is added to stop the player before it hits the object as the coordinates are for the top left of the player tile
                entityRightCol = (entityRightWorldX + (gp.tileSize/2)- entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.getMapTileNum()[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.getMapTileNum()[gp.currentMap][entityRightCol][entityBottomRow];
                
                if(gp.tileManager.getTile()[tileNum1].collision == true || gp.tileManager.getTile()[tileNum2].collision == true){
                    entity.collisionOn = true;
                    System.out.println("collision right detected");
                }
                break;
        }
    }
    
    //checks if player is hitting any objects and return the index of said object
    public int checkObject(Entity entity, boolean player){
        
        int index = 999;
        
        for(int i = 0; i<gp.object[1].length; i++){
            if(gp.object[gp.currentMap][i] != null){
                
                //Get entity's solid area position 
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                //get the object's solid area position
                gp.object[gp.currentMap][i].solidArea.x = gp.object[gp.currentMap][i].worldX + gp.object[gp.currentMap][i].solidArea.x;
                gp.object[gp.currentMap][i].solidArea.y = gp.object[gp.currentMap][i].worldY + gp.object[gp.currentMap][i].solidArea.y;
                
                switch(entity.direction){
                    case "up": 
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.object[gp.currentMap][i].solidArea)){ //the intersects method checks if two given rectangles are colliding or not
                            if(gp.object[gp.currentMap][i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down": 
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.object[gp.currentMap][i].solidArea)){
                            if(gp.object[gp.currentMap][i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left": 
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.object[gp.currentMap][i].solidArea)){ 
                            if(gp.object[gp.currentMap][i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right": 
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.object[gp.currentMap][i].solidArea)){ 
                            if(gp.object[gp.currentMap][i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    
                    
                }
                
                //resets the solid area parameters to the default after each loop
                entity.solidArea.x = entity.solidAreaDefX;
                entity.solidArea.y = entity.solidAreaDefY;
                gp.object[gp.currentMap][i].solidArea.x = gp.object[gp.currentMap][i].solidAreaDefX;
                gp.object[gp.currentMap][i].solidArea.y = gp.object[gp.currentMap][i].solidAreaDefY;
                
                
            }
            
        }
        
        
        
        return index;
    }
    
    //similar to checkObject
    public int checkEntity(Entity entity, Entity target){
        
        int index = 999;
        

        //Get entity's solid area position 
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        //get the object's solid area position
        target.solidArea.x = target.worldX + target.solidArea.x;
        target.solidArea.y = target.worldY + target.solidArea.y;

        switch(entity.direction){
            case "up": 
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(target.solidArea)){ //the intersects method checks if two given rectangles are colliding or not
                    entity.collisionOn = true;
                    index = 0;
                    
                }
                break;
            case "down": 
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(target.solidArea)){
                    entity.collisionOn = true;
                    index = 0;
                    
                }
                break;
            case "left": 
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects(target.solidArea)){ 
                    entity.collisionOn = true;
                    index = 0;
                    
                }
                break;
            case "right": 
                entity.solidArea.x += entity.speed;
                if(entity.solidArea.intersects(target.solidArea)){ 
                    entity.collisionOn = true;
                    index = 0;
                    
                }
                break;


        }

        //resets the solid area parameters to the default after each loop
        entity.solidArea.x = entity.solidAreaDefX;
        entity.solidArea.y = entity.solidAreaDefY;
        target.solidArea.x = target.solidAreaDefX;
        target.solidArea.y = target.solidAreaDefY;

        return index;
        
    }
    
    
}
