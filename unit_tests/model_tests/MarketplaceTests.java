package model_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import model.commerce.Goods;
import model.commerce.Marketplace;
import model.core.Player;
import model.core.TechLevel;

public class MarketplaceTests {

    private Marketplace market;
    private Player proxy;
    private TechLevel tech;

    @Before
    public void setUp() throws Exception {
        proxy = new Player("Proxy", 5, 5, 5, 5, 5);

        tech = TechLevel.values()[new Random().nextInt(TechLevel.values().length)];
        market = new Marketplace(tech, proxy);

    }

    @Test
    public void testPlayerSellsExistingGoods() {

        /*
         * Set up a proxy player with one item of each type
         */
        for (Goods g : Goods.values()) {
            proxy.addCargo(g);
        }
        /*
         * Preconditions
         */
        assertEquals(Goods.values().length, proxy.getCargo().size());
        /*
         * Set up initial constants
         */
        int initMoney = proxy.getMoney();
        int sellingRevenue = 0;
        int numSales = 0;
        int initialCargoSize = proxy.getCargo().size();
        /*
         * The below for-loop tests selling goods that are actually in the player's cargo
         */
        for (Goods g : proxy.getCargo()) {
            //add checks to make sure the money and supply is updated correctly
            if (g.minTechToUse().ordinal() > tech.ordinal()) {
                //Ensure player can only sell to appropriate TechLevel
                assertFalse(market.playerSells(g));
            } else {
                /*
                 * Constants beforehand
                 */
                int cargoSizeBefore = proxy.getCargo().size();
                sellingRevenue += market.getPrice(g);
                numSales++;
                /*
                 * Checks afterwards
                 */
                assertTrue(market.playerSells(g));
                assertEquals(cargoSizeBefore, proxy.getCargo().size() + 1);
            }
        }

        /*
         * Postconditions
         */
        assertEquals(initialCargoSize, proxy.getCargo().size() + numSales);
        assertEquals(sellingRevenue + initMoney, proxy.getMoney());
    }

    @Test
    public void testPlayerSellsNonExistentGoods() {
        for (Goods g : Goods.values()) {
            assertFalse(market.playerSells(g));
        }
    }

}
