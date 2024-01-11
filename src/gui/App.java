package gui;

import java.io.IOException;

import javax.swing.JFrame;

import gui.Frames.Menu;

import javax.swing.JButton;
import java.awt.BorderLayout;

public class App extends JFrame{

    private Game game;

    public App(int level,int mapNumber){
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("TOWER DEFENSE");
        setResizable(false);

        this.game = new Game(level,mapNumber, this);

        JButton button = new JButton("TEST");
        button.setBounds(200,700, 100, 50);
        game.add(button,BorderLayout.SOUTH);
        add(game, BorderLayout.NORTH);
        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initInput() {
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