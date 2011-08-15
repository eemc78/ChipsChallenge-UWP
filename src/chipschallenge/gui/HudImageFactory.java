package chipschallenge.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author wasd
 */
public class HudImageFactory {

    private BufferedImage baseImage;
    private Image[] loadedNumbers = new Image[24];
    private Image hudBg = null;
    private Image itemSlot = null;
    
    private static HudImageFactory instance = null;
    
    private HudImageFactory(){
        try {
            baseImage = ImageIO.read(new File("window.png"));
        } catch (Exception e) {
            System.out.println("Could not find window.png");
            System.exit(-1);
        }
    }

    public static HudImageFactory getInstance(){
        if(instance==null){
            instance = new HudImageFactory();
        }
        return instance;
    }

    /**
     * Returns a digital number for Hud
     *
     * @param number The number to get. -1 for blank. -2 for - (minus sign)
     * @param yellow false = green. true = yellow
     * @return The number as an image
     */
    public Image getNumber(int number, boolean yellow){
        if(number>9 || number<-2)
            throw new IllegalArgumentException("number must be between -2 and 9");

        int code = number+2;
        if(yellow)
            code+=12;

        if(loadedNumbers[code]!=null)
            return loadedNumbers[code];

        int x = 0;
        int y = yellow ? 375 : 353;
        
        if(number>=0){
            x = 15+(number-1)*15;
        }
        else if(number==-1){
            x = 1;
        }
        else{
            //number == -2
            x = 155;
        }

        BufferedImage img = baseImage.getSubimage(x, y, 13, 21);
        loadedNumbers[code] = img;

        return img;
    }

    public Image getHudBackground(){
        if(hudBg==null)
            hudBg = baseImage.getSubimage(339, 26, 154, 300);
        return hudBg;
    }

    public Image getItemSlot(){
        if(itemSlot==null)
            itemSlot = baseImage.getSubimage(352, 248, 32, 32);
        return itemSlot;
    }

}