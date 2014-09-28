package model;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is used as backing data for most items in a Ship. It is
 * essentially an array with the nitty-gritty abstracted into add(T) and
 * remove(T). Chose not to implement List<T> because there were way too many
 * unecessary methods.
 *
 * USE THIS: http://docs.oracle.com/javase/7/docs/api/java/util/AbstractList.html
 *
 * @author Nick
 *
 * @param <T>
 */
public class PresizedList<T> implements Iterable<T> {

    private T[] backing;

    int size;

    @SuppressWarnings("unchecked")
    public PresizedList(int maxSize) {
        backing = (T[]) new Object[maxSize];
        size = 0;
    }

    public int maxSize() {
        return backing.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(T item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Items cannot be null");
        }
        if (size >= backing.length) {
            return false;
        }
        backing[size] = item;
        size++;
        return true;
    }


    /**
     * Looks for the item in the list and removes it.
     *
     * @param item
     *        the item to remove from the list
     * @return the item if found, null otherwise
     */
    public T remove(T item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Items cannot be null");
        }
        T retval = null;
        int i = 0;
        while (retval == null && i < size) {
            if (item.equals(backing[i])) {
                retval = backing[i];
                for (; i < (backing.length - 1); i++) {
                    backing[i] = backing[i + 1];
                }
                backing[i + 1] = null;
            }
            i++;
        }
        return retval;
    }

    @Override
    public Iterator<T> iterator() {
        return new Innerator();
    }

    /**
     * Inner iterator
     *
     * @author ngraves3
     *
     */
    private class Innerator implements Iterator<T> {

        int index = 0;

        @Override
        public boolean hasNext() {
            return backing[index] != null;
        }

        @Override
        public T next() {
            if (index >= backing.length || !hasNext()) {
                throw new NoSuchElementException("All out of elements");
            }
            T retval = backing[index];
            index++;
            return retval;

        }
    }
}
