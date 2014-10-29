package model.upgrades;

import java.util.List;
import model.commerce.Goods;
import model.core.PresizedList;
import model.core.Ship;

/**
 * This class represents an object that adds extra cargo to a Ship. It uses the
 * command pattern to do/undo changes
 *
 * @author ngraves3
 *
 */
public class CargoGadget extends Gadget {

    private int additionalSize;

    public CargoGadget(Ship ship, int additionalSize) {
        super("Extra Cargo Bay", ship);
        this.additionalSize = additionalSize;
    }

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
            PresizedList<Goods> bigger =
                            new PresizedList<>(currentCargo.size()
                                            + additionalSize);
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
                PresizedList<Goods> smaller =
                                new PresizedList<>(currentCargo.size()
                                                - additionalSize);
                for (Goods cargo : currentCargo) {
                    smaller.add(cargo);
                }

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
