import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;


public class GamePanel extends JPanel implements  Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 16;//16x16 pixels
    final int scale = 3;
    final int tileSize = originalTileSize * scale;

    final int maxScreenHorizonal = 20;
    final int maxScreenVertical = 16;
    final int screenWidth = tileSize * maxScreenHorizonal;
    final int screenHeight = tileSize * maxScreenVertical;

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null){
            System.out.println("Game is running!");
        }
    }
}

