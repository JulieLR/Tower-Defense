package gui;

import javax.swing.JButton;
import javax.swing.JFrame;

import gui.Menu.Fond;

import javax.swing.JButton;

public class Settings extends JFrame {

    private Menu menu;
    private Fond fond;

    private JButton difficultyButton, editButton, backButton;
    
    public Settings(Menu menu, Fond fond){
        
        this.menu = menu;
        this.fond= fond;

        setLayout(null);
        setBounds(0, 0, 960, 640);
        this.setTitle("TOWER DEFENSE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        fond.setBounds(0, 0, this.getWidth(), this.getHeight());

        JButton difficultyButton = new JButton("DIFFICULTY");
        this.difficultyButton=difficultyButton;
        difficultyButton.setBounds(this.getWidth()/2, 400, 100, 50);
        //difficultyButton.setBounds(this.getWidth()/3+70, (this.getHeight()/2)-50, 140, 56);
        //difficultyButton.setBackground(null);
        fond.add(difficultyButton);
/* 
        JButton editButton = new JButton("EDIT");
        this.editButton=editButton;
        editButton.setBounds(this.getWidth()/3+70, (this.getHeight()/2+15), 140, 56);
        //editButton.setBackground(null);
        fond.add(editButton);

        JButton backButton = new JButton("BACK");
        this.backButton=backButton;
        backButton.setBounds(this.getWidth()/3+70, (this.getHeight()/2)+80, 140, 56);
        //backButton.setBackground(null);
        fond.add(backButton);  */

        //this.fond.add(quitButton);
        add(fond);
    }

}
