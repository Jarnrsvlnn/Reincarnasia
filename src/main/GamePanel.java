package main;

import entity.Player;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements  Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 16;//sets the pixels into 16x16, final means it cant be changed
    final int scale = 3;
    public int tileSize = originalTileSize * scale;//16x3 = 48 tiles per item or character from the game
    final int maxScreenHorizonal = 20; //sets the max horizontal view from the window
    final int maxScreenVertical = 16; //sets the max vertical view from the window
    final int screenWidth = tileSize * maxScreenHorizonal;//sets the screen width to 960 in tiles
    final int screenHeight = tileSize * maxScreenVertical;//sets the screen height to 768 in tiles

    //FPS
    int FPS = 60;//sets the frames per second to 60

    Thread gameThread;//thread is a method that allows to us compute/control time needed for FPS
    KeyHandler keyH = new KeyHandler();//keyhandler is a function that enables the user to control the character based on the keys
    Player player = new Player(this,keyH);//instantiate the player object with "this" (this class), and keyH.
                                              //control the player using keyH

    //player's default position on the window
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

