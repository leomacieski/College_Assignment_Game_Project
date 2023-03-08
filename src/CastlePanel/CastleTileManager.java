///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package CastlePanel;
//
//import java.awt.Graphics2D;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import javax.imageio.ImageIO;
//import tile.Tile;
//
///**
// *
// * @author gmcoa
// */
//public class CastleTileManager {
//    
//    GamePanelCastle gp;
//    Tile[] tile;
//    Tile[] miscTile;
//    int mapTileNum[][];
//    
//    
//    public TileManager(GamePanelCastle gp){
//        this.gp = gp;
//        
//        tile = new Tile[10];
//        miscTile = new Tile[5];
//        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
//        
//        getTileImage();
//        loadMap("/maps/Map_Castle_01.txt"); //puts the location of file in the method call instead of hardcoding it inside the method for easy map access
//        
//    }
//    
//    public void getTileImage(){
//        
//        try{
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background_Wall_0.png"));
//            
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background_Floor_1.png"));
//            
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background_Floor_2.png"));
//            
//            tile[3] = new Tile();
//            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background_Window_2.png"));
//            
//            tile[4] = new Tile();
//            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background_Roof.png"));
//            
//            tile[5] = new Tile();
//            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background_Wall_1.png"));
//            
//            tile[6] = new Tile();
//            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background_Wall_2.png"));
//            
//            tile[7] = new Tile();
//            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background_Wall_3.png"));
//            
//            tile[8] = new Tile();
//            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background_Wall_4.png"));
//            
//            
//            
//            
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        
//        
//    }
//    
//    // loads a map data from a text file
//    public void loadMap(String mapData){
//        
//        try{
//            
//            InputStream is = getClass().getResourceAsStream(mapData);
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            
//            int col = 0;
//            int row = 0;
//            
//            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
//                
//                String line = br.readLine();
//                
//                while(col < gp.maxWorldCol){
//                    String numbers[] = line.split(" ");
//                    
//                    int num = Integer.parseInt(numbers[col]);
//                    
//                    mapTileNum[col][row] = num;
//                    col++;
//                }
//                if(col == gp.maxWorldCol){
//                    col = 0;
//                    row++;
//                }
//                
//                
//            }
//            br.close();
//            
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        
//        
//    }
//    
//    
//    public void draw(Graphics2D g2){
//
//        int worldCol = 0;
//        int worldRow = 0;
////        int x = 0;
//        int y = 0;
//        
//        
//        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
//            
//            int tileNum = mapTileNum[worldCol][worldRow];
//            
//            int worldX = worldCol * gp.tileSize;
//            int worldY = worldRow * gp.tileSize;
//            int screenX = worldX - gp.player.worldX + gp.player.screenX;
//            int screenY = worldY - gp.player.worldY + gp.player.screenY;
//            
//            
//            //Stops the camera from moving when at the edge
//            if(gp.player.screenX > gp.player.worldX){
//                screenX = worldX;
//            }
//            if(gp.player.screenY > gp.player.worldY){
//                
//                screenY = worldY;
//            }
//            int rightOffset = gp.screenWidth - gp.player.screenX;
//            if(rightOffset>gp.worldWidth - gp.player.worldX){
//                screenX = gp.screenWidth - (gp.worldWidth - worldX);
//                
//            }
//            int bottomOffset = gp.screenHeight - gp.player.screenY;
//            if(bottomOffset>gp.worldHeight - gp.player.worldY){
//                screenY = gp.screenHeight - (gp.worldHeight - worldY);
//                
//            }
//            
//            //For performance efficiency; Only draw the map that is on the screen instead of drawing the whole map
//            if(worldX > (gp.player.worldX)- (gp.player.screenX * 2) && worldX < (gp.player.worldX) + (gp.player.screenX* 15)){
////                    && worldY > gp.player.worldY - gp.player.screenY && worldY < gp.player.worldY + gp.player.screenY){  since the castle is side-scrolling, we don'y need camera for Y for now
//                
//                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//            }else if(gp.player.screenX > gp.player.worldX || gp.player.screenY > gp.player.worldY ||
//                    rightOffset > (gp.worldWidth - gp.player.worldX) || bottomOffset > (gp.worldHeight - gp.player.worldY)){
//                
//                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//            }
//            
//            
//            worldCol++;
////            x += gp.tileSize;
//            
//            if(worldCol == gp.maxWorldCol){
//                worldCol = 0;
////                x = 0;
//                worldRow++;
////                y += gp.tileSize;
//            }
//            
//        }
//        
//        
//    }
//    
//    
//    
//}
