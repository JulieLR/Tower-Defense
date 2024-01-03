package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame implements Frame,MouseListener{

    private BufferedImage buttonImage;
    private BufferedImage fondMenuImage;
    private BufferedImage fondImage;
    private BufferedImage settingsFond;
    private BufferedImage sceneFond;

    private int mapNumber=1;
    private int modeNumber=1;

    private ArrayList<BufferedImage> assets = new ArrayList<>();

    private JButton quitButton,playButton,settingsButton;

    public Menu(){

        this.fondMenuImage = getImage("src/ressources/menu/background.png");
        this.fondImage= getImage("src/ressources/menu/fond.png");
        this.settingsFond= getImage("src/ressources/menu/fondSettings.png");
        this.buttonImage = getImage("src/ressources/menu/buttons2.png");
        this.sceneFond= getImage("src/ressources/menu/tableau.png");

        setLayout(null);
        setBounds(0, 0, 960, 640);
        this.setTitle("TOWER DEFENSE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        addAsset();

        Fond fond = new Fond(this.fondImage, this.fondMenuImage,(this.getWidth()-this.fondMenuImage.getWidth())/2, (this.getHeight()-this.getInsets().top-this.fondMenuImage.getHeight())/2);
        fond.setBounds(0, 0, this.fondImage.getWidth(), this.fondImage.getHeight());

        /* JButton playButton = new JButton();
        this.playButton=playButton;
        playButton.setBounds((this.getWidth()-140)/2, (this.getHeight()-112)/2, 140, 56);
        playButton.setBackground(null);
        playButton.setIcon(new ImageIcon(assets.get(0)));
        makeTransparentBackground(playButton);
        playButton.addMouseListener(this); */

        this.playButton = makeButton((this.getWidth()-140)/2, (this.getHeight()-143)/2, 140, 56, 0, assets);
        playButton.addMouseListener(this);
        add(playButton);

        this.settingsButton=makeButton((this.getWidth()-140)/2, (this.getHeight()-143)/2+61, 140, 56, 2, assets);
        settingsButton.addMouseListener(this);
        add(settingsButton);

        this.quitButton=makeButton((this.getWidth()-140)/2, (this.getHeight()-143)/2+122, 140, 56, 4, assets);
        quitButton.addMouseListener(this);
        add(quitButton); 

        this.add(fond);

    }

    public class Fond extends JPanel{

        private BufferedImage fondMenu;
        private BufferedImage fond;
        private int x, y;

        public Fond(BufferedImage fond,BufferedImage fondMenu,int x,int y){
            this.fond= fond;
            this.fondMenu=fondMenu;
            this.x=x;
            this.y=y;

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fond, 0, 0, fond.getWidth(), fond.getHeight(), null);
            g.drawImage(fondMenu, x, y, fondMenu.getWidth(), fondMenu.getHeight(), null);
        }

        public BufferedImage getFond(){
            return this.fond;
        }
    }

    public BufferedImage getFondImage(){
        return this.fondImage;
    }

    public BufferedImage getSceneFond() {
        return sceneFond;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }

    public void setModeNumber(int modeNumber) {
        this.modeNumber = modeNumber;
    }

    public void playAction(){
        playButton.setIcon(new ImageIcon(assets.get(1)));
        new App(modeNumber,mapNumber);
        dispose();

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

    public ArrayList<BufferedImage> getAssets() {
        return assets;
    }

    public void addAsset() {
        for(int ligne=0; ligne<11;ligne++){
            for(int col=0;col<2;col++){
                assets.add(buttonImage.getSubimage(col*140,ligne*56, 140, 56));
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()== playButton){
            playButton.setIcon(new ImageIcon(assets.get(1)));
        }
        else if((e.getSource()== settingsButton)){
            settingsButton.setIcon(new ImageIcon(assets.get(3)));
        }
        else if((e.getSource()== quitButton)){
            quitButton.setIcon(new ImageIcon(assets.get(5)));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()== playButton){
            playButton.setIcon(new ImageIcon(assets.get(0)));
            playAction();
        }
        else if((e.getSource()== settingsButton)){
            settingsButton.setIcon(new ImageIcon(assets.get(2)));
            Fond fondSet = new Fond(this.fondImage, this.settingsFond, (this.getWidth()-this.settingsFond.getWidth())/2, (this.getHeight()-this.getInsets().top-this.settingsFond.getHeight())/2);
            new SettingsMenu(this, fondSet);
            this.setVisible(false);
        }
        else if((e.getSource()== quitButton)){
            quitButton.setIcon(new ImageIcon(assets.get(4)));
            this.dispose();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}