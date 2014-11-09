package model.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import model.commerce.Goods;

/**
 * Singleton game monitor. The game model will control the passing of turns and
 * random events
 *
 * @author ngraves3
 *
 */
public final class GameInstance implements Serializable {

    /**
     * The player playing.
     */
    private Player player;
    /**
     * The solar systems in the game.
     */
    private Set<SolarSystem> solarSystems = new HashSet<>();
    /**
     * The planets in the game.
     */
    private Set<Planet> planets = new HashSet<>();
    /**
     * The points which are the locations of the solar systems.
     */
    private HashSet<Point> points = new HashSet<>();
    /**
     * Player's current location.
     */
    private Planet currentPlanet;
    /**
     * Players current loation.
     */
    private SolarSystem currentSolarSystem;
    /**
     * Default location for saving games.
     */
    private File file = new File("game_saves");
    /**
     * Flag to check if game was created.
     */
    private boolean saveDirectoryCreated = false;
    /**
     * All the planet names. May or may not use all of them.
     */
    private String[] planetNames = {"Acamar", "Adahn", "Aldea", "Andevian", "Antedi", "Balosnee",
        "Baratas", "Brax", "Bretel", "Calondia", "Campor", "Capelle", "Carzon", "Castor",
        "Cestus", "Cheron", "Courteney", "Daled", "Damast", "Davlos", "Deneb", "Deneva",
        "Devidia", "Draylon", "Drema", "Endor", "Esmee", "Exo", "Ferris", "Festen", "Fourmi",
        "Frolix", "Gemulon", "Guinifer", "Hades", "Hamlet", "Helena", "Hulst", "Iodine",
        "Iralius", "Janus", "Japori", "Jarada", "Jason", "Kaylon", "Khefka", "Kira", "Klaatu",
        "Klaestron", "Korma", "Kravat", "Krios", "Laertes", "Largo", "Lave", "Ligon", "Lowry",
        "Magrat", "Malcoria", "Melina", "Mentar", "Merik", "Mintaka", "Montor", "Mordan",
        "Myrthe"};


    /**
     * Keeps track of how many planets are being made.
     */
    private int planetCount = 0;

    /**
     * Solar system names for the game. May or may not use all.
     */
    private String[] solarSystemNames = {"Nelvana", "Nix", "Nyle",
        "Odet", "Og", "Omega", "Omphalos", "Orias", "Othello",
        "Parade", "Penthara", "Picard", "Pollux",
        "Quator", "Rakhar", "Ran", "Regulas", "Relva", "Rhymus", "Rochani", "Rubicum", "Rutia",
        "Sarpeidon", "Sefalla", "Seltrice", "Sigma", "Sol", "Somari",
        "Stakoron", "Styris", "Talani", "Tamus", "Tantalos", "Tanuga",
        "Tarchannen", "Terosa", "Thera", "Titan", "Torin", "Triacus",
        "Turkana", "Tyrus",
        "Umberlee", "Utopia",
        "Vadera", "Vagra", "Vandor", "Ventax",
        "Xenon", "Xerxes",
        "Yew", "Yojimbo",
        "Zalkon", "Zuul"};
    /**
     * Flag to keep track of solar systems made.
     */
    private int solarSystemCount = 0;

    /**
     * Local instance of all the Goods. Not sure why we need this.
     */
    private Goods[] resources = Goods.values();

    /**
     * Local instance of all the tech levels. Not sure why we need this.
     */
    private TechLevel[] techLevels = TechLevel.values();

    /**
     * Reference to the Singleton GameInstance.
     */
    private static GameInstance instance = new GameInstance();

    /**
     * Private constructor for Singleton. Prevents others from accessing
     */
    private GameInstance() { //private constructor for singleton
    }

    /**
     * Gets the only GameInstance.
     *
     * @return the sole GameInstance
     */
    public static GameInstance getInstance() {
        return instance;
    }

    /**
     * Gets the player's current planet.
     *
     * @return player's current planet
     */
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    /**
     * Sets the player's location to a planet.
     *
     * @param destination
     *        the planet to go to
     */
    public void setCurrentPlanet(Planet destination) {
        this.currentPlanet = destination;
    }

    /**
     * Sets a player for this game instance.
     *
     * @param data
     *        the Player to use for this instance
     */
    public void setPlayer(Player data) {
        player = data;
    }

    /**
     * Returns the player for the instance of this game.
     *
     * @return the Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the solar system the player is in.
     *
     * @return the current solar system
     */
    public SolarSystem getCurrentSolarSystem() {
        return currentSolarSystem;
    }

    /**
     * Sets the current solar system to whatever is passed in.
     *
     * @param desination
     *        the new solar system
     */
    public void setCurrentSolarSystem(SolarSystem desination) {
        currentSolarSystem = desination;
    }

    /**
     * Returns a Set of the solar systems.
     *
     * @return Set of solar systems
     */
    public Set<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    /**
     * Returns a set of the planets.
     *
     * @return set of planets
     */
    public Set<Planet> getPlanets() {
        return planets;
    }

    /**
     * Sets the planet to the Set<Planet> arg.
     *
     * @param planetSet
     *        the planets to use as set of planets
     */
    public void setPlanets(Set<Planet> planetSet) {
        this.planets = planetSet;
    }

    /**
     * Sets the solar systems to the given set.
     *
     * @param solarSystemSet
     *        the set of solarSystems to use
     */
    public void setSolarSystems(Set<SolarSystem> solarSystemSet) {
        this.solarSystems = solarSystemSet;
    }

    /**
     * Creates a universe with number of planets equal to the length of our default list of planet
     * names.
     */
    public void createUniverse() {
        createUniverse(Math.min(planetNames.length, solarSystemNames.length) - 1);
    }

    /**
     * create universe with specified number of planets.
     *
     * @param number
     *        of planets
     */
    public void createUniverse(int number) {
        if (number >= planetNames.length || number >= solarSystemNames.length) {
            throw new IllegalArgumentException(
                            "Number is bigger than planet names");
        }
        Random rand = new Random();
        int startingLocation = rand.nextInt(number) - 1;

        for (int i = 0; i < number; i++) {

            int resourceNum =  rand.nextInt(resources.length);
            int techLevelNum = rand.nextInt(techLevels.length);

            Point point = new Point(rand.nextInt(340) + 5, rand.nextInt(340) + 5);
            while (points.contains(point)) {
                point = new Point(rand.nextInt(340) + 5, rand.nextInt(340) + 5);
            }
            points.add(point);

            Planet planet =
                            new Planet(planetNames[planetCount],
                                            resources[resourceNum],
                                            techLevels[techLevelNum]);
            planets.add(planet);

            planetCount++;
            SolarSystem solarsystem =
                            new SolarSystem(solarSystemNames[solarSystemCount],
                                            point, planet);
            solarSystemCount++;

            solarSystems.add(solarsystem);

            if (startingLocation == i) {

                setCurrentPlanet(planet);
                //System.out.println(getCurrentPlanet());
                setCurrentSolarSystem(solarsystem);
                //System.out.println(getCurrentSolarSystem());

            }

        }

    }

    /**
     * Ends the turn. Probably dont need ths.
     */
    public void endTurn() {
        //Do something to end the turn or whatever
    }


    /**
     * Saves the game.
     *
     * @return boolean to make sure game was saved
     *
     */
    public boolean saveGameInstance() {

        try {
            if (!saveDirectoryCreated) {
                file.mkdir();
                saveDirectoryCreated = true;
            }
            FileOutputStream saveFile = new FileOutputStream(file.getAbsoluteFile() + File.separator + player.getName() + ".sav");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(planets);
            save.writeObject(solarSystems);
            save.writeObject(player);
            save.writeObject(currentPlanet);
            save.writeObject(currentSolarSystem);
            save.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        }
        return false;
    }


    /**
     * Loads a saved game.
     *
     * @param saveFileLocation
     *        Location of the save file
     * @return boolean to make sure game was loaded
     */
    public boolean loadGameInstance(String saveFileLocation) {

        FileInputStream openFile = null;
        ObjectInputStream restore = null;

        try {
            openFile = new FileInputStream(saveFileLocation);
            restore = new ObjectInputStream(openFile);

            HashSet<Planet> planetsRestored = (HashSet<Planet>) restore.readObject();
            HashSet<SolarSystem> solarSystemsRestored = (HashSet<SolarSystem>) restore.readObject();
            Player restoredPlayer = (Player) restore.readObject();
            Planet restoredCurrentPlanet = (Planet) restore.readObject();
            SolarSystem restoredCurrentSolarSystem = (SolarSystem) restore.readObject();

            this.setPlanets(planetsRestored);
            this.setSolarSystems(solarSystemsRestored);
            this.setPlayer(restoredPlayer);
            this.setCurrentSolarSystem(restoredCurrentSolarSystem);
            this.setCurrentPlanet(restoredCurrentPlanet);

            openFile.close();
            restore.close();

            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ClassNotFoundException g) {
            g.printStackTrace();
        } finally {

            try {
                openFile.close();
                restore.close();

            } catch (IOException h) {
                h.printStackTrace();
            }

        }


        return false;

    }

    @Override
    public String toString() {

        StringBuilder gameString = new StringBuilder();
        gameString.append("SOLAR: ");

        for (SolarSystem s: solarSystems) {

            gameString.append(" " + s.toString());

        }
        String term = "\n\n";
        gameString.append(term + " Current Player: " + player.toString() + term);
        gameString.append("Current Planet: " + currentPlanet.toString() + term);
        gameString.append("Current SolarSystem: " + currentSolarSystem.toString() + term);


        return gameString.toString();
    }

}
