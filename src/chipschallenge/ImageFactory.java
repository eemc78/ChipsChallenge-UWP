/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;
import chipschallenge.Block.Type;
import chipschallenge.Move.Moves;
import java.awt.Image;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 * @author patrik
 */
class ImageFactory {
    private static Map<Type, Map<Moves, Image>> loadedImages = new WeakHashMap<Type, Map<Moves, Image>>();

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
        return null;
    }

}
