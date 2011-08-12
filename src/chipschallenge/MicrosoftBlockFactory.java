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
public class MicrosoftBlockFactory extends BlockFactory {

    private MicrosoftBlockFactory(){}
    private static MicrosoftBlockFactory mInstance = null;
    public static synchronized MicrosoftBlockFactory getInstance() {
        if(mInstance == null)
            mInstance = new MicrosoftBlockFactory();
        return mInstance;
    }

    public Block get(Type type, Moves facing) {
        Block ret = new Block();
        switch(type) {
            case BLOB:
                break;
            case BLOCK:
                break;
            case BLUEBUTTON:
                break;
            case BLUEKEY:
                break;
            case BLUELOCK:
                break;
            case BLUEWALL:
                break;
            case BOMB:
                break;
            case BROWNBUTTON:
                break;
            case BUG:
                break;
            case BURNEDCHIP:
                break;
            case CHIP:
                break;
            case CLONEBLOCK:
                break;
            case CLONEMACHINE:
                break;
            case COMPUTERCHIP:
                break;
            case DIRT:
                break;
            case DROWNEDCHIP:
                break;
            case EXIT:
                break;
            case FAKEEXIT:
                break;
            case FIRE:
                break;
            case FIREBOOTS:
                break;
            case FIREBALL:
                break;
            case FLIPPERS:
                break;
            case FLOOR:
                break;
            case FORCEFLOOR:
                break;
            case RANDOMFORCEFLOOR:
                break;
            case GLIDER:
                break;
            case GRAVEL:
                break;
            case GREENBUTTON:
                break;
            case GREENKEY:
                break;
            case GREENLOCK:
                break;
            case HIDDENWALL:
                break;
            case HINT:
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
                break;
            case REDKEY:
                break;
            case REDLOCK:
                break;
            case SOCKET:
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
                break;
            case YELLOWLOCK:
                break;
        }
        return ret;
    }
}
