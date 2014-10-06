package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.GameInstance;
import model.Planet;
import model.Player;
import view.Main;

import javax.naming.OperationNotSupportedException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Joshua on 10/6/2014.
 */
public class PlanetScreenController implements Initializable {
    public Text planetName;
    public Button enterMarket;
    public Button buyFuel;
    public Button travelButton;
    public Label planetText;
    private GameInstance gi;
    private Planet curPlanet;
    private Player player;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gi = GameInstance.getInstance();
        curPlanet = gi.getCurrentPlanet();
        player = gi.getPlayer();

        planetName.setText(curPlanet.getName());
        planetText.setText(curPlanet.getName() + "\n Resources:  " + curPlanet.getResource().toString());
    }

    public void goToMarket(ActionEvent actionEvent) {
        Main.setScene("screens/marketplacescreen.fxml");
    }

    public void buyFuel(ActionEvent actionEvent) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public void leaveSystem(ActionEvent actionEvent) {
        Main.setScene("screens/mapscreen.fxml");
    }
}
