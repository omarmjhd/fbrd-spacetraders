package model.upgrades;

import java.util.List;

import model.commerce.Goods;
import model.core.AbstractPresizedList;
import model.core.PresizedList;
import model.core.Ship;

/**
 * This class represents an object that adds extra cargo to a Ship. It uses the
 * command pattern to do/undo changes
 *
 * @author ngraves3
 *
 */
public class CargoGadget extends AbstractGadget {

    /**
     * The extra size added.
     */
    private int additionalSize;

    /**
     * Constructor for the gadget.
     *
     * @param ship
     *        the ship to affect.
     * @param additionalSizeArg
     *        the extra size to add
     */
    public CargoGadget(Ship ship, int additionalSizeArg) {
        super("Extra Cargo Bay", ship);
        this.additionalSize = additionalSizeArg;
    }

    /**
     * Default size constructor.
     *
     * @param ship
     *        the ship to affect
     */
    public CargoGadget(Ship ship) {
        this(ship, 5);
    }

    @Override
    public int getPrice() {
        // arbitrary number
        return 1000;
    }

    @Override
    protected boolean effect() {
        if (!effectApplied) {
            effectApplied = true;
            //TODO add cargo room to ship
            List<Goods> currentCargo = ship.getCargo();
            AbstractPresizedList<Goods> bigger =
                            new PresizedList<>(ship.cargoSize() + additionalSize);
            for (Goods cargo : currentCargo) {
                bigger.add(cargo);
            }
            ship.setCargo(bigger);
            return true;
        }

        return false;

    }

    @Override
    protected boolean uneffect() {
        if (effectApplied) {
            List<Goods> currentCargo = ship.getCargo();
            if ((ship.cargoSize() - additionalSize) >= currentCargo.size()) {
                AbstractPresizedList<Goods> smaller =
                                new PresizedList<>(ship.cargoSize() - additionalSize);
                for (Goods cargo : currentCargo) {
                    smaller.add(cargo);
                }

                /*
                 * Apply effect
                 */

                ship.setCargo(smaller);
                effectApplied = false;
                return true;

            } else {
                //too much cargo to remove additional space
                return false;
            }
        }

        return false;

    }

}
