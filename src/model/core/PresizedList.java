package model.core;

import java.io.Serializable;
import java.util.AbstractList;
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
public class PresizedList<T> extends AbstractList<T> implements Iterable<T>,
                Serializable {

    private T[] backing;

    private int size;

    @SuppressWarnings("unchecked")
    public PresizedList(int maxSize) {
        backing = (T[]) new Object[maxSize];
        size = 0;
    }

    public int maxSize() {
        return backing.length;
    }

    public boolean hasRoom() {
        return size < backing.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(T item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Items cannot be null");
        }
        if (size >= backing.length) {
            throw new IllegalStateException("List is full");
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
    private T removeHelper(T item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Items cannot be null");
        }
        T retval = null;
        int index = 0;
        while (retval == null && index < size && index < backing.length) {
            if (item.equals(backing[index])) {
                retval = backing[index];
                for (int j = index; j < (backing.length - 1); j++) {
                    backing[j] = backing[j + 1];
                }
                backing[backing.length - 1] = null;
            }
            index++;
        }
        return retval;
    }

    @Override
    public Iterator<T> iterator() {
        return new Innerator();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        T item = (T) obj;

        T retval = removeHelper(item);
        if (retval != null) {
            size--;
        }
        return retval != null;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Index out of range");
        }

        return backing[index];
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
            if (index >= backing.length) {
                return false;
            }
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
