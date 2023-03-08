///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package CastlePanel;
//
//import entity.Player;
//import gameca.KeyHandler;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import javax.swing.JPanel;
//import tile.TileManager;
//
///**
// *
// * @author gmcoa
// */
//public class CastlePanel extends JPanel implements Runnable{
//    
//    //Screen settings
//    
//    final int originalTileSize = 16; //16x16 tile 
//    final int scale = 6;
//    
//    public final int tileSize = originalTileSize * scale; //48x48
//    public final int maxScreenCol = 16;
//    public final int maxScreenRow = (maxScreenCol * 9)/16;
//    public final int screenWidth = tileSize * maxScreenCol;  //1536 pixels
//    public final int screenHeight = tileSize * maxScreenRow; //864 pixels
//    
//    //World Settings
//    public final int maxWorldCol = 96;  //number of columns in the map tile
//    public final int maxWorldRow = 9;
//    public final int worldWidth = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize* maxWorldRow;
//    
//    
//    //FPS
//    int FPS = 60;
//    
//    
//    TileManager tileManager = new TileManager(this);
//    KeyHandler keyHandler = new KeyHandler();
//    Thread gameThread;
//    public Player player = new Player(this, keyHandler);
//    MiscCandle candle = new MiscCandle(this);
//    
//    
//    
//    
//    
//    public GamePanelCastle(){
//        
//        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
//        this.setBackground(Color.CYAN);
//        this.setDoubleBuffered(true); //all drawing from this component will be done in an offscreen painting buffer; increases render performance
//        this.addKeyListener(keyHandler);
//        this.setFocusable(true); //frame can be focused to input
//        
//        
//    }
//
//    public void startGameThread(){
//        
//        gameThread = new Thread(this);
//        gameThread.start();
//        
//    }
//    
//    
//    
//    @Override
////    ===== SLEEPER GAME LOOP METHOD ==========
////    ==Some says sleeper method is not as accurate
////    public void run() {
////        
////        //Game Loop; Core of games
////        
////        this.requestFocus(); //automatically sets the focus on the window after running
////        
////        double drawInterval = 1000000000/FPS; //draw the screen every 0.01666 seconds
////        double nextDrawTime = System.nanoTime() + drawInterval;
////        
////        while(gameThread!=null){ //as long as the thread exists, the method is executed
////            
////            //1. UPDATE: Update information such as character position
////            update();
////            
////            //2. DRAW: Draw the screen with the updated info
////            repaint();
////            
////            
////            try { 
////                double remainingTime = nextDrawTime - System.nanoTime();
////                remainingTime = remainingTime/1000000; //convert it to milliseconds
////                
////                if(remainingTime < 0){
////                    remainingTime = 0;
////                }
////                
////                
////                Thread.sleep((long) remainingTime); //The thread will not do anything during the remaining type of the tick/frame
////                
////                nextDrawTime += drawInterval;
////                
////                
////            } catch (InterruptedException ex) {
////                Logger.getLogger(GamePanelCastle.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        }
////        
////    
////    }
//    // ============= Delta Game Loop Method =================
//    
//    public void run(){
//        
//        //Game Loop; Core of games
//        this.requestFocus(); //automatically sets the focus on the window after running
//        
//        
//        
//        double drawInterval = 1000000000/FPS;//draw the screen every 0.01666 seconds
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//        long timer = 0;
//        int drawCount = 0;
//        
//        while(gameThread != null){//as long as the thread exists, the method is executed
//            
//            currentTime = System.nanoTime();
//            
//            delta += (currentTime - lastTime) / drawInterval;
//            
//            timer += (currentTime - lastTime);
//            
//            lastTime = currentTime;
//            
//            if(delta >= 1){
//                //1. UPDATE: Update information such as character position
//                update(); 
//                
//                //2. DRAW: Draw the screen with the updated info
//                repaint();
//                
//                
//                delta--; //resets the delta
//                
//                drawCount++;
//                
//            }
//            
//            if(timer >= 1000000000){
//                System.out.println("FPS: " + drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
//            
//            
//            
//        }
//        
//        
//        
//    }
//    
//    public void update(){
//        
////        candle.update();
//         player.update();
//    }
//    
//    //sets the bound limits of the object based on the screen size
//    public static int clamp(int var, int min, int max){
//        
//        if(var >= max){
//            return var = max;
//        }else if(var <= min){
//            return var = min;
//        }else{return var;}
//        
//    }
//    
//    public void paintComponent(Graphics g){
//        
//        super.paintComponent(g);
//        
//        Graphics2D g2 = (Graphics2D)g;
//        
//        tileManager.draw(g2);
////        candle.draw(g2, tileSize*2,tileSize*5);
////        candle.draw(g2, tileSize*6,tileSize*5);
////        candle.draw(g2, tileSize*10,tileSize*5);
////        candle.draw(g2, tileSize*14,tileSize*5);
//        player.draw(g2);
//        
//        g2.dispose(); //release any resources that this is using
//        
//    }
//    
//    
//}
