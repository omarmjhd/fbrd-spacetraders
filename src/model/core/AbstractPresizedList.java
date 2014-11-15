package model.core;

import java.util.AbstractList;

/**
 * The abstract class describing the operations of a PresizedList.
 *
 * @author ngraves3
 *
 * @param <T>
 */
public abstract class AbstractPresizedList<T> extends AbstractList<T> {

    /**
     * Returns whether another item can be added to the list.
     *
     * @return whether another item can be added to the list.
     */
    public abstract boolean hasRoom();

    /**
     * Returns the max size of the list.
     *
     * @return the max size of the list.
     */
    public abstract int maxSize();

}
