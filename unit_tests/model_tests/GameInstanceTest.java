package model_tests;

import model.core.GameInstance;
import model.core.Planet;
import model.core.Point;
import model.core.SolarSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class GameInstanceTest {

    private GameInstance gi;
    private Set<SolarSystem> solarSystems;
    private Set<Planet> planets;
    private Set<Point> points;

    @Before
    public void setUp() {
        gi = GameInstance.getInstance();
        solarSystems = gi.getSolarSystems();
        planets = gi.getPlanets();
        points = gi.getPoints();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateUniverse() {
        gi.createUniverse(500);
    }

    @Test
    public void testCreateUniverse1() {
        gi.createUniverse(1);
        assertEquals(1, planets.size());
        assertEquals(1, points.size());
        gi.createUniverse(3);
        assertEquals(3, planets.size());
        assertEquals(3, points.size());
    }


}