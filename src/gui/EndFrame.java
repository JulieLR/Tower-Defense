package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EndFrame extends JFrame implements Frame,Graphic,MouseListener{

    private Game game;
    private BufferedImage buttonImage;
    private BufferedImage sceneFond;

    private int mode;
    private int actualLevel;
    private boolean win;

    private ArrayList<BufferedImage> assets = new ArrayList<>();

    private JButton homeButton,nextButton,restartButton;

    public EndFrame(Game game,int actualLevel, int mode, boolean win){

        this.buttonImage = getImage("src/ressources/menu/buttons2.png");
        this.sceneFond= getImage("src/ressources/menu/tableau2.png");

        setLayout(null);
        setBounds(0, 0, sceneFond.getWidth()/2+15, sceneFond.getHeight()/2+38);
        this.setTitle("TOWER DEFENSE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        addAsset();

        this.game=game;
        this.actualLevel=actualLevel;
        this.mode=mode;
        this.win=win;

        Fond fond = new Fond(this.sceneFond);
        fond.setBounds(0, 0, this.getWidth(), this.getHeight());
        fond.setBackground(Color.BLACK);

        if(!win){
            this.restartButton = makeButton((this.getWidth()-140)/2, (this.getHeight()-120)/2, 140, 56, 4, assets);
            restartButton.addMouseListener(this);
            add(restartButton);

            this.homeButton=makeButton((this.getWidth()-140)/2, (this.getHeight()-120)/2+80, 140, 56, 2, assets);
            homeButton.addMouseListener(this);
            add(homeButton);
        }
        else if(actualLevel==2){
            this.homeButton=makeButton((this.getWidth()-140)/2, (this.getHeight()-120)/2+40, 140, 56, 2, assets);
            homeButton.addMouseListener(this);
            add(homeButton);
        }
        else{
            this.nextButton = makeButton((this.getWidth()-140)/2, (this.getHeight()-120)/2, 140, 56, 0, assets);
            nextButton.addMouseListener(this);
            add(nextButton);

            this.homeButton=makeButton((this.getWidth()-140)/2, (this.getHeight()-120)/2+80, 140, 56, 2, assets);
            homeButton.addMouseListener(this);
            add(homeButton);
        }

        add(fond);

    }

    public class Fond extends JPanel{

        private BufferedImage fond;

        public Fond(BufferedImage fond){
            this.fond=fond;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fond, 0, 0, fond.getWidth()/2, fond.getHeight()/2, null);
        }

    }


    public ArrayList<BufferedImage> getAssets() {
        return assets;
    }

    public void addAsset() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<2; j++) {
                this.assets.add(this.buttonImage.getSubimage(140*j, 616+(56*i), 140, 56));
            } 
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()== homeButton){
            homeButton.setIcon(new ImageIcon(assets.get(3)));
        }
        else if((e.getSource()== nextButton) && win){
            nextButton.setIcon(new ImageIcon(assets.get(1)));
        }
        else if((e.getSource()== restartButton) && !win){
            restartButton.setIcon(new ImageIcon(assets.get(5)));
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()== homeButton){
            homeButton.setIcon(new ImageIcon(assets.get(2)));
            game.getApp().dispose();
            new Menu();
            dispose();
        }
        else if((e.getSource()== nextButton) && win){
            nextButton.setIcon(new ImageIcon(assets.get(0)));
            this.game.resetAll(mode, actualLevel+1);
            this.game.setEnd(false);
            this.game.setEndFrameOn(false);
            dispose();
        }
        else if((e.getSource()== restartButton) && !win){
            restartButton.setIcon(new ImageIcon(assets.get(4)));
            this.game.resetAll(mode, actualLevel);
            this.game.setEnd(false);
            this.game.setEndFrameOn(false);
            dispose();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void drawImages(Graphics g) {

    }

}
