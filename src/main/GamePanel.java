package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTilesSize = 16;  //16x16 tiles
    final int scale = 3;

    public final int tilesSize = originalTilesSize * scale; // 48x48 tiles
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tilesSize * maxScreenCol; //768 pixels
    public final int screenHeight= tilesSize * maxScreenRow; //576 pixels

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionCheker cCheker =new CollisionCheker(this);
    public AssetsSetter aSetter = new AssetsSetter(this);
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int WorldWidth = tilesSize * maxWorldCol;
    public final int WorldHeight = tilesSize * maxWorldRow;

    //FPS
    int FPS = 60;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){

        aSetter.setObject();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //0.01666 sec
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                // 1: Update information sutch as character position
                Update();
                //2; draw the screen with the update information
                repaint();
                delta --;
                drawCount++;

            }
            if (timer>=1000000000){
                System.out.println("FPS : " + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }

    }
    public void Update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //debug
        long drawStart = 0;
        if(keyH.debugMode == true){
            drawStart = System.nanoTime();
        }
        //TILE
        tileM.draw(g2);

        //OBJECT
        for(int i = 0; i < obj.length; i++) {
            if(obj[i]!= null){
                obj[i].draw(g2,this);
            }
        }

        //PLAYER
        player.draw(g2);

        //DEBUG
        if(keyH.debugMode == true){
            long drawEnd =System.nanoTime();
            long passed = drawEnd - drawStart;
            System.out.println("Draw Time :" + passed);
        }

        g2.dispose();
    }
}
