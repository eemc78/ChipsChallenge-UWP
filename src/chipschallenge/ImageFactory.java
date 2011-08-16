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
import javax.imageio.ImageIO;

/**
 *
 * @author patrik
 */
public class ImageFactory {

    private Map<Type, Map<Moves, Image>> loadedImages = new HashMap<Type, Map<Moves, Image>>();
    private BufferedImage tileset;
    private static ImageFactory instance = null;

    private ImageFactory() {
        try {
            tileset = ImageIO.read(new File("tileset.gif"));
        } catch (Exception e) {
            System.out.println("Could not find tileset.gif");
            System.exit(-1);
        }
    }

    public static ImageFactory getInstance() {
        if (instance == null) {
            instance = new ImageFactory();
        }
        return instance;
    }

    public Image get(Type type, Moves moves, boolean overlay) {
        Map<Moves, Image> moveImages = loadedImages.get(type);
        if (moveImages == null) {
            moveImages = new HashMap<Moves, Image>();
            loadedImages.put(type, moveImages);
        }
        Image im = moveImages.get(moves);
        if (im == null) {
            im = loadImage(type, moves, overlay);
            moveImages.put(moves, im);
        }
        return im;
    }

    private Image loadImage(Type type, Moves moves, boolean overlay) {
        // If overlay, x and y are the overlay coordinates.
        int x = 0;
        int y = 0;
        int maskX = -1;
        int maskY = -1;

        switch (type) {
            case BLOB:
                y = 12;
                x = 5;
                break;
            case BLOCK:
                y = 10;
                x = 0;
                break;
            case BLUEBUTTON:
                y = 8;
                x = 2;
                break;
            case BLUEKEY:
                y = 4;
                x = 6;
                break;
            case BLUELOCK:
                y = 6;
                x = 1;
                break;
            case BLUEWALLREAL:
            case BLUEWALLFAKE:
                y = 14;
                x = 1;
                break;
            case BOMB:
                y = 10;
                x = 2;
                break;
            case BROWNBUTTON:
                y = 7;
                x = 2;
                break;
            case BUG:
                y = 0;
                x = 4;
                break;
            case BURNEDCHIP:
                y = 4;
                x = 3;
                break;
            case CHIP:
                y = 12;
                x = 6;
                break;
            case CLONEBLOCK:
                y = 0;
                x = 1;
                break;
            case CLONEMACHINE:
                y = 1;
                x = 3;
                break;
            case COMPUTERCHIP:
                y = 0;
                x = 2;
                break;
            case DIRT:
                y = 11;
                x = 0;
                break;
            case DROWNEDCHIP:
                y = 3;
                x = 3;
                break;
            case EXIT:
                y = 5;
                x = 1;
                break;
            case FAKEEXIT:
                y = 5;
                x = 1;
                break;
            case FIRE:
                y = 4;
                x = 0;
                break;
            case FIREBOOTS:
                y = 9;
                x = 6;
                break;
            case FIREBALL:
                y = 4;
                x = 4;
                break;
            case FLIPPERS:
                y = 8;
                x = 6;
                break;
            case FLOOR:
                y = 0;
                x = 0;
                break;
            case FORCEFLOOR:
                y = 2;
                x = 1;
                break;
            case RANDOMFORCEFLOOR:
                y = 2;
                x = 3;
                break;
            case GLIDER:
                y = 0;
                x = 5;
                break;
            case GRAVEL:
                y = 13;
                x = 2;
                break;
            case GREENBUTTON:
                y = 3;
                x = 2;
                break;
            case GREENKEY:
                y = 6;
                x = 6;
                break;
            case GREENLOCK:
                y = 8;
                x = 1;
                break;
            case HIDDENWALL:
                y = 0;
                x = 0;
                break;
            case HINT:
                y = 15;
                x = 2;
                break;
            case ICE:
                y = 12;
                x = 0;
                break;
            case ICECORNER:
                y = 10;
                x = 1;
                break;
            case ICEBLOCK:
                y = 10;
                x = 1;
                break;
            case ICESKATES:
                y = 10;
                x = 6;
                break;
            case INVISIBLEWALL:
                y = 0;
                x = 0;
                break;
            case LOCK: // ?
                break;
            case PARAMECIUM:
                y = 0;
                x = 6;
                break;
            case PINKBALL:
                y = 8;
                x = 4;
                break;
            case RECESSEDWALL:
                y = 14;
                x = 2;
                break;
            case REDBUTTON:
                y = 4;
                x = 2;
                break;
            case REDKEY:
                y = 5;
                x = 6;
                break;
            case REDLOCK:
                y = 7;
                x = 1;
                break;
            case SOCKET:
                y = 2;
                x = 2;
                break;
            case SUCTIONBOOTS:
                y = 11;
                x = 6;
                break;
            case SWIMMINGCHIP:
                y = 12;
                x = 3;
                break;
            case TANK:
                y = 12;
                x = 4;
                break;
            case TEETH:
                y = 4;
                x = 5;
                break;
            case TELEPORT:
                y = 11;
                x = 2;
                break;
            case THIEF:
                y = 1;
                x = 2;
                break;
            case THINWALL:
                y = 6;
                x = 0;
                break;
            case THINWALLSE:
                y = 0;
                x = 3;
                break;
            case TOGGLEWALLCLOSED:
                y = 5;
                x = 2;
                break;
            case TOGGLEWALLOPEN:
                y = 6;
                x = 2;
                break;
            case TRAP:
                y = 11;
                x = 2;
                break;
            case WALKER:
                y = 8;
                x = 5;
                break;
            case WALL:
                y = 1;
                x = 0;
                break;
            case WATER:
                y = 3;
                x = 0;
                break;
            case YELLOWKEY:
                y = 7;
                x = 6;
                break;
            case YELLOWLOCK:
                y = 9;
                x = 1;
                break;
        }

        if (overlay) {
            if (x > 4) {
                x += 3;
                maskX = x + 3;
            }
        }

        if ((x >= 4 && !(x == 6 && y >= 4 && y <= 11)) || type == Type.SWIMMINGCHIP || type == Type.ICEBLOCK || type == Type.THINWALL || type == Type.ICECORNER) {
            switch (moves) {
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
        }

        if (type == Type.FORCEFLOOR) {
            switch (moves) {
                case DOWN:
                    x = 0;
                    y = 13;
                    break;
                case LEFT:
                    y += 2;
                    break;
                case RIGHT:
                    y += 1;
                    break;
            }
        }

        // TODO: Handle masks if overlay == true
        BufferedImage img = tileset.getSubimage(x * 32, y * 32, 32, 32);

        Map<Moves, Image> movesMap = loadedImages.get(type);
        if (movesMap == null) {
            movesMap = new HashMap<Moves, Image>();
            movesMap.put(moves, img);
            loadedImages.put(type, movesMap);
        } else {
            movesMap.put(moves, img);
        }

        return img;
    }

    private BufferedImage imageToBufferedImage(Image image) {

        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();

        return bufferedImage;

    }

    private Image makeColorTransparent(BufferedImage im, final Color color) {
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
