package model.events;

import java.util.Random;

import model.commerce.Goods;
import model.core.Player;

/**
 * A class representing random events. These are things such as losing fuel, gaining or losing an
 * item, or gaining or losing money
 *
 * @author ngraves3
 *
 */
public class RandomEvent {

    /**
     * The Player whom the event will affect.
     */
    protected Player player;

    /**
     * Use the same RNG for efficiency.
     */
    protected Random rand;

    /**
     * The array of all possible events.
     */
    private RandomEvent[] events;

    /**
     * Constructor for the random events.
     *
     * @param playerArg
     *        the player to affect
     */
    public RandomEvent(Player playerArg) {
        this.player = playerArg;
        rand = new Random();
    }

    /**
     * Private class to handle money events.
     *
     * @author ngraves3
     *
     */
    private class MoneyEvent extends RandomEvent {

        /**
         * Phrases for gaining money.
         */
        private String[] addPhrases = {"Your last lotto ticket was worth %d! credits",
                                       "%d credits fell into your hand!",
                                       "You found %d credits in your back pocket!",
                                       "An old man gave you %d credits (and a weird wink...)",
                                       "You helped a little old lady carry her groceries "
                                                       + "to the car and she gave you %d credits!"};

        /**
         * Phrases for losing money.
         */
        private String[] minusPhrases = {"You lost %d credits playing a shell game!",
                                         "%d credits fell out of your pocket! Oops!",
                                         "You were mugged and had %d credits stolen!",
                                         "Unexpected taxes in this market cost you %d credits!",
                                         "You gave %d credits to a crying orphan!"};

        /**
         * Constructor for sub-event.
         *
         * @param playerArg
         *        the player to affect
         */
        public MoneyEvent(Player playerArg) {
            super(playerArg);
        }

        @Override
        public String event() {
            int money = rand.nextInt(player.getMoney() * 2 + 1);
            money -= player.getMoney();
            if (money == 0) {
                money++;
            }

            player.addMoney(money); //adding negative money is taking money

            if (money > 0) {
                return String.format(
                                addPhrases[rand.nextInt(addPhrases.length)],
                                money);
            } else {
                return String.format(
                                minusPhrases[rand.nextInt(minusPhrases.length)],
                                -1 * money);
            }
        }
    }

    /**
     * Private class to handle events involving cargo.
     *
     * @author ngraves3
     *
     */
    private class GoodsEvent extends RandomEvent {

        /**
         * Phrases for losing an item.
         */
        private String[] losePhrases = {"Your %s fell out of your ship!",
                                        "A thief stole your %s!",
                                        "Your %s broke!",
                                        "Your %s was in a freak cargo accident"
                                        + " and is no longer sellable!",
                                        "You can't seem to remember where you put that %s..."};

        /**
         * Phrases for gaining an item.
         */
        private String[] getPhrases = {"You found %s floating in space!",
                                       "A retiring trader gave you his last %s!",
                                       "%s fell from the sky into your hands!",
                                       "A shady looking man gave you his %s and ran away!",
                                       "You found free %s under a bush!"};

        /**
         * Constructor for GoodsEvent.
         *
         * @param playerArg
         *        the player to affect
         */
        public GoodsEvent(Player playerArg) {
            super(playerArg);
        }

        @Override
        public String event() {
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
                    return "Your cargo hold broke open,"
                                    + " but there was nothing in it!";
                }
            }
        }

    }

    /**
     * Inner class to handle random events with fuel.
     *
     * @author ngraves3
     *
     */
    private class FuelEvent extends RandomEvent {
        /**
         * Phrases for losing fuel.
         */
        private String[] losePhrases = {"%d units of your fuel leaked out!",
                                        "A thief stole %d units of fuel!"}; //i'm all out of ideas for phrases now

        /**
         * Constructor for a fuel event.
         *
         * @param playerArg
         *        the player to affect
         */
        public FuelEvent(Player playerArg) {
            super(playerArg);
        }

        @Override
        public String event() {
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


    /**
     * this method makes a random event happen in the game universe. It will
     * randomly choose one type of random event and execute it. It has a 10%
     * chance of executing an event
     *
     * @return the message from the event
     */
    public String event() {
        if (events == null) {
            events = new RandomEvent[] {new MoneyEvent(player), new GoodsEvent(player),
                                        new FuelEvent(player)};
        }

        if (rand.nextInt(10) == 0) {

            int eventType = rand.nextInt(events.length);
            return events[eventType].event();
        }


        return "";
    }

}
