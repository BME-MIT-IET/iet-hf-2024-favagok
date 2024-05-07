package entities;

import main.LoadSave;
import main.Main;
import things.*;

import java.awt.*;
import java.io.Serializable;

public class Plumber extends Player implements Serializable {
    /**
     * Azt jelzi, ha a szerelőnél van pumpa, és hogy melyik.
     **/
    private Pump pumpInPocket;
    /**
     * Azt jelzi, ha szerelőnél van cső, és hogy melyik.
     **/
    private Pipe pipeInPocket;

    /**
     * Plumber konstruktora
     *
     * @param name
     * @param cooldown
     * @param currentPosition
     */
    public Plumber(String name, int cooldown, Pump currentPosition, String fileName) {
        super(name, cooldown, currentPosition, fileName);
        this.pumpInPocket = null;
        this.pipeInPocket = null;

    }


    /**
     * Ha el volt romolva az a pumpa vagy cső, amin a szerelő áll, akkor megjavítja.
     *
     * @param f
     */
    public void repair(Field f) {
        if (this.getCooldown() <= 0) {
            if (this.getPosition().getConnectedPipes().contains(f) || this.getPosition().equals(f)) {
                f.repair();
                //System.out.println("SUCCESS - Plumber.repair()");
                // this.done = true;
                this.cooldown = main.Controller.getNomadCoolDown();
            }
        }
    }

    /**
     * A szerelő felvesz egy pumpát a ciszternából.
     **/
    public void pickUpPump() {
        try {
            if (this.getPosition().getNewPump() && this.getPumpInPocket() == null) // Ha ahol vagyunk van pumpa és nincs a zsebben
            {
                // Létrehozunk egy új pumpát olyan "name"-mel még nem rendelkezett egy sem
                for (int counter = 0; counter < Integer.MAX_VALUE; counter++) {
                    boolean egyezik = false;
                    for (Pump p : Main.game.Fields()) {
                        if (p != null && p.GetName().equals("pump" + counter)) {
                            egyezik = true;
                        }
                    }
                    if (!egyezik) {
                        Pump p = new Pump("pump" + counter, LoadSave.PUMP_WORKING_EMPTY);
                        this.setPumpInPocket(p);
                        //this.done = true;
                        //System.out.println("SUCCESS - Plumber.pickUpPump()");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            //System.out.println();
        }
    }

    /**
     * A szerelő felveszi egy csőnek azt a felét, amelyik be van kötve a pumpához, amin éppen áll.
     *
     * @param p
     */
    public void pickUpPipe(Pipe p) {
        if (this.getPosition().getConnectedPipes().contains(p)) {
            this.setPipeInPocket(p);
            this.getPosition().removePipe(p);
            p.pickedUp(this);
            //this.done = true;
            //System.out.println("SUCCESS - Plumber.pickUpPipe()");
        } else {
            //System.out.println("FAILED - The pipe is not connected to the pump!");
        }
    }

    /**
     * új Pipe felvétele, névvel ellátva
     */
    public void pickUpNewPipe() {
        try {
            for (int counter = 0; counter < Integer.MAX_VALUE; counter++) {
                boolean egyezik = false;
                for (Pipe p : Main.game.Pipes()) {
                    if (p != null && p.GetName().equals("pipe" + counter)) {
                        egyezik = true;
                    }
                }
                if (!egyezik) {
                    Pipe pipe = new Pipe(this.getPosition(), this, "pipe" + counter);
                    game.Pipes().add(pipe);
                    this.setPipeInPocket(pipe);
                    //this.done = true;
                    //System.out.println("SUCCESS - Plumber.pickUpNewPump()");
                    break;
                }
            }
        } catch (Exception e) {

        }

    }

    /**
     * A szerelő leteszi a pumpát egy mellette levő (a szerelő egy pumpán áll) cső közepére.
     *
     * @param p
     */
    public void placePump(Pipe p) {
        if (this.getPumpInPocket() != null) {
            if (this.getPosition().getConnectedPipes().contains(p)) {
                p.placePump(this.getPumpInPocket());
                this.setPumpInPocket(null);
                //this.done = true;
                //System.out.println("SUCCESS - Plumber.placePump()");
            } else {
                //System.out.println("FAILED - The pipe is not connected to the pump!");
            }
        } else {
            //System.out.println("FAILED - There is no pump in the pocket!");
        }
    }

    /**
     * A szerelő beköti a nála levő csövet ahhoz a pumpához, ahol áll.
     */
    public void placePipe() {
        if (pipeInPocket != null) {
            this.getPosition().connectPipe(this.getPipeInPocket());
            this.getPipeInPocket().placedDown();
            this.setPipeInPocket(null);
            //this.done = true;
            //System.out.println("SUCCESS - Plumber.placePipe()");
        }
    }

    /**
     * Plumber setPipeInPocket metódusa
     *
     * @param p
     */
    public void setPipeInPocket(Pipe p) {
        pipeInPocket = p;
        //System.out.println("SUCCESS - Plumber.setPipeInPocket()");
    }

    /**
     * plumber setPumpInPocket metódusa
     *
     * @param pump
     */
    public void setPumpInPocket(Pump pump) {
        pumpInPocket = pump;
        //System.out.println("SUCCESS - Plumber.setPumpInPocket()");
    }

    /**
     * Plumber turn metódusa
     **/
    public void turn() {

        this.decreaseCooldown();
        this.decreaseStuckTimeLeft();
        //System.out.println("SUCCESS - Plumber.turn()");


    }

    /**
     * PumpInPocket getter függvénye
     *
     * @return
     */
    public Pump getPumpInPocket() {
        //System.out.println("SUCCESS - Plumber.getPumpInPocket()");
        return pumpInPocket;
    }

    /**
     * PipeInPocket getter függvéne
     *
     * @return
     */
    public Pipe getPipeInPocket() {
        //System.out.println("SUCCESS - Plumber.getPipeInPocket()");
        return pipeInPocket;
    }

    @Override
    public void makeSlippery(Pipe p) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye");
    }

}
