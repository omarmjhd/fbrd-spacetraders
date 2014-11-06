package model_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.core.Player;
import model.core.Ship;
import model.upgrades.Crew;

public class PlayerTests {

    private Player player;
    private Crew crewmembers[];
    private Ship ship;

    @Before
    public void setUp() throws Exception {
        player = new Player("Player", 5, 5, 5, 5, 5);
        ship = player.getShip();
    }

    @Test
    public void hasCrew() {

        /*
         * Gives one crew member to the ship
         */
        crewmembers[0] = new Crew(5, 5, 5, 5, 5);

        for (Crew c : crewmembers) {
            ship.addCrew(c);
        }
        /*
         * Preconditions
         */
        assertEquals(ship.getCrew().size(), crewmembers.length);

        /*
         * Calculates the player's total skill, and what it should be
         */
        int playerskill = player.getTradeSkill();

        /*
         * Postconditions
         */
        assertEquals(playerskill, 10);
    }

    @Test
    public void noCrew() {
        /*
         * Preconditions (no crew)
         */
        assertEquals(ship.getCrew().size(), 0);

        /*
         * check the method
         */
        int playerskill = player.getTradeSkill();

        /*
         * Postconditions
         */
        assertEquals(playerskill, 5);
    }

}
