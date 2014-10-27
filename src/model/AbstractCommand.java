package model;

import java.util.Stack;

/**
 * The abstraction of the Command design pattern. has an effect and it at can
 * either apply or remove that effect. It also enforces the variant th
 *
 * @author ngraves
 *
 */
public abstract class AbstractCommand implements CommandPattern {

    /*
     * applyEffect := true, removeEffect := false
     */
    private Stack<Boolean> stack = new Stack<Boolean>();

    /**
     * Applies the given effect
     *
     * @return true if effect was applied, false otherwise
     */
    public boolean applyEffect() {
        if (effect()) {
            stack.push(true);
            effect();
            return true;
        }
        return false;
    }

    /**
     * Undoes the effect
     *
     * @return true if effect was undone, false otherwise
     */
    public boolean removeEffect() {
        if (stack.peek()) {
            if (uneffect()) {
                return stack.pop(); //we know top of stack is 'true'
            }
        }

        return false;
    }

    /**
     * The actual effect that will be done
     * 
     * @return boolean whether the effect was actually applied
     */
    protected abstract boolean effect();

    /**
     * The way to remove the effect.
     * 
     * @return boolean whether the effect was actually removed
     */
    protected abstract boolean uneffect();
}
