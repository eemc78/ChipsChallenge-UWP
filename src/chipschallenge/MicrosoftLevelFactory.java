/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

import chipschallenge.Block.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Level factory which uses CHIPS.DAT to create levels.
 * @author patrik
 */
public class MicrosoftLevelFactory extends LevelFactory {

    private Map<Block.Type,Byte> msType = new HashMap<Block.Type,Byte>();

    private void addBlockTypes(Type... blockTypes) {
        byte i = 0;
        for(Block.Type t : blockTypes) {
            if(t != null)
                msType.put(t, i);
            i++;
        }
    }

    private MicrosoftLevelFactory() {
        //TODO: Check if CHIPS.DAT exists
    }

    private static MicrosoftLevelFactory mInstance = null;
    public static synchronized MicrosoftLevelFactory getInstance() {
        if(mInstance == null)
            mInstance = new MicrosoftLevelFactory();
        return mInstance;
    }

    public GameLevel getLevel(int n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getLastLevelNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getLevelNumberByPassword(String pass) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
