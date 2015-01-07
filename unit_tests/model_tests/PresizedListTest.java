package model_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.core.PresizedList;
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

    /**
     * Sets up the PresizedList of Integers with a maxSize of 5
     */
    @Before
    public void setup() {

        maxSize = 5;
        pList = new PresizedList<>(maxSize);

    }

    /**
     * Clears the PresizedList of Integers
     */
    @After
    public void cleanup() {

        pList = new PresizedList<>(maxSize);

    }

    /**
     * Tests adding null items to the PresizedList
     * Should throw and IllegalArgumentException
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAddNull(){

        pList.add(null);

    }

    /**
     * Tests adding too many items to the PresizedList
     * Should throw and IllegalStateException
     */
    @Test (expected = IllegalStateException.class)
    public void testAddTooMany() {

        for (int i = 0; i < maxSize + 1; i++) {

            pList.add(i + 1);

        }

    }

    /**
     * Tests adding one item to the PresizedList
     * Should make the list hold the Integer 1, make size 1, and make isEmpty false
     */
    @Test
    public void testAddOne(){

        pList.add(1);

        assertFalse(pList.isEmpty());
        assertEquals(1, pList.size());
        assertEquals((Integer) 1, pList.get(0));

    }

    /**
     * Tests adding half the maxSize items to the PresizedList
     * Should make the list hold the Integers 1-maxSize / 2, make size maxSize / 2, and make isEmpty false
     */
    @Test
    public void testAddHalf(){

        for (int i = 0; i < maxSize / 2; i++) {

            pList.add(i + 1);

        }

        assertFalse(pList.isEmpty());
        assertEquals(maxSize / 2, pList.size());

        for (int i = 0; i < maxSize / 2; i++) {

            int integer = i + 1;

            assertEquals((Integer) integer, pList.get(i));

        }


    }

    /**
     * Tests adding the maxSize items to the PresizedList
     * Should make the list hold the Integers 1-maxSize, make size maxSize, and make isEmpty false
     */
    @Test
    public void testAddMax() {

        for (int i = 0; i < maxSize; i++) {

            pList.add(i + 1);

        }

        assertFalse(pList.isEmpty());
        assertEquals(maxSize, pList.size());

        for (int i = 0; i < maxSize; i++) {

            int integer = i + 1;

            assertEquals((Integer) integer, pList.get(i));

        }

    }

    @Test
    public void testEmpty() {

        assertTrue(pList.isEmpty());

    }

}
