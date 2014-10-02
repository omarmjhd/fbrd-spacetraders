package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.GameInstance;
import model.Point;
import model.SolarSystem;
import view.Main;

import java.net.URL;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author Joshua on 9/25/2014.
 */
public class MapScreenController implements Initializable {

    public AnchorPane root;
    public Pane lolpane;
    private GameInstance gm;
    private HashSet<SolarSystem> universe;
    private boolean clickedPlanet = false;
    private Circle currentChoice;
    private Point playerLocation;
    @FXML

    private String[] colorList = {"blue", "aqua", "aquamarine", "BLUEVIOLET", "blue", "cadetblue", "CHARTREUSE",
                                "coral", "cornflowerblue", "crimson", "cyan", "darkcyan", "goldenrod"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.universe = GameInstance.getInstance().getSolarSystems();
        this.gm = GameInstance.getInstance();

        //playerLocation = gm.getCurrentSolarSystem.getPosition();
        playerLocation = new Point(10, 10);
        Rectangle rectangle = new Rectangle(playerLocation.getX(), playerLocation.getY(), 20, 20);
        rectangle.setFill(Color.WHITE);
        lolpane.getChildren().add(rectangle);



        EventHandler<MouseEvent> handler = event -> {
            System.out.println(event.getX() + ", " + event.getY() + ", " + event.getPickResult().toString());
            Circle clickedCircle = (Circle) event.getPickResult().getIntersectedNode();
            Point point = new Point( (int) clickedCircle.getCenterX(), (int) clickedCircle.getCenterY());
            SolarSystem selectedSystem = (SolarSystem) clickedCircle.getUserData();
            gm.setCurrentPlanet(selectedSystem.getPlanets().get(0));
            Main.setScene("screens/marketplacescreen.fxml");

        };

        EventHandler<MouseEvent> drawClickedCircle = event -> {
            //if haven't already chosen a planet, just highlight clicked planet
            Circle clickedCircle = (Circle) event.getPickResult().getIntersectedNode();
            Point chosenPlanet = new Point((int) clickedCircle.getCenterX(), (int) clickedCircle.getCenterY());
            if (!clickedPlanet) {
                currentChoice = clickedCircle;
                currentChoice.setStroke(Color.RED);
                currentChoice.setStrokeWidth(10);
                clickedPlanet = true;
            } else {
            //changes the current chosen planet
                currentChoice.setStroke(null);
                currentChoice = clickedCircle;
                currentChoice.setStroke(Color.RED);
                currentChoice.setStrokeWidth(10);
            }
        };

        lolpane.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), null, null)));

        Random random = new Random();
        int b = 0;
        for (SolarSystem s:  universe) {
            Point point = s.getPosition();
            System.out.println(point);
            //int b = random.nextInt(colorList.length - 1);
            if (b >= colorList.length) { b =0;}
            System.out.println(b + ": " + colorList[b]);

            Circle circle = new Circle(point.getX(), point.getY(), 5,
                    Paint.valueOf(colorList[b]));
            //circle.addEventHandler(MouseEvent.MOUSE_CLICKED , handler);
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, drawClickedCircle);
            circle.setUserData(s);
            lolpane.getChildren().add(circle);

            b++;
        }

    }
}
