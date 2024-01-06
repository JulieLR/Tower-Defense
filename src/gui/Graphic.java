package gui;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface Graphic {

    public void addAsset();
    public void drawImages(Graphics g);

    public default BufferedImage getImage(String chemin){
        try {
            BufferedImage image = ImageIO.read(new File(chemin));
            return image;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public default BufferedImage rotate(BufferedImage img, double angle) {
        int w = img.getWidth();    
        int h = img.getHeight();
    
        BufferedImage rotated = new BufferedImage(w, h, img.getType());  
        Graphics2D graphic = rotated.createGraphics();

        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawImage(img, null, 0, 0);
        graphic.dispose();
        
        return rotated;
    }
}
