package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    //SCREEN SETTINGS
    final int originalTilesSize = 16;  //16x16 tiles
    final int scale = 3;

    final int tilesSize = originalTilesSize * scale; // 48x48 tiles
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tilesSize * maxScreenCol; //768 pixels
    final int screenHeight= tilesSize * maxScreenRow; //576 pixels

    Thread gameThread;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }




}
