package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.core.GameInstance;
import model.core.Player;
import model.core.Ship;
import model.core.TechLevel;
import model.upgrades.AbstractGadget;
import model.upgrades.CargoGadget;
import model.upgrades.CloakingGadget;
import model.upgrades.FuelGadget;
import model.upgrades.HasPrice;
import model.upgrades.Shield;
import model.upgrades.Weapon;
import org.controlsfx.dialog.Dialogs;
import view.Main;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by Joshua on 10/26/2014.
 */
public class UpgradeShipScreenController implements Initializable {

    /**
     * buy upgrades.
     */
    public Button buyButton;
    /**
     * sell upgrades.
     */
    public Button sellButton;
    /**
     * player's money.
     */
    public Label playerMoney;
    /**
     * back to shipyard.
     */
    public Button leaveButton;
    /**
     * shipyard's upgrades.
     */
    public ListView<HasPrice> shipyardView;
    /**
     * ship's upgrades.
     */
    public ListView<HasPrice> shipView;
    /**
     * the player.
     */
    private Player player;
    /**
     * player's ship.
     */
    private Ship ship;
    /**
     * shipyard upgrades.
     */
    private ObservableList<HasPrice> shipyardUpgrades = FXCollections.observableArrayList();
    /**
     * ship upgrades.
     */
    private ObservableList<HasPrice> shipUpgrades = FXCollections.observableArrayList();
    /**
     * random number generator.
     */
    private Random random = new Random();
    private TechLevel techLevel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
      game instance.
     */
        GameInstance gi = GameInstance.getInstance();
        player = gi.getPlayer();
        /*
      tech level.
     */
        techLevel = gi.getCurrentPlanet().getTechLevel();
        ship = player.getShip();
        playerMoney.setText(Integer.toString(player.getMoney()));

        // Allows us to set our ListCell's text to what we needs and bind them to a good
        shipyardView.setCellFactory(new Callback<ListView<HasPrice>, ListCell<HasPrice>>() {
            @Override
            public ListCell<HasPrice> call(ListView<HasPrice> param) {
                return new GadgetCell();
            }
        });
        shipView.setCellFactory(shipyardView.getCellFactory());
        populateMarket();


        shipUpgrades.addAll(player.getUpgrades());

        // Loads the ListViews and displays the players cash
        shipyardView.setItems(shipyardUpgrades);
        shipView.setItems(shipUpgrades);
        shipyardView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                        if (newValue == null) {
                            return;
                        }
                        if (newValue.getPrice() > player.getMoney()) {
                            buyButton.setDisable(true);
                        } else if (newValue.getPrice() <= player.getMoney()) {
                            if (Shield.class.isInstance(newValue)
                                    && ship.getShields().size() < ship.shieldsSize() ) {
                                buyButton.setDisable(false);
                            } else if (Weapon.class.isInstance(newValue)
                                    && ship.getWeapons().size() < ship.weaponsSize() ) {
                                buyButton.setDisable(false);
                            } else if (AbstractGadget.class.isInstance(newValue)
                                    && ship.getGadgets().size() < ship.gadgetSize() ) {
                                buyButton.setDisable(false);
                            } else {
                                buyButton.setDisable(true);
                            }
                        }
                    }
            );
    }

    /**
     *
     * Populates the market.
     */
    private void populateMarket() {
        if (techLevel == TechLevel.HI_TECH) {
            int chance = random.nextInt(3);
            if (chance == 0) {
                shipyardUpgrades.add(new CargoGadget(player.getShip()));

            } else if (chance == 1) {
                shipyardUpgrades.add(new CloakingGadget(player.getShip()));

            } else if (chance == 2) {
                shipyardUpgrades.add(new FuelGadget(player.getShip()));

            }

        }
        for (int index = 0; index < random.nextInt(5) + 5; index++) {
            if (random.nextBoolean()) {
                if (random.nextInt(3) + techLevel.ordinal() > 7) {
                    shipyardUpgrades.add(Shield.REFLECTIVE_SHIELD);
                } else {
                    shipyardUpgrades.add(Shield.ENERGY_SHIELD);
                }
            }
            if (random.nextBoolean()) {
                int nextInt = random.nextInt(3);
                if (nextInt + techLevel.ordinal() * 2 > 15) {
                    shipyardUpgrades.add(Weapon.MILITARY_LASER);
                } else if (nextInt + techLevel.ordinal() * 2 > 13) {
                    shipyardUpgrades.add(Weapon.BEAM_LASER);
                } else {
                    shipyardUpgrades.add(Weapon.PULSE_LASER);
                }
            }
        }
    }

    /**
     * buys an upgrade.
     *
     * @param actionEvent
     *        the trigger
     */
    public void buy(ActionEvent actionEvent) {
        HasPrice boughtThing = shipyardView.getSelectionModel().getSelectedItem();
        if (boughtThing == null) {
            return;
        }

        if (boughtThing.getPrice() > player.getMoney()) {
            buyButton.setDisable(true);
            return;
        }

        if (Shield.class.isInstance(boughtThing)) {
            ship.addShield((Shield) boughtThing);
        } else if (Weapon.class.isInstance(boughtThing)) {
            ship.addWeapon((Weapon) boughtThing);
        } else if (AbstractGadget.class.isInstance(boughtThing)) {
            ship.addGadget((AbstractGadget) boughtThing);
        }
        shipyardUpgrades.remove(boughtThing);
        player.subtractMoney(boughtThing.getPrice());
        shipUpgrades.add(boughtThing);
        if (shipyardUpgrades.size() == 0) {
            buyButton.setDisable(true);
        }

        if (shipUpgrades.size() > 0) {
            sellButton.setDisable(false);
        }
        playerMoney.setText(String.valueOf(player.getMoney()));
    }

    /**
     * sells an upgrade.
     *
     * @param actionEvent
     *        the trigger
     */
    public void sell(ActionEvent actionEvent) {
        HasPrice soldThing = shipView.getSelectionModel().getSelectedItem();
        if (soldThing == null) {
            return;
        }

        if (Shield.class.isInstance(soldThing)) {
            ship.removeShield((Shield) soldThing);
        } else if (Weapon.class.isInstance(soldThing)) {
            ship.removeWeapon((Weapon) soldThing);
        } else if (AbstractGadget.class.isInstance(soldThing)) {
            if (!ship.removeGadget((AbstractGadget) soldThing)) {
                Dialogs.create().owner(Main.getPrimaryStage())
                        .title("Error!")
                        .message("You still have cargo in the additional bays!!")
                        .showError();
                return;
            }
        }
        shipUpgrades.remove(soldThing);
        player.addMoney(soldThing.getPrice());
        shipyardUpgrades.add(soldThing);

        if (shipUpgrades.size() == 0) {
            sellButton.setDisable(true);
        }
        if (shipyardUpgrades.size() > 0) {
            buyButton.setDisable(false);
        }
        playerMoney.setText(String.valueOf(player.getMoney()));
    }

    /**
     * returns you to the shipyard.
     *
     * @param actionEvent
     *        the trigger
     */
    public void returnToShipyard(ActionEvent actionEvent) {
        Main.setScene("screens/shipyardscreen.fxml");
    }

    /**
     * List Cell subclass that displays items correctly.
     *
     */
    class GadgetCell extends ListCell<HasPrice> {

        @Override
        public void updateItem(HasPrice item, boolean empty) {
            super.updateItem(item, empty);

            //second null check an ugly way to stop marketplace from crashing
            if (item != null) {
                setText(item.getClass().getSimpleName() + " - " + item.toString()
                        + "  " + item.getPrice());
            } else {
                setText("");
            }
        }
    }
}
