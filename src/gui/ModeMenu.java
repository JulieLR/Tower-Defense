package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import gui.Menu.Fond;

import javax.swing.JButton;

public class ModeMenu extends JFrame implements Frame,MouseListener{

    private Menu menu;
    private SettingsMenu settings;
    private Fond fond;

    private ArrayList<BufferedImage> assets;

    private JButton confirmButton, backButton, easyButton, normalButton, hardButton, marathonButton, chosen;

    private int modeNumber=1;
    private boolean changed=false;
    
    
    public ModeMenu(Menu menu, SettingsMenu settings, Fond fond){
        
        this.menu = menu;
        this.settings=settings;
        this.fond= fond;
        this.assets=menu.getAssets();

        setLayout(null);
        setBounds(0, 0, 960, 640);
        this.setTitle("TOWER DEFENSE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        fond.setBounds(0, 0, this.getWidth(), this.getHeight());
        
        this.confirmButton=makeButton((this.getWidth()-140)/2-140,this.getHeight()-56*3, 140, 56, 12, assets);
        confirmButton.addMouseListener(this);
        add(confirmButton);

        this.backButton=makeButton((this.getWidth()-140)/2+140, this.getHeight()-56*3, 140, 56, 10, assets);
        backButton.addMouseListener(this);
        add(backButton); 

        this.easyButton=makeButton((this.getWidth()-240)/2-160, (this.getHeight()-360)/2, 240, 160, 15, assets);
        easyButton.addMouseListener(this);
        this.chosen=easyButton;
        add(easyButton); 

        this.normalButton=makeButton((this.getWidth()-240)/2+160, (this.getHeight()-360)/2, 240, 160, 17, assets);
        normalButton.addMouseListener(this);
        add(normalButton); 

        this.hardButton=makeButton((this.getWidth()-240)/2-160, (this.getHeight()-360)/2+140, 240, 160, 19, assets);
        hardButton.addMouseListener(this);
        add(hardButton); 

        this.marathonButton=makeButton((this.getWidth()-240)/2+160, (this.getHeight()-360)/2+140, 240, 160, 21, assets);
        marathonButton.addMouseListener(this);
        add(marathonButton); 


        add(fond);
    }

    public BufferedImage getImage(String chemin){
        try {
            BufferedImage image = ImageIO.read(new File(chemin));
            return image;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public int getNbAsset(JButton j){
        if(j==easyButton){
            return 15;
        }
        else if(j==normalButton){
            return 17;
        }
        else if(j==hardButton){
            return 19;
        }
        else{
            return 21;
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()== easyButton){
            chosen.setIcon(new ImageIcon(assets.get(getNbAsset(chosen))));
            easyButton.setIcon(new ImageIcon(assets.get(14)));
            chosen=easyButton;
            changed=true;
            modeNumber=1;
        }
        else if(e.getSource()== normalButton){
            chosen.setIcon(new ImageIcon(assets.get(getNbAsset(chosen))));
            normalButton.setIcon(new ImageIcon(assets.get(16)));
            chosen=normalButton;
            changed=true;
            modeNumber=2;
        }
        else if(e.getSource()== hardButton){
            chosen.setIcon(new ImageIcon(assets.get(getNbAsset(chosen))));
            hardButton.setIcon(new ImageIcon(assets.get(18)));
            chosen=hardButton;
            changed=true;
            modeNumber=3;
        }
        else if(e.getSource()== marathonButton){
            chosen.setIcon(new ImageIcon(assets.get(getNbAsset(chosen))));
            marathonButton.setIcon(new ImageIcon(assets.get(20)));
            chosen=marathonButton;
            changed=true;
            modeNumber=4;
        }
        System.out.println(modeNumber);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()== confirmButton){
            confirmButton.setIcon(new ImageIcon(assets.get(13)));
        }
        else if((e.getSource()== backButton)){
            backButton.setIcon(new ImageIcon(assets.get(11)));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()== confirmButton){
            confirmButton.setIcon(new ImageIcon(assets.get(12)));
            this.menu.setModeNumber(modeNumber);
            if(changed){
                this.settings.setVisible(true);
                dispose();
            }
        }
        else if((e.getSource()== backButton)){
            backButton.setIcon(new ImageIcon(assets.get(10)));
            this.settings.setVisible(true);
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
