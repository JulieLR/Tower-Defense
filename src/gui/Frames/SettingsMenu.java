package gui.Frames;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import gui.Frames.Menu.Fond;

public class SettingsMenu extends JFrame implements Frame,MouseListener{

    private Menu menu;
    private Fond fond;

    
    private ArrayList<BufferedImage> assets;

    private JButton modeButton, sceneButton, backButton;
    
    public SettingsMenu(Menu menu, Fond fond){
        
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

        this.modeButton = makeButton((this.getWidth()-140)/2, (this.getHeight()-124)/2, 140, 56, 6, assets);
        modeButton.addMouseListener(this);
        add(modeButton);

        this.sceneButton=makeButton((this.getWidth()-140)/2, (this.getHeight()-124)/2+61, 140, 56, 8, assets);
        sceneButton.addMouseListener(this);
        add(sceneButton);

        this.backButton=makeButton((this.getWidth()-140)/2, (this.getHeight()-124)/2+122, 140, 56, 10, assets);
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
            Fond fondMode = menu.new Fond(this.menu.getFondImage(), this.menu.getSceneFond(), (this.getWidth()-this.menu.getSceneFond().getWidth())/2, (this.getHeight()-this.getInsets().top-this.menu.getSceneFond().getHeight())/2);
            new ModeMenu(menu, this, fondMode);
            this.setVisible(false);
        }
        else if((e.getSource()== sceneButton)){
            sceneButton.setIcon(new ImageIcon(assets.get(8)));
            Fond fondScene = menu.new Fond(this.menu.getFondImage(), this.menu.getSceneFond(), (this.getWidth()-this.menu.getSceneFond().getWidth())/2, (this.getHeight()-this.getInsets().top-this.menu.getSceneFond().getHeight())/2);
            new SceneMenu(menu,this, fondScene);
            this.setVisible(false);
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
