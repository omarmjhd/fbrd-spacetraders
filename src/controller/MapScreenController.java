package controller;

import java.net.URL;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
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
import model.GameInstance;
import model.Player;
import model.Point;
import model.RandomEvent;
import model.SolarSystem;
import view.Main;

/**
 * @version 2.0
 * @author Joshua on 9/25/2014.
 * @author Renee on 10/2/2014
 */
public class MapScreenController implements Initializable {

    public AnchorPane root;
    public Pane lolpane;
    public Button travelButton;
    public Text fuelError;
    public Label currentFuelLabel;
    public Label travelDistanceLabel;
    public Label randomEventLabel;
    private GameInstance gm;
    private HashSet<SolarSystem> universe;
    private boolean clickedPlanet = false;
    private Circle currentCircle;
    private Line currentLine;
    private Point playerLocation;
    private Point currentCirclePoint;
    private int travelDistance;
    private Image astronaut;
    private ImageView astronautView;
    private Player player;

    @FXML

    private String[] colorList = {"blue", "aqua", "aquamarine", "BLUEVIOLET", "blue", "cadetblue", "CHARTREUSE",
                                "coral", "cornflowerblue", "crimson", "cyan", "darkcyan", "goldenrod"};

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
        lolpane.getChildren().add(currentLine);

        //make player image
        astronaut = new Image("file:assets/astronaut.png");
        astronautView = new ImageView(astronaut);
        lolpane.getChildren().add(astronautView);
        astronautView.setPreserveRatio(true);
        astronautView.setFitHeight(50);
        astronautView.setX(playerLocation.getX() - 20);
        astronautView.setY(playerLocation.getY() - 25);

        //random events
        player = gm.getPlayer();
        RandomEvent randomEvent = new RandomEvent(player);
        randomEventLabel.setText(randomEvent.event());

        currentFuelLabel.setText("" + gm.getPlayer().getCurrentFuel());

        //keep in case out of bounds errors happen bc the - 20
        //if (playerLocation.distance(new Point(0,0)) >= 30) {
        //astronautView.setX(playerLocation.getX() - 20);
        //astronautView.setY(playerLocation.getY() - 20);
        //} else {
        //astronautView.setX(playerLocation.getX());
        //astronautView.setY(playerLocation.getY());
        //}

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
            Point chosenPlanet = new Point((int) clickedCircle.getCenterX(), (int) clickedCircle.getCenterY());
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

        lolpane.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), null, null)));

        Random random = new Random();
        int b = 0;
        for (SolarSystem s:  universe) {
            Point point = s.getPosition();
            if (b >= colorList.length) { b =0;}
            Circle circle = new Circle(point.getX(), point.getY(), 5,
                    Paint.valueOf(colorList[b]));
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, drawClickedCircle);
            lolpane.addEventHandler(MouseEvent.MOUSE_CLICKED, drawLine);
            lolpane.addEventHandler(MouseEvent.MOUSE_CLICKED, handleLabels);
            circle.setUserData(s);
            lolpane.getChildren().add(circle);

            b++;
        }

    }

    /**
     * Changes to the marketplace for chosen solar system
     * @param actionEvent
     */
    public void travel(ActionEvent actionEvent) {
        SolarSystem selectedSystem = (SolarSystem) currentCircle.getUserData();
        gm.setCurrentPlanet(selectedSystem.getPlanets().get(0));
        gm.getPlayer().travel(travelDistance);
        gm.getCurrentPlanet().enterMarket(gm.getPlayer());
        Main.setScene("screens/planetscreen.fxml");
    }

    /**
     * If the distance is too far for the amount of fuel, disables travel button
     * and adds error Text
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
     * makes the planet screen
     *
     * @param actionEvent
     */
    public void returnToPlanet(ActionEvent actionEvent) {
        Main.setScene("screens/planetscreen.fxml");
    }

}
