package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame implements Frames {

    private JLabel fond;
    private JPanel boutons= new JPanel();
    private JButton playButton, quitButton;
    private BufferedImage buttonImage;
    private BufferedImage fondImage;
    private ArrayList<BufferedImage> assets = new ArrayList<>();

    public Menu(){
        
        /*this.buttonImage=getImage("src/ressources/buttons.png");
        this.fondImage=getImage("src/ressources/background.png");

        //setSize(350,470);
        setSize(700,700);
        setBounds(0, 0, 700, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(51, 50, 61));

        this.fond=new JLabel(new ImageIcon(this.fondImage), JLabel.CENTER);   
        addAsset();
        assets.add(fondImage);
        
        this.fond.setBounds(60, 20, 564, 672);
        fond.setIcon(new ImageIcon(assets.get(6)));

        playButton = new JButton();
        playButton.setIcon(new ImageIcon(assets.get(0)));
        playButton.setBorder(null);
        playButton.setBackground(null);
        playButton.setVisible(true);

        quitButton = new JButton();
        quitButton.setIcon(new ImageIcon(assets.get(1)));

        quitButton.setVisible(true);
        quitButton.setBorder(null);
        quitButton.setBackground(null);


        //boutons.setLayout(new GridLayout(2, 1));
        //boutons.setSize(new Dimension(50, 50));
        boutons.setBounds(200,200,120,20);
        boutons.add(playButton);
        boutons.setVisible(true);
        //boutons.add(quitButton);

        this.getContentPane().add(boutons);
        this.getContentPane().add(fond);
        //add(boutons);*/


        setLayout(null);
        setBounds(0, 0, 700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setBounds(this.getWidth()/4, this.getHeight()/4, 282, 336);
        panel.setBackground(Color.BLACK);

        JButton playButton = new JButton("PLAY");
        playButton.setBounds(90, 120, 100, 50);
        panel.add(playButton);

        JButton quitButton = new JButton("QUIT");
        quitButton.setBounds(90, 180, 100, 50);
        panel.add(quitButton);

        this.add(panel);

    }

    public BufferedImage getImage(String chemin){
        BufferedImage img=null;
        try {
            img = ImageIO.read(new File(chemin));
        }catch(IOException e){
            e.printStackTrace();
        }
        return img;
    }

    @Override
    public void addAsset() {
        for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                if(j!=1){
                    assets.add(buttonImage.getSubimage(i*140, j*56, 140, 56));
                }
            }
        }
    }

}