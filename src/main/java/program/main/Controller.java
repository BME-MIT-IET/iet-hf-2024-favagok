package program.main;

import java.util.Random;
import static program.main.Main.game;
import static program.main.State.Working;
import static program.main.State.Broken;

public class Controller {
    /**
     * attributumok
     */

    private static int coolDown;

    private static int roundsLeft = 10;
    private static int plumberCoolDown;
    private static int nomadCoolDown;

    private static int plumberPoints;
    private static int nomadPoints;

    private static Random rand;
    private static int index;
    static Nomad currentNomad;
    static Plumber currentPlumber;

    static boolean  currentTurnIsNomad = false;

    static boolean gameOver = false;


    /**functions
     */

    /**
     * setRounds
     * Változtatja a roundLeft adattag értékét -1 el
     */
    public static void decrementRounds() {
        roundsLeft--;
    }

    /**
     * Beállítja a hátralévő körök számát.
     *
     * @param i Az új hátralévő körök száma.
     */
    public static void setRounds(int i){
        if (i < 0){
            throw new IllegalArgumentException("Round count cannot be negative");
        }
        roundsLeft = i;
    }

    /**
     * Visszaadja a jelenlegi vízszerelő játékost.
     *
     * @return A jelenlegi vízszerelő játékos.
     */
    public static Plumber getCurrentPlumber() {
        return currentPlumber;
    }

    /**
     * Visszaadja a jelenlegi nomád játékost.
     *
     * @return A jelenlegi nomád játékos.
     */
    public static Nomad getCurrentNomad() {
        return currentNomad;
    }

    /**
     * getRounds
     * roundLeft adattag értékét adja vissza
     *
     * @return roundLeft
     */
    public static int getRounds() {
        // System.out.println("SUCCESS - Controller.getRounds()");
        return roundsLeft;
    }


    /**
     * getPlumberCooldown
     * A plumberCoolDown attribútumot adja vissza
     *
     * @return plumberCoolDown
     */
    public static int getCoolDown() {
        // System.out.println("SUCCESS - Controller.getPlumberCoolDown()");
        return coolDown;
    }

    /**
     * setNomadCoolDown
     * Beállítja a nomadCooldown adattag értékét.
     *
     * @param value
     */
    public static void setCoolDown(int value) {
        //System.out.println("SUCCESS - Controller.setNomadCoolDown()");
        coolDown = value;
    }

    /**
     * getNomadCoolDown
     * A plumberCoolDown attribútumot adja vissza
     *
     * @return nomadCooldown
     */
    public static int getNomadCoolDown() {
        // System.out.println("SUCCESS - Controller.getNomadCoolDown()");
        return coolDown;
    }

    /**
     * Visszaadja a jelenlegi nomád játékost.
     *
     * @return A jelenlegi nomád játékos.
     */
    public Nomad currentNomadTurn() {
        return currentNomad;
    }

    /**
     * Visszaadja a jelenlegi vízszerelő játékost.
     *
     * @return A jelenlegi vízszerelő játékos.
     */
    public Plumber currentPlumberTurn() {
        return currentPlumber;
    }

    /**
     * turn
     * A megvalósított Round interface turn fuggvenye
     */
    public void turn() {
        if (getRounds() == 0){
            gameOver = true;
            return;
        }
        Main.game.getGameWindow().getPlumberButtonPanel().setVisible(true);
        for (int i = 0; i < game.Plumbers().size(); i++) {
            game.Nomads().get(i).setMoved(false);
            currentPlumber = game.Plumbers().get(i);
            while (!game.plumberButtonPanel.getendturn()) {
                currentPlumber = game.Plumbers().get(i);
                currentPlumber.turn();
            }
            Main.game.gamePanel.repaint();
            game.Plumbers().get(i).setMoved(false);

            Main.game.gamePanel.repaint();
            for (int j = 0; j < game.Pipes().size(); j++) {
                game.Pipes().get(j).turn();
            }
            for (int k = 0; k < game.Fields().size(); k++) {
                game.Fields().get(k).turn();
            }

            game.plumberButtonPanel.resetTurn(false);

        }
         currentTurnIsNomad = true;
        Main.game.getGameWindow().getPlumberButtonPanel().setVisible(false);
        Main.game.getGameWindow().getNomadButtonPanel().setVisible(true);


        for (int i = 0; i < game.Nomads().size(); i++) {
            game.Nomads().get(i).setMoved(false);
            currentNomad = game.Nomads().get(i);
            while (!game.nomadButtonPanel.getendturn()) {
                currentNomad = game.Nomads().get(i);
                currentPlumber.turn();
            }
            Main.game.gamePanel.repaint();
            game.Nomads().get(i).setMoved(false);

            Main.game.gamePanel.repaint();
            for (int k = 0; k < game.Pipes().size(); k++) {
                game.Pipes().get(k).turn();
            }
            for (int j = 0; j < game.Fields().size(); j++) {
                game.Fields().get(j).turn();
            }
            game.nomadButtonPanel.resetTurn(false);
        }
         currentTurnIsNomad = false;
        Main.game.getGameWindow().getNomadButtonPanel().setVisible(false);
        Main.game.getGameWindow().getPlumberButtonPanel().setVisible(true);
        decrementRounds();
    }

    /**
     * addPointsToPlumber
     * A plumberPoints adattag értékét növeli meg akkor amikor befolyik víz a ciszternába.
     */
    public static void addPointsToPlumber() {
        plumberPoints++;
        //System.out.println("SUCCESS - Controller.addPointsToPlumber()");
    }

    /**
     * getPlumberPoints
     * A plumberPoints adattagot adja vissza
     *
     * @return plumberPoints
     */
    public static int getPlumberPoints() {
        //System.out.println("SUCCESS - Controller.getPlumberPoints()");
        return plumberPoints;
    }

    /**
     * addPointsToNomad
     * A nomadPoints adattag értékét növeli meg akkor amikor elfolyik a víz a sivatagba.
     * A nomádok úgy tudnak pontot szerezni,
     * ha a víz egy lyukas csövön keresztül kifolyik a sivatagba
     * vagy egy olyan csövön keresztül folyik a víz
     * ami nincsen bekötve egyetlen punpába sem.
     */
    public static void addPointsToNomad() {
        nomadPoints++;
        //System.out.println("SUCCESS - Controller.addPointsToNomad()");
    }

    /**
     * getNomadPoints
     * A nomadPoints adattagot adja vissza
     *
     * @return nomadPoints
     */
    public static int getNomadPoints() {
        //System.out.println("SUCCESS - Controller.getNomadPoints()");
        return nomadPoints;
    }

    /**
     * destroyPump
     * random pump defectet ezzel a függvénnyel okoz a Controller
     */
    public static void destroyPump() {
        int upperbound = game.Fieldsize();
        rand = new Random();
        index = rand.nextInt(upperbound);
        Pump pump = game.Fields().get(index);
        //System.out.println("SUCCESS - " + pump.GetName() + " Has been destroyed");
        if (pump.getState() == Working) {
            pump.setState(Broken);
            //System.out.println("SUCCESS - Controller.destroyPump()");
        } else {
            //System.out.println("FAILED, " + pump.GetName() + " ALREADY DESTROYED");
        }
    }

    public static boolean getCurrentTurnIsNomad() {
        return  currentTurnIsNomad;
    }

    public static void setPlumberPoints(int plumberPoints) {
        Controller.plumberPoints = plumberPoints;
    }
}
