package gui;

import java.awt.LayoutManager;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

import inputs.Keyboard_Listener;
import inputs.Mouse_Listener;

public class App extends JFrame{

    private Game game;
    private Menu menu;
    private Mouse_Listener mouseListener; 
    private Keyboard_Listener keyboardlistener;

    public App(int level,int mapNumber){
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("TOWER DEFENSE");
        //setResizable(false);

        this.game = new Game(level,mapNumber, this);
        //add(game);

        //this.pack();
        JButton button = new JButton("TEST");
        button.setBounds(200,700, 100, 50);
        game.add(button,BorderLayout.SOUTH);
        add(game, BorderLayout.NORTH);
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
        javax.swing.SwingUtilities.invokeLater(
        new Runnable() {
            public void run() {
                Menu menu = new Menu();
            }
        }
    );
        
    }
}