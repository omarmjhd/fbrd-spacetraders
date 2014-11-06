package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import org.controlsfx.dialog.Dialogs;
import model.core.GameInstance;
import model.core.Player;
import model.core.Point;
import model.core.SolarSystem;
import model.events.RandomEvent;
import view.Main;

/**
 * @version 2.0
 * @author Joshua on 9/25/2014.
 * @author Renee on 10/2/2014
 */
public class MapScreenController implements Initializable {

    /**
     * don't change this.
     */
    public AnchorPane root;
    /**
     * the map.
     */
    public Pane mapPane;
    /**
     * travel button.
     */
    public Button travelButton;
    /**
     * not enough fuel.
     */
    public Text fuelError;
    /**
     * ship's fuel.
     */
    public Label currentFuelLabel;
    /**
     * shows distance = fuel.
     */
    public Label travelDistanceLabel;
    /**
     * game instance.
     */
    private GameInstance gm;
    /**
     * the universe.
     */
    private Set<SolarSystem> universe;
    /**
     * clicked planet.
     */
    private boolean clickedPlanet = false;
    /**
     * current circle of clicked planet.
     */
    private Circle currentCircle;
    /**
     * current line to travel.
     */
    private Line currentLine;
    /**
     * player location.
     */
    private Point playerLocation;
    /**
     * current point chosen by player.
     */
    private Point currentCirclePoint;
    /**
     * travel distance to chosen planet.
     */
    private int travelDistance;
    /**
     * astronaut image.
     */
    private Image astronaut;
    /**
     * view of the astronaut.
     */
    private ImageView astronautView;
    /**
     * the player.
     */
    private Player player;

    /**
     * The list of colors used for planets.
     */
    @FXML
    private String[] colorList = { "blue", "aqua", "aquamarine", "BLUEVIOLET", "cadetblue",
            "CHARTREUSE", "coral", "cornflowerblue",
            "crimson", "cyan", "darkcyan", "goldenrod" };

    /**
     * Location of planet screen.
     */
    private String planetScreen = "screens/planetscreen.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.universe = GameInstance.getInstance().getSolarSystems();
        this.gm = GameInstance.getInstance();
        travelDistanceLabel.setText("");
        playerLocation = gm.getCurrentSolarSystem().getPosition();

        //currentLine initialization
        currentLine = new Line();
        currentLine.setStartX(playerLocation.getX());
        currentLine.setStartY(playerLocation.getY());
        mapPane.getChildren().add(currentLine);

        //make player image
        astronaut = new Image("file:assets/astronaut.png");
        astronautView = new ImageView(astronaut);
        mapPane.getChildren().add(astronautView);
        astronautView.setPreserveRatio(true);
        astronautView.setFitHeight(50);
        astronautView.setX(playerLocation.getX() - 20);
        astronautView.setY(playerLocation.getY() - 25);

        //random events
        player = gm.getPlayer();


        currentFuelLabel.setText("" + gm.getPlayer().getCurrentFuel());

        EventHandler<MouseEvent> handleLabels = event -> {
            if (clickedPlanet) {
                travelDistance = playerLocation.distance(currentCirclePoint);
                travelDistanceLabel.setText("" + travelDistance);
                checkFuel();
            }
        };

        EventHandler<MouseEvent> drawClickedCircle = event -> {
            //if haven't already chosen a planet, just highlight clicked planet
            Circle clickedCircle = (Circle) event.getPickResult().getIntersectedNode();
            Point chosenPlanet = new Point((int) clickedCircle.getCenterX(),
                            (int) clickedCircle.getCenterY());
            if (!clickedPlanet) {
                currentCircle = clickedCircle;
                currentCirclePoint = chosenPlanet;
                currentCircle.setStroke(Color.RED);
                currentCircle.setStrokeWidth(10);
                clickedPlanet = true;
            } else {
//            changes the current chosen planet
                currentCircle.setStroke(null);
                currentCircle = clickedCircle;
                currentCirclePoint = chosenPlanet;
                currentCircle.setStroke(Color.RED);
                currentCircle.setStrokeWidth(10);
            }
        };

        EventHandler<MouseEvent> drawLine = event -> {
            if (clickedPlanet) {
                currentLine.setStroke(null);
                currentLine.setEndX(currentCirclePoint.getX());
                currentLine.setEndY(currentCirclePoint.getY());
                currentLine.setStroke(Color.RED);
                currentLine.setStrokeWidth(3);
            }
        };

        mapPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), null, null)));

        int colorIndex = 0;
        for (SolarSystem s:  universe) {
            Point point = s.getPosition();
            if (colorIndex >= colorList.length) {
                colorIndex = 0;
            }
            Circle circle = new Circle(point.getX(), point.getY(), 5,
                                            Paint.valueOf(colorList[colorIndex]));
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, drawClickedCircle);
            mapPane.addEventHandler(MouseEvent.MOUSE_CLICKED, drawLine);
            mapPane.addEventHandler(MouseEvent.MOUSE_CLICKED, handleLabels);
            circle.setUserData(s);
            mapPane.getChildren().add(circle);

            colorIndex++;
        }

    }

    /**
     * Changes to the marketplace for chosen solar system.
     *
     * @param actionEvent
     *        the trigger
     */
    public void travel(ActionEvent actionEvent) {
        SolarSystem selectedSystem = (SolarSystem) currentCircle.getUserData();
        gm.setCurrentSolarSystem(selectedSystem);
        gm.setCurrentPlanet(selectedSystem.getPlanets().get(0));
        gm.getPlayer().travel(travelDistance);
        RandomEvent randomEvent = new RandomEvent(player);
        String event = randomEvent.event();
        if (!event.equals("")) {
            Dialogs.create().owner(Main.getPrimaryStage())
                            .title("Something has happened...").message(event)
                            .lightweight().showInformation();
        }
        gm.getCurrentPlanet().enterMarket(gm.getPlayer());
        gm.getCurrentPlanet().enterShipyard(gm.getPlayer());
        Main.setScene(planetScreen);
    }

    /**
     * If the distance is too far for the amount of fuel, disables travel button.
     * and adds error Text.
     */
    private void checkFuel() {
        if (clickedPlanet) {
            if (travelDistance > gm.getPlayer().getCurrentFuel()) {
                travelButton.setDisable(true);
                fuelError.setText("Not enough fuel :(");
            } else {
                travelButton.setDisable(false);
                fuelError.setText("");
            }
        } else {
            travelButton.setDisable(false);
            fuelError.setText("");
        }
    }

    /**
     * makes the planet screen.
     *
     * @param actionEvent
     *        the trigger
     *
     */
    public void returnToPlanet(ActionEvent actionEvent) {
        Main.setScene(planetScreen);
    }

}
