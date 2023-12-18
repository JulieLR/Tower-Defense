package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class App extends JFrame{

    private Game game;
    private BufferedImage image;

    public App(){

        getImage("src/ressources/sprite.png");

        setSize(640,640);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);

        this.game = new Game(image);
        add(game);
    }

    public void getImage(String chemin){
        try {
            this.image = ImageIO.read(new File(chemin));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hey");
        App app = new App();
    }
}