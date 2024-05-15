package program.main;

import java.awt.*;
import java.io.Serializable;

public class Cistern extends Pump implements Serializable {

    /**
     * Meagdja, hogy a ciszternában van-e pumpa.
     **/
    private boolean newpump;

    /**
     * Ciszterna konstruktora
     * @param name
     * @param fileName
     */
    public Cistern(String name, String fileName) {
        super(name, fileName);
        water = 0;
        newpump = true;
    }

    @Override
    public String toString() {
        return (ID + " Cistern");
    }


    /**
     * newpump setter függvénye
     *
     * @param b
     */
    public void setNewPump(boolean b) {
        newpump = b;
    }

    /**
     * newpump getter függvénye
     *
     * @return
     */
    @Override
    public boolean getNewPump() {
        return newpump;
    }

    /**
     * Ciszternában lévő vízmennyiség növelése
     */
    @Override
    public void addWater() {
        water++;
    }

    @Override
    public boolean flowThru() {
        return true;
    }


    /**
     * A ciszterna körének megvalósítása
     */
    @Override
    public void turn() {
        for (Pipe pipe : connectedPipes) {
            if (pipe.getWater()){
                this.addWater();
                pipe.setWater(false);
            }
        }
        Controller.setPlumberPoints(this.SpareWater());
    }


    public void repair() {
    }


    public void destroy() {
    }

    public void setWater(boolean state) {
    }
}
