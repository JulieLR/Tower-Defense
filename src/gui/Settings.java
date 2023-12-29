package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JButton;

public class Settings extends JFrame {

    private Menu menu;
    
    public Settings(Menu menu){
        
        this.menu = menu;

        setLayout(null);
        setBounds(0, 0, 960, 640);
        this.setTitle("TOWER DEFENSE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JButton quitButton = new JButton("QUIT");
        quitButton.setBounds(this.getWidth()/3+90, (this.getHeight()/2)+80, 100, 50);

        add(quitButton);
    }

}
