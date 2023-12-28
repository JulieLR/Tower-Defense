package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TowerBottomBar extends JPanel{
    Game game;
    // BufferedImage towerManager;
    JButton buttonTowerBlue, towerOrange, towerRed, towerSmall, towerMedium, TowerExtra;
    int cordX, cordY;

    public TowerBottomBar (Game game) {
        this.game=game;
        int x= 150, y=260, width=16, height=29;
        this.buttonTowerBlue= createTowerButton(getImage("src/ressources/towers/towerSprite.png").getSubimage(0, 0, width, height), x, y, width, height);
        add(this.buttonTowerBlue);
        //JButton ButtonTowerBlue= new JButton(" AAAAAAAAAAAA");
        //ButtonTowerBlue.setBounds(x, y,width,height);
        //game.add(ButtonTowerBlue);
        //System.out.println("button");
        //add(this.ButtonTowerBlue);


    } 
    

    private JButton createTowerButton (BufferedImage image, int x, int y, int width, int height) {
        JButton button= new JButton(new ImageIcon(image));
        button.setBounds(x, y, width, height);
        /* button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                JOptionPane.showMessageDialog(TowerBottomBar.this, "clicked");
            }
        }); */
        return button;
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

    public void setCordX (int x) {
        this.cordX=x;
    }

    public void setCordY (int y) {
        this.cordY=y;
    }
    
}