package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class App extends JFrame{

    private Game game;

    public App(){
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("TOWER DEFENSE");
        //setResizable(false);

        this.game = new Game();
        add(game);

        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Hey");
        App app = new App();
        app.game.initInput();
        //Menu menu = new Menu();
    }
}