package main;

import entity.Player;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements  Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 16;//16x16 pixels
    final int scale = 3;
    public int tileSize = originalTileSize * scale;

    final int maxScreenHorizonal = 20;
    final int maxScreenVertical = 16;
    final int screenWidth = tileSize * maxScreenHorizonal;
    final int screenHeight = tileSize * maxScreenVertical;
    //FPS
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this,keyH);

    //player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null){

            update();

            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);
        g2.dispose();
    }
}

