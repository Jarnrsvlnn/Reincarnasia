package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player (GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage () {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/resource/BaseUp.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resource/BaseDown.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resource/BaseLeft.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resource/BaseRight.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void update() {
        if(keyH.upPressed == true){
            direction = "up";
            y -= speed;
        }
        else if(keyH.downPressed == true){
            direction = "down";
            y += speed;
        }
        else if(keyH.leftPressed == true){
            direction = "left";
            x -= speed;
        }
        else if(keyH.rightPressed == true){
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction){
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
