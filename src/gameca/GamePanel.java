
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameca;

//import entity.MiscCandle;
import Object.ObjCastle;
import Object.Object;
import entity.Entity;
import entity.NPCWise;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tile.TileManager;

/**
 *
 * @author gmcoa
 */
public class GamePanel extends JPanel implements Runnable{
    
    //Screen settings
    
    final int originalTileSize = 16; //16x16 tile 
    final int scale = 5;
    
    public final int tileSize = originalTileSize * scale; //48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 9;
    public final int screenWidth = tileSize * maxScreenCol;  //1280 pixels
    public final int screenHeight = tileSize * maxScreenRow; //72 pixels

    
    //World Settings
    public final int maxWorldCol = 50;  //number of columns in the map tile
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize*maxWorldCol;
    public final int worldHeight = tileSize*maxWorldRow;
    public final int maxMap = 2;
    public int currentMap = 0;
    
    
    //FPS
    int FPS = 60;
    
    //System
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler(this);
    Sound soundEffect = new Sound();
    Sound music = new Sound();
    public CollisionCheck cCheck = new CollisionCheck(this);
    public AssetManager aManager = new AssetManager(this);
    public UI ui = new UI(this);
    Thread gameThread;
    
    //Entities and Objects
    public Player player = new Player(this, keyHandler);
    public NPCWise wise = new NPCWise(this);
    public Object object[][]= new Object[maxMap][10] ;  //array to store as many as 10 objects to be shown at a time
    
    
    
    //Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    
    
    
    
    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //all drawing from this component will be done in an offscreen painting buffer; increases render performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true); //frame can be focused to input
        
        
    }
    
    //Starts the thread
    public void startGameThread(){
        
        gameThread = new Thread(this);
        gameThread.start();
        
    }
    
    
    public void setGame(){
    
        aManager.setObj();
        gameState = playState;
        
    }
    
    
    
    @Override
//    ===== SLEEPER GAME LOOP METHOD ==========
//    ==Some says sleeper method is not as accurate
//    ==We didn't delete this part as we want to show the difference between the two game loop method
//    public void run() {
//        
//        //Game Loop; Core of games
//        
//        this.requestFocus(); //automatically sets the focus on the window after running
//        
//        double drawInterval = 1000000000/FPS; //draw the screen every 0.01666 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        
//        while(gameThread!=null){ //as long as the thread exists, the method is executed
//            
//            //1. UPDATE: Update information such as character position
//            update();
//            
//            //2. DRAW: Draw the screen with the updated info
//            repaint();
//            
//            
//            try { 
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000; //convert it to milliseconds
//                
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//                
//                
//                Thread.sleep((long) remainingTime); //The thread will not do anything during the remaining type of the tick/frame
//                
//                nextDrawTime += drawInterval;
//                
//                
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//    
//    }
    
    
    // ============= Delta Game Loop Method =================
    
    public void run(){
        
        //Game Loop; Core of games
        this.requestFocus(); //automatically sets the focus on the window after running
        
        
        
        double drawInterval = 1000000000/FPS;//draw the screen every 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){//as long as the thread exists, the method is executed
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            
            timer += (currentTime - lastTime);
            
            lastTime = currentTime;
            
            if(delta >= 1){
                //1. UPDATE: Update information such as character position
                update(); 
                
                //2. DRAW: Draw the screen with the updated info
                repaint();
                
                
                delta--; //resets the delta
                
                drawCount++;
                
            }
            
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
            
            
            
        }
        
        
        
    }
    
    public void update(){
        
        
        if(gameState == playState){
            wise.update();
            player.update();
            
        }
        if(gameState == pauseState){
            //nothing
            
        }
        
    }
    // clamp method was rplaced with collision check
    
    //sets the bound limits of the object based on the screen size
//    public static int clamp(int var, int min, int max){
//        
//        if(var >= max){
//            return var = max;
//        }else if(var <= min){
//            return var = min;
//        }else{return var;}
//        
//    }
    
    //Draws the components
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        //Debug mode
        long drawStart = 0;
        if(keyHandler.checkDrawTime == true){
            drawStart = System.nanoTime();
        }
        
        
       //draws tile 
        tileManager.draw(g2);

        //draws object
        for(int i = 0; i <object[1].length; i ++){
        //checks if the array is not empty before drawing
            if (object[currentMap][i] != null){
                //draws the object
                object[currentMap][i].Draw(this, g2);
            }
        }
        
        
        
        //draws entities
        
        wise.draw(g2, tileSize, tileSize);
        player.draw(g2);
        
        
        
        //UI
        ui.draw(g2);
        
        //Debug
        if(keyHandler.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
        
        
        g2.dispose(); //release any resources that this is using
        
    }
    
    public void playMusic(int i){
        
        music.setFile(i);
        music.play();
        music.loop();
        
    }
    
    public void stopMusic(){
        music.stop();
    }
    
    public void playSoundEffect(int i){
        
        soundEffect.setFile(i);
        soundEffect.play();
    }
  
    
    
    
}
