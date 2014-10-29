package model.upgrades;

import model.core.Ship;

/**
 * The gadget the makes the player invisible to encounters
 *
 * @author ngraves3
 *
 */
public class CloakingGadget extends Gadget {

    public CloakingGadget(Ship ship) {
        super("Stealth Generator", ship);
    }

    @Override
    public int getPrice() {
        //arbitrary number
        return 2000;
    }

    @Override
    protected boolean effect() {
        if (!effectApplied) {
            ship.setVisible(false);
            effectApplied = true;
            return true;
        }
        return false;
    }

    @Override
    protected boolean uneffect() {
        if (effectApplied) {
            ship.setVisible(true);
            effectApplied = false;
            return true;
        }

        return false;
    }

}
