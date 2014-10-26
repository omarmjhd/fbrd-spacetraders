package model;

public class FuelGadget extends Gadget {

    /*
     * Divide current fuel cost by this amount
     */
    private int fuelModifier = 2;

    private int originalFuelCost;

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
            ship.setFuelCost(originalFuelCost / fuelModifier);
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
