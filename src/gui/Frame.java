package gui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public interface Frame {
    
    public default void makeTransparentBackground(JButton j){
        j.setOpaque(false);
        j.setContentAreaFilled(false);
        j.setBorderPainted(false);
    }

    public default JButton makeButton(int x, int y, int width, int height, int index, ArrayList<BufferedImage> assets){
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        button.setBackground(null);
        button.setIcon(new ImageIcon(assets.get(index)));
        makeTransparentBackground(button);
        return button;
    }
}
