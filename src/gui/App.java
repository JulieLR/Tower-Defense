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

        getImage("src/ressources/map/sprite1.png");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("TOWER DEFENSE");
        //setResizable(false);

        this.game = new Game(image);
        add(game);

        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
        //Menu menu = new Menu();
    }
}