/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

import chipschallenge.Block.Type;
import chipschallenge.Move.Moves;

/**
 *
 * @author patrik
 */
public abstract class BlockFactory {
    public final Block get(Type type) {
        return get(type, Moves.DOWN);
    }
    public abstract Block get(Type type, Moves direction);
}
