package program.main;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A pálya ezekből az elemekből áll. Ez egy absztrakt osztály.
 */
public abstract class Field implements Round, Serializable {
    /**
     * A State az egy enum. Megadja, hogy az adott mező működik vagy el van e romolva.
     */

    protected State state = State.Working;

    protected String ID;

    public void SetName(String s) {
        ID = s;
    }

    public String GetName() {
        return ID;
    }

    @Override
    public String toString() {
        return (ID + " Field");
    }

    /**
     * Ez a függvény állítja át a mezők állapotát (state).
     */
    public abstract void destroy();

    /**
     * Kilistázza annak a Fieldnek a szomszédait amin éppen állunk.
     */
    public abstract ArrayList<Pump> listNeighbours();

    /**
     * Átállítja a state-et working-re.
     */
    public abstract void repair();

    /**
     * Lekapcsolja a megadott csövet a pumpáról
     *
     * @param p
     */
    public abstract void removePipe(Pipe p);

    /**
     * Csatlakoztat egy új csövet a pumpához.
     *
     * @param p
     */
    public abstract void connectPipe(Pipe p);

    /**
     * Megváltoztatja a pumpa bemenetét egy másik, már a pumpához csatlakoztatott csőre.
     *
     * @param p
     */
    public abstract void changePipeIn(Pipe p);

    /**
     * Megváltoztatja a pumpa kimenetét egy másik, már a pumpához csatlakoztatott csőre.
     *
     * @param p
     */
    public abstract void changePipeOut(Pipe p);

    /**
     * Beállítja a newPipe adattagot a ciszternában.
     *
     * @param p
     */
    public abstract void setNewPipe(Pipe p);

    /**
     * A newPipe adattagnak ez a gettere.
     *
     * @return
     */
    public abstract Pipe getNewPipe();

    /**
     * Ha van a pumpában víz akkor true.
     */
    public abstract int SpareWater();

    /**
     * A csövek közepére lehelyez egy pumpát.
     *
     * @param p
     */
    public abstract void placePump(Pump p);

    /**
     * Lekapcsolódik a cső a pumpától.
     */
    public abstract void detach(Pump pump);

    /**
     * Megadja hogy keresztül tud-e folyni rajta a víz.
     *
     * @return
     */
    public abstract boolean flowThru();

    /**
     * Átadja önmagát paraméterként és megadja, hogy mi van a másik felén.
     *
     * @param p
     * @return
     */
    public abstract Pump otherEnd(Pump p);

    /**
     * Beállítja, hogy a ciszternában van-e pumpa a newPump adattag értékének változtatásával.
     *
     * @param p
     */
    public abstract void setNewPump(Pump p);

    /**
     * A newPump adattagnak ez a gettere.
     *
     * @return
     */
    public abstract boolean getNewPump();

    public abstract ArrayList<Pipe> getConnectedPipes();

    public abstract ArrayList<Pump> getConnectedPumps();

    /**
     * A field állapotának lekérésére szolgál
     *
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * A field állapotának lekérésére szolgál
     *
     * @return
     */
    public State getState() {
        return state;
    }

    public abstract Pipe getPipeIn();

    public abstract Pipe getPipeOut();

    public abstract void addWater();

    public abstract void addNeighour(Pump p);
    public abstract void removeNeighour(Pump p);
}
