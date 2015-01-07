package model.events;

import java.util.Random;

import model.commerce.Goods;
import model.core.Player;

/**
 * Private class to handle events involving cargo.
 *
 * @author ngraves3
 *
 */
public class GoodsStrategy implements EventStrategy {

    /**
     * Phrases for losing an item.
     */
    private String[] losePhrases = {"Your %s fell out of your ship!", "A thief stole your %s!",
            "Your %s broke!",
            "Your %s was in a freak cargo accident" + " and is no longer sellable!",
            "You can't seem to remember where you put that %s..."};

    /**
     * Phrases for gaining an item.
     */
    private String[] getPhrases = {"You found %s floating in space!",
            "A retiring trader gave you his last %s!", "%s fell from the sky into your hands!",
            "A shady looking man gave you his %s and ran away!", "You found free %s under a bush!"};


    @Override
    public String execute(Player player) {

        Random rand = new Random();

        if (rand.nextBoolean()) {

            Goods[] items = Goods.values();
            Goods toAdd = items[rand.nextInt(items.length)];

            int msg = rand.nextInt(getPhrases.length);
            String out = String.format(getPhrases[msg], toAdd.toString());

            if (player.cargoRoomLeft() >= 1) {
                player.addCargo(toAdd);
            } else {
                out = out.substring(0, out.length() - 1);
                out += ", but you don't have room in your cargo for it!";
            }
            return out;
        } else {
            if (player.getCargo().size() > 0) {
                int cargoIndex = rand.nextInt(player.getCargo().size());
                Goods toRemove = player.getCargo().get(cargoIndex);
                player.removeCargo(toRemove);
                int msg = rand.nextInt(losePhrases.length);
                return String.format(losePhrases[msg], toRemove.toString());
            } else {
                return "Your cargo hold broke open," + " but there was nothing in it!";
            }
        }
    }

}

