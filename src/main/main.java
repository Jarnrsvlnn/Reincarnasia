package main;

import javax.swing.JFrame;

public class main {
    public static void main(String[] args){

        //setting up the window
        JFrame window = new JFrame();//instantiate the JFrame object
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//a function of JFrame which closes the window if x is selected
        window.setResizable(false);//sets the window size to fixed
        window.setTitle("Reincarnasia");//sets title

        GamePanel gamePanel = new GamePanel();//instantiation of GamePanel object
        window.add(gamePanel);//adds the GamePanel object into the window/JFrame
        window.pack();

        window.setLocationRelativeTo(null);//sets the location to null so that the program can be at any position
        window.setVisible(true);//sets the window to be visible

        gamePanel.startGameThread();
    }
}
