package model.events;

import java.util.Random;

import model.core.Player;


/**
 * Private class to handle money events.
 *
 * @author ngraves3
 *
 */
public class MoneyStrategy implements EventStrategy {

    /**
     * Phrases for gaining money.
     */
    private String[] addPhrases = {
            "Your last lotto ticket was worth %d! credits",
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

    @Override
    public String execute(Player player) {
        Random rand = new Random();
        int money = rand.nextInt(player.getMoney() * 2 + 1);
        money -= player.getMoney();

        if (money == 0) {
            money++;
        }

        player.addMoney(money); //adding negative money is taking money

        if (money > 0) {
            return String.format(addPhrases[rand.nextInt(addPhrases.length)], money);
        } else {
            return String.format(minusPhrases[rand.nextInt(minusPhrases.length)], -1 * money);
        }
    }
}

