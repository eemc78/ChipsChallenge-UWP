package chipschallenge;

import chipschallenge.Block.Type;
import chipschallenge.Move.Moves;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author patrik
 */
public class ImageFactory {

    private static Map<Type, Map<Moves, Image>> loadedImages = new WeakHashMap<Type, Map<Moves, Image>>();
    private static BufferedImage tileset;
    static{
        try {
            tileset = ImageIO.read(new File("tileset.bmp"));
        } catch (Exception e) {
            System.out.println("Could not find tileset.bmp");
            System.exit(-1);
        }
    }

    public static Image get(Type type, Moves moves, boolean overlay) {
        Map<Moves, Image> moveImages = loadedImages.get(type);
        if(moveImages == null) {
            moveImages = new WeakHashMap<Moves, Image>();
            loadedImages.put(type, moveImages);
        }
        Image im = moveImages.get(moves);
        if(im == null) {
             im = loadImage(type, moves, overlay);
             moveImages.put(moves, im);
        }
        return im;
    }
    
    private static Image loadImage(Type type, Moves moves, boolean overlay) {
        // If overlay, x and y are the overlay coordinates.
        int x = 0;
        int y = 0;
        int maskX = -1;
        int maskY = -1;

        switch(type) {
            case BLOB:
                y = 12; x = overlay ? 8 : 5;
                break;
            case BLOCK:
                y = 10; x = 0;
                break;
            case BLUEBUTTON:
                y = 8; x = 2;
                break;
            case BLUEKEY:
                y = 4; x = 6;
                break;
            case BLUELOCK:
                y = 6; x = 1;
                break;
            case BLUEWALL:
                y = 14; x = 1;
                break;
            case BOMB:
                y = 10; x = 2;
                break;
            case BROWNBUTTON:
                y = 7; x = 2;
                break;
            case BUG:
                break;
            case BURNEDCHIP:
                y = 4; x = 3;
                break;
            case CHIP:
                break;
            case CLONEBLOCK:
                y = 0; x = 1;
                break;
            case CLONEMACHINE:
                y = 1; x = 3;
                break;
            case COMPUTERCHIP:
                y = 0; x = 2;
                break;
            case DIRT:
                y = 11; x = 0;
                break;
            case DROWNEDCHIP:
                y = 3; x = 3;
                break;
            case EXIT:
                y = 5; x = 1;
                break;
            case FAKEEXIT:
                y = 5; x = 1;
                break;
            case FIRE:
                y = 4; x = 0;
                break;
            case FIREBOOTS:
                break;
            case FIREBALL:
                break;
            case FLIPPERS:
                break;
            case FLOOR:
                y = 0; x = 0;
                break;
            case FORCEFLOOR:
                break;
            case RANDOMFORCEFLOOR:
                y = 2; x = 3;
                break;
            case GLIDER:
                break;
            case GRAVEL:
                y = 13; x = 2;
                break;
            case GREENBUTTON:
                y = 3; x = 2;
                break;
            case GREENKEY:
                y = 6; x = 6;
                break;
            case GREENLOCK:
                y = 8; x = 1;
                break;
            case HIDDENWALL:
                y = 0; x = 0;
                break;
            case HINT:
                y = 15; x = 2;
                break;
            case ICE:
                break;
            case ICEBLOCK:
                break;
            case ICESKATES:
                break;
            case INVISIBLEWALL:
                break;
            case LOCK:
                break;
            case PARAMECIUM:
                break;
            case PINKBALL:
                break;
            case RECESSEDWALL:
                break;
            case REDBUTTON:
                y = 4; x = 2;
                break;
            case REDKEY:
                y = 5; x = 6;
                break;
            case REDLOCK:
                y = 7; x = 1;
                break;
            case SOCKET:
                y = 2; x = 2;
                break;
            case SUCTIONBOOTS:
                break;
            case SWIMMINGCHIP:
                break;
            case TANK:
                break;
            case TEETH:
                break;
            case TELEPORT:
                break;
            case THIEF:
                break;
            case THINWALL:
                break;
            case TOGGLEWALLCLOSED:
                break;
            case TOGGLEWALLOPEN:
                break;
            case TRAP:
                break;
            case WALKER:
                break;
            case WALL:
                break;
            case WATER:
                break;
            case YELLOWKEY:
                y = 7; x = 6;
                break;
            case YELLOWLOCK:
                y = 9; x = 1;
                break;
        }

        switch(moves) {
            case UP:
                break;
            case DOWN:
                y += 2;
                break;
            case LEFT:
                y += 1;
                break;
            case RIGHT:
                y += 3;
                break;
        }

        BufferedImage img = tileset.getSubimage(x*32, y*32, 32, 32);
        
        Map<Moves, Image> movesMap = loadedImages.get(type);
        if(movesMap==null){
            movesMap = new HashMap<Moves, Image>();
            movesMap.put(moves, img);
            loadedImages.put(type, movesMap);
        }
        else{
            movesMap.put(moves, img);
        }
        
        return img;
    }

      private static BufferedImage imageToBufferedImage(Image image) {

        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();

        return bufferedImage;

    }

    private static Image makeColorTransparent(BufferedImage im, final Color color) {
        ImageFilter filter = new RGBImageFilter() {

                // the color we are looking for... Alpha bits are set to opaque
                public int markerRGB = color.getRGB() | 0xFF000000;

                public final int filterRGB(int x, int y, int rgb) {
                        if ((rgb | 0xFF000000) == markerRGB) {
                                // Mark the alpha bits as zero - transparent
                                return 0x00FFFFFF & rgb;
                        } else {
                                // nothing to do
                                return rgb;
                        }
                }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }

}
