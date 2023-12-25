package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import inputs.Keyboard_Listener;
import inputs.Mouse_Listener;

public class App extends JFrame{

    private Game game;
    private Mouse_Listener mouseListener; 
    private Keyboard_Listener keyboardlistener;

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

    public void initInput() {
        mouseListener= new Mouse_Listener();
        keyboardlistener= new Keyboard_Listener();

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addKeyListener(keyboardlistener);

        requestFocus();
    }

    public static void main(String[] args) {
        System.out.println("Hey");
        App app = new App();
        app.initInput();
        //Menu menu = new Menu();
        //TowerManager towerManager= new TowerManager();
        //TowerSerialize towerSerialize= new TowerSerialize(towerManager);
    }
}