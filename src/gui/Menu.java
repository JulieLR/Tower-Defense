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

public class Menu extends JFrame implements MouseListener{

    private BufferedImage buttonImage;
    private BufferedImage fondMenuImage;
    private BufferedImage fondImage;
    private ArrayList<BufferedImage> assets = new ArrayList<>();

    private JButton quitButton,playButton,settingsButton;

    public Menu(){

        this.fondMenuImage = getImage("src/ressources/menu/background.png");
        this.fondImage= getImage("src/ressources/menu/fond.png");
        this.buttonImage = getImage("src/ressources/menu/buttons2.png");

        setLayout(null);
        setBounds(0, 0, 960, 640);
        this.setTitle("TOWER DEFENSE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        addAsset();

        Fond fond = new Fond(this.fondImage, this.fondMenuImage,this.getWidth()/3, this.getHeight()/4);
        fond.setBounds(0, 0, this.fondImage.getWidth(), this.fondImage.getHeight());

        JButton playButton = new JButton();
        this.playButton=playButton;
        playButton.setBounds(this.getWidth()/3+70, (this.getHeight()/2)-50, 140, 56);
        playButton.setBackground(null);
        playButton.setIcon(new ImageIcon(assets.get(0)));
        makeTransparentBackground(playButton);
        playButton.addMouseListener(this);
        fond.add(playButton);

        JButton settingsButton = new JButton();
        this.settingsButton=settingsButton;
        settingsButton.setBounds(this.getWidth()/3+70, (this.getHeight()/2+15), 140, 56);
        settingsButton.setBackground(null);
        settingsButton.setIcon(new ImageIcon(assets.get(2)));
        makeTransparentBackground(settingsButton);
        settingsButton.addMouseListener(this);
        fond.add(settingsButton);

        JButton quitButton = new JButton();
        this.quitButton=quitButton;
        quitButton.setBounds(this.getWidth()/3+70, (this.getHeight()/2)+80, 140, 56);
        quitButton.setBackground(null);
        quitButton.setIcon(new ImageIcon(assets.get(4)));
        makeTransparentBackground(quitButton);
        quitButton.addMouseListener(this);
        fond.add(quitButton); 

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
    }

    public void playAction(){
        playButton.setIcon(new ImageIcon(assets.get(1)));
        new App();
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

    public void addAsset() {
        for(int ligne=0; ligne<3;ligne++){
            for(int col=0;col<2;col++){
                assets.add(buttonImage.getSubimage(col*140,ligne*56, 140, 56));
            }
        }
    }

    public void makeTransparentBackground(JButton j){
        j.setOpaque(false);
        j.setContentAreaFilled(false);
        j.setBorderPainted(false);
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
            new Settings(this);
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