package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import gui.Menu.Fond;

import javax.swing.JButton;

public class Settings extends JFrame implements Frame,MouseListener{

    private Menu menu;
    private Fond fond;

    private ArrayList<BufferedImage> assets;

    private JButton modeButton, sceneButton, backButton;
    
    public Settings(Menu menu, Fond fond){
        
        this.menu = menu;
        this.fond= fond;
        this.assets=menu.getAssets();

        setLayout(null);
        setBounds(0, 0, 960, 640);
        this.setTitle("TOWER DEFENSE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        fond.setBounds(0, 0, this.getWidth(), this.getHeight());

        JButton test = new JButton("test");
        test.setBounds(100, 100, 100, 50);
        
        this.modeButton = makeButton((this.getWidth()-140)/2, (this.getHeight()-94)/2, 140, 56, 6, assets);
        modeButton.addMouseListener(this);
        add(modeButton);

        this.sceneButton=makeButton((this.getWidth()-140)/2, (this.getHeight()-94)/2+61, 140, 56, 8, assets);
        sceneButton.addMouseListener(this);
        add(sceneButton);

        this.backButton=makeButton((this.getWidth()-140)/2, (this.getHeight()-94)/2+122, 140, 56, 10, assets);
        backButton.addMouseListener(this);
        add(backButton); 

        add(fond);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()== modeButton){
            modeButton.setIcon(new ImageIcon(assets.get(7)));
        }
        else if((e.getSource()== sceneButton)){
            sceneButton.setIcon(new ImageIcon(assets.get(9)));
        }
        else if((e.getSource()== backButton)){
            backButton.setIcon(new ImageIcon(assets.get(11)));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()== modeButton){
            modeButton.setIcon(new ImageIcon(assets.get(6)));
        }
        else if((e.getSource()== sceneButton)){
            sceneButton.setIcon(new ImageIcon(assets.get(8)));
        }
        else if((e.getSource()== backButton)){
            backButton.setIcon(new ImageIcon(assets.get(10)));
            this.menu.setVisible(true);
            dispose();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    

}
