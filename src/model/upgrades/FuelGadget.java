package model.upgrades;

import model.core.Ship;

/**
 * This class is a gadget that reduces fuel cost for the ship.
 *
 * @author ngraves3
 *
 */
public class FuelGadget extends AbstractGadget {

    /**
     * Divide current fuel cost by this amount.
     */
    private int fuelModifier = 2;

    /**
     * Need to know original cost to avoid truncation errors.
     */
    private int originalFuelCost;

    /**
     * Constructor for a fuel gadget.
     * 
     * @param ship
     *        the ship to affect
     */
    public FuelGadget(Ship ship) {
        super("Efficient Engine", ship);
        originalFuelCost = ship.getFuelCost();
    }

    @Override
    public int getPrice() {
        return 500;
    }

    @Override
    protected boolean effect() {
        if (!effectApplied) {
            ship.setFuelCost(Math.max(1, originalFuelCost / fuelModifier));
            effectApplied = true;
            return true;
        }

        return false;
    }

    @Override
    protected boolean uneffect() {
        if (effectApplied) {
            ship.setFuelCost(originalFuelCost);
            effectApplied = false;
            return true;
        }
        return false;
    }

}
