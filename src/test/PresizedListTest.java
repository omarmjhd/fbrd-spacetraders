package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.PresizedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * This JUnit class tests the add method for the PresizedList class
 *
 */


/**
 * Created by omarmujahid on 11/2/14.
 */
public class PresizedListTest {

    //Make instance variables here
    PresizedList<Integer> pList;
    int maxSize;

    @Before
    public void setup() {

        maxSize = 5;
        pList = new PresizedList<>(maxSize);

    }

    @After
    public void cleanup() {

        pList = new PresizedList<>(maxSize);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddNull(){

        pList.add(null);

    }

    @Test (expected = IllegalStateException.class)
    public void testAddTooMany() {

        for (int i = 0; i < maxSize + 1; i++) {

            pList.add(i + 1);

        }

    }

    @Test
    public void testAddOne(){

        pList.add(1);

        assertFalse(pList.isEmpty());
        assertEquals(1, pList.size());
        assertEquals((Integer) 1, pList.get(0));

    }

    @Test
    public void testAddHalf(){

        for (int i = 0; i < maxSize / 2; i++) {

            pList.add(i + 1);

        }

        assertFalse(pList.isEmpty());
        assertEquals(maxSize / 2, pList.size());
        assertEquals((Integer) 1, pList.get(0));
        assertEquals((Integer) 2, pList.get(1));

    }

    @Test
    public void testAddMax() {

        for (int i = 0; i < maxSize; i++) {

            pList.add(i + 1);

        }

        assertFalse(pList.isEmpty());
        assertEquals(maxSize, pList.size());
        assertEquals((Integer) 1, pList.get(0));
        assertEquals((Integer) 2, pList.get(1));
        assertEquals((Integer) 3, pList.get(2));
        assertEquals((Integer) 4, pList.get(3));
        assertEquals((Integer) 5, pList.get(4));

    }

    @Test
    public void testEmpty() {

        assertTrue(pList.isEmpty());

    }

}
