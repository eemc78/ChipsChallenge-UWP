package chipschallenge;

import chipschallenge.Block.Type;
import chipschallenge.Move.Moves;
import java.awt.Image;
import java.awt.image.BufferedImage;
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

    public static Image get(Type type, Moves moves) {
        Map<Moves, Image> moveImages = loadedImages.get(type);
        if(moveImages == null) {
            moveImages = new WeakHashMap<Moves, Image>();
            loadedImages.put(type, moveImages);
        }
        Image im = moveImages.get(moves);
        if(im == null) {
             im = loadImage(type, moves);
             moveImages.put(moves, im);
        }
        return im;
    }
    
    private static Image loadImage(Type type, Moves moves) {

        int x = 0;
        int y = 0;
        //TODO find x and y coordinates for type and moves

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

}
