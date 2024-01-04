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

public class SceneMenu extends JFrame implements Frame,MouseListener{

    private Menu menu;
    private SettingsMenu settings;
    private Fond fond;

    private ArrayList<BufferedImage> assets;
    private ArrayList<BufferedImage> mapAssets= new ArrayList<>();
    private BufferedImage mapsImage;

    private JButton confirmButton, backButton, map1Button, map2Button, map3Button;

    private int mapNumber=1;
    private boolean chosen=false;
    
    
    public SceneMenu(Menu menu, SettingsMenu settings, Fond fond){
        
        this.menu = menu;
        this.settings=settings;
        this.fond= fond;
        this.assets=menu.getAssets();
        this.mapsImage = getImage("src/ressources/menu/maps.png");
        addAsset();

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

        this.map1Button=makeButton((this.getWidth()-240)/2-160, (this.getHeight()-480)/2, 240, 160, 0, mapAssets);
        map1Button.addMouseListener(this);
        add(map1Button); 

        this.map2Button=makeButton((this.getWidth()-240)/2+160, (this.getHeight()-480)/2, 240, 160, 3, mapAssets);
        map2Button.addMouseListener(this);
        add(map2Button);
        
        this.map3Button=makeButton((this.getWidth()-240)/2-160, (this.getHeight()-480)/2+200, 240, 160, 5, mapAssets);
        map3Button.addMouseListener(this);
        add(map3Button); 

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

    public void addAsset() {
        for(int ligne=0; ligne<3;ligne++){
            for(int col=0;col<2;col++){
                mapAssets.add(mapsImage.getSubimage(col*240,ligne*160, 240, 160));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()== map1Button){
            map1Button.setIcon(new ImageIcon(mapAssets.get(0)));

            map2Button.setIcon(new ImageIcon(mapAssets.get(3)));
            map3Button.setIcon(new ImageIcon(mapAssets.get(5)));
            
            mapNumber=1;
            this.chosen=true;
        }
        else if((e.getSource()== map2Button)){
            map2Button.setIcon(new ImageIcon(mapAssets.get(2)));
            
            map1Button.setIcon(new ImageIcon(mapAssets.get(1)));
            map3Button.setIcon(new ImageIcon(mapAssets.get(5)));

            mapNumber=2;
            this.chosen=true;
        }
        else if((e.getSource()== map3Button)){
            map3Button.setIcon(new ImageIcon(mapAssets.get(4)));

            map1Button.setIcon(new ImageIcon(mapAssets.get(1)));
            map2Button.setIcon(new ImageIcon(mapAssets.get(3)));
            
            mapNumber=3;
            this.chosen=true;
        }
        System.out.println(mapNumber);
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
            this.menu.setMapNumber(mapNumber);
            if(this.chosen){
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
