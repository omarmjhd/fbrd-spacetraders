package model_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.core.Ship;
import model.upgrades.Shield;

import org.junit.Before;
import org.junit.Test;

import java.util.AbstractList;

/**
 * JUnit test for the addShield method in Ship.
 */
public class ShipTest {

    private AbstractList<Shield> shields;
    private int shieldSize;

    /**
     * Creates a new Firefly Ship.
     */
    @Before
    public void setUp() {

        Ship ship = Ship.firefly();
        shields = ship.getShields();

    }

    /**
     * Tests adding a shield when there's no room left on the Player's Ship.
     */
    @Test (expected = IllegalStateException.class)
    public void testAddTooManyShields() {

        shields.add(Shield.ENERGY_SHIELD);
        assertFalse(shields.add(Shield.REFLECTIVE_SHIELD));
        assertEquals(shieldSize, shields.size());

    }

    /**
     * Tests adding a shield when there's room on the Player's Ship.
     */
    @Test
    public void testAddShield() {

        assertTrue(shields.add(Shield.REFLECTIVE_SHIELD));
        assertEquals(1, shields.size());

    }

    /**
     * Tests adding null argument.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAddNull() {

        shields.add(null);

    }
}
