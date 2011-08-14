/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge.blockreactions;

import chipschallenge.Block;
import chipschallenge.Block.Type;
import chipschallenge.Game;
import chipschallenge.Inventory.Boots;

/**
 *
 * @author patrik
 */
public class FireTo extends BlockReaction {

    private FireTo() {}
    private static FireTo mInstance = null;
    public static synchronized FireTo getInstance() {
        if(mInstance == null)
            mInstance = new FireTo();
        return mInstance;
    }

    public void react(Block moving, Block standing) {
        Game g = Game.getInstance();
        switch (moving.getType()) {
            case CHIP:           
                if(!hasBoots(Boots.FIREBOOTS)) {
                    moving.destroy();
                    standing.replace(createBlock(Type.BURNEDCHIP));
                    g.die("Ooops! Don't step in the fire without fire boots!");
                }
                break;
            case BUG:
            case TEETH:
            case PINKBALL:
            case TANK:
            case BLOB:
                moving.destroy();
                break;
        }
    }

    public boolean canMove(Block moving, Block standing) {
        return !(moving.isA(Type.BUG) || moving.isA(Type.WALKER));
    }

}
