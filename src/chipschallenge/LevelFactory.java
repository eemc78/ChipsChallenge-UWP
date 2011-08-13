/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

/**
 *
 * @author patrik
 */
public abstract class LevelFactory {
    public abstract GameLevel getLevel(int n);
    public abstract int getLastLevelNumber();
    public abstract int getLevelNumberByPassword(String pass);
    public final GameLevel getLevelByPassword(String pass) {
        int n = getLevelNumberByPassword(pass);
        if(n == -1)
            return null;
        return getLevel(n);
    }
}
