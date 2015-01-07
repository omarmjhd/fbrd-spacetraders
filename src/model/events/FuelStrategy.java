package model.events;

import java.util.Random;

import model.core.Player;

/**
 * Strategy for handling fuel events.
 *
 * @author ngraves3
 *
 */
public class FuelStrategy implements EventStrategy {

    /**
     * Phrases for losing fuel.
     */
    private String[] losePhrases = {"%d units of your fuel leaked out!",
            "A thief stole %d units of fuel!"}; //i'm all out of ideas for phrases now

    @Override
    public String execute(Player player) {

        Random rand = new Random();

        int fuelLeakage = rand.nextInt(player.getCurrentFuel() + 1);
        if (fuelLeakage == 0) {
            fuelLeakage++; // 1 to player.getCurrentFuel()
        }
        if (player.getCurrentFuel() > 0) {
            player.travel(fuelLeakage);
            int msg = rand.nextInt(losePhrases.length);
            return String.format(losePhrases[msg], fuelLeakage);
        }

        return "You left your fuel tank open, but you have no fuel to leak!";

    }

}

