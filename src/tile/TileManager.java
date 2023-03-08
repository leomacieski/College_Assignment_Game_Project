/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import gameca.GamePanel;
import gameca.UtilityTool;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 *
 * @author gmcoa
 */
public class TileManager {
    
    private GamePanel gp;
    private Tile[] tile;
    private Tile[] miscTile;
    private int mapTileNum[][][];
    
    
    public TileManager(GamePanel gp){
        this.gp = gp;
        
        tile = new Tile[20];
        miscTile = new Tile[5];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        loadMap("/maps/WorldMap.txt", 0); //puts the location of file in the method call instead of hardcoding it inside the method for easy map access
        loadMap("/maps/map01.txt", 1);

    }
    
    
    public void getTileImage(){
        
        setTile(1, "Background_Water_Left", true);
        setTile(2, "Background_Water_CornerL", true);
        setTile(3, "Background_Water_Top", true);
        setTile(4, "Background_Water_CornerR", true);
        setTile(5, "Background_Water_Right", true);
        setTile(6, "Background_Water", true);
        setTile(7, "Background_Tree", true);
        setTile(8, "Background_Dirt", false);
        setTile(9, "Background_Grass", false);
        setTile(10, "Background_Bridge", false);
        setTile(11, "Background_Wall", true);
        setTile(12, "Background_WallsideL", true);
        setTile(13, "Background_WallUpCornerL", true);
        setTile(14, "Background_WallUpCornerR", true);
        setTile(15, "Background_WallSideR", true);
        setTile(16, "Background_WallDownCornerL", true);
        setTile(17, "Background_WallDownCornerR", true);

    }
    
    //loads the image of a tile to the tile array, we made this method to remove redundancy on our code as these lines will be used a lot for fetching tile images
    public void setTile(int tileIndex, String tileData, boolean collision){
        
        UtilityTool uTool = new UtilityTool();
        
        try{
            tile[tileIndex] = new Tile();
            tile[tileIndex].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ tileData +".png"));
            tile[tileIndex].image = uTool.scaleImage(tile[tileIndex].image, gp.tileSize, gp.tileSize);
            tile[tileIndex].collision = collision;
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
  
    
    // loads a map data from a text file
    public void loadMap(String mapData, int map){
        
        try{
            
            InputStream is = getClass().getResourceAsStream(mapData);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                
                String line = br.readLine();
                
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
                
                
            }
            br.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
    }
    
    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
             
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
 

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

             //For performance efficiency; Only draw the map that is on the screen instead of drawing the whole map
            if(
                    worldX + gp.tileSize > (gp.player.worldX)- (gp.player.screenX) && 
                    worldX - gp.tileSize < (gp.player.worldX) + (gp.player.screenX) && 
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){ 
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }else if(gp.player.screenX > gp.player.worldX || gp.player.screenY > gp.player.worldY ||
                    rightOffset > (gp.worldWidth - gp.player.worldX) || bottomOffset > (gp.worldHeight - gp.player.worldY)){
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            
            
            worldCol++;
           //when it reaches the end of the row, it changes to the next row 
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
            
        }
        
        
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public Tile[] getTile() {
        return tile;
    }

    public void setTile(Tile[] tile) {
        this.tile = tile;
    }

    public Tile[] getMiscTile() {
        return miscTile;
    }

    public void setMiscTile(Tile[] miscTile) {
        this.miscTile = miscTile;
    }

    public int[][][] getMapTileNum() {
        return mapTileNum;
    }

    public void setMapTileNum(int[][][] mapTileNum) {
        this.mapTileNum = mapTileNum;
    }
    
    
    
    
}
