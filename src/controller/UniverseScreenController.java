package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import model.GameModel;
import model.Point;
import model.SolarSystem;

import java.net.URL;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author Joshua on 9/25/2014.
 */
public class UniverseScreenController implements Initializable {

    public AnchorPane root;
    public Pane lolpane;
    private HashSet<SolarSystem> universe;
    @FXML
    private Canvas map;

    private String[] colorList = {"blue", "aqua", "aquamarine", "BLUEVIOLET", "blue", "cadetblue", "CHARTREUSE",
                                "coral", "cornflowerblue", "crimson", "cyan", "darkcyan", "goldenrod"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("INIT");
        this.universe = GameModel.getInstance().getSolarSystems();

        EventHandler<MouseEvent> handler = event -> {
            System.out.println(event.getX() + ", " + event.getY() + ", " + event.getPickResult().toString());


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
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED , handler);
            lolpane.getChildren().add(circle);

            b++;
        }

    }
}
