package gui;

import java.io.IOException;

import javax.swing.JFrame;

import inputs.Keyboard_Listener;
import inputs.Mouse_Listener;

public class App extends JFrame{

    private Game game;
    private Menu menu;
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

    public static void main(String[] args) throws IOException {
        System.out.println("Hey");
        //App app = new App();
        Menu menu = new Menu();
        //app.initInput();
        //Menu menu = new Menu();
        
        
    }
}