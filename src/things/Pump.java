package things;

import main.LoadSave;
import main.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pump extends Field implements Serializable {
    /**
     * A pumpa grafikus képe
     */
    BufferedImage file_pic;
    /**
     * x koordináta
     */
    protected int x;
    /**
     * y koordináta
     */
    protected int y;
    /**
     * Input cső
     */
    protected Pipe in;
    /**
     * Output cső
     */
    protected Pipe out;
    /**
     * Pumpában lévő vízmennyiség
     */
    protected int water;
    /**
     * Bekötött csövek listája
     */
    protected ArrayList<Pipe> connectedPipes;
    /**
     * Szomszédos pumpák listája
     */
    protected ArrayList<Pump> neighbours = new ArrayList<>();
    /**
     * Friss víz boolean
     */
    protected boolean freshWater = false;

    /**
     * X koordináta lékérése
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Y koordináta lekérése
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * X koordináta beállítása
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Y koordináta beállítása
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * konstruktor
     *
     * @param name     Pump neve
     * @param fileName fajl neve amely a Pump kepe
     */
    public Pump(String name, String fileName) {
        ID = name;
        connectedPipes = new ArrayList<Pipe>();
        file_pic = LoadSave.getFile(fileName);
    }

    /**
     * Pump grafikus megjelenesenek kirajzolasa es nevenek kiirasa
     *
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(file_pic, x, y, 50, 50, null);
        g.drawString(ID, x + 7, y - 3);
    }

    /**
     * Pump kirajzolasa (frissitesre is hasznalatos)
     *
     * @param g
     */
    public void draw(Graphics g) {
        render(g);
    }


    @Override
    public String toString() {
        return (ID + " Pump");
    }

    /**
     * Ez a függvény állítja át a mezők állapotát (state).
     */
    @Override
    public void destroy() {
        Random random = new Random();
        int number = random.nextInt(4);
        if (number == 5) {
            this.setState(State.Broken);
        }

        if (state == State.Broken) {
            if (water == 0) file_pic = LoadSave.getFile(LoadSave.PUMP_BROKEN_EMPTY);
            else {
                file_pic = LoadSave.getFile(LoadSave.PUMP_BROKEN_FULL);
            }
        } else {
            if (water == 0) file_pic = LoadSave.getFile(LoadSave.PUMP_WORKING_EMPTY);
            else {
                file_pic = LoadSave.getFile(LoadSave.PUMP_WORKING_FULL);
            }
        }
        Main.game.gamePanel.repaint();
        //System.out.println("SUCCESS - Pump.destroy()");
        return;
    }

    /**
     * AEz a függvény állítja át a mezők állapotát (state).
     */
    @Override
    public void repair() {
        this.state = State.Working;

        if (water == 0) file_pic = LoadSave.getFile(LoadSave.PUMP_WORKING_EMPTY);
        else {
            file_pic = LoadSave.getFile(LoadSave.PUMP_WORKING_FULL);
        }
        Main.game.gamePanel.repaint();
        //System.out.println("SUCCESS - Pump.repair()");
    }

    /**
     * Kilistázza annak a Pumpnak a szomszédait amin éppen állunk.
     */
    @Override
    public ArrayList<Pump> listNeighbours() {
        //System.out.println("SUCCESS - Pump.listNeighbours()");
        return neighbours;
    }

    /**
     * Szomszédok hozzáadása a pumpához
     *
     * @param p
     */
    public void addNeighour(Pump p) {
        if (!neighbours.contains(p))
            neighbours.add(p);
        //System.out.println("SUCCESS - Pump.addNeighbour()");
        return;
    }

    /**
     * Szomszédok eltávolítása egy pumpától
     *
     * @param p
     */
    public void removeNeighour(Pump p) {
        neighbours.remove(p);
        //System.out.println("SUCCESS - Pump.removeNeighbour()");
        return;
    }

    /**
     * Ez a metódus implementálja a kör végrehajtását.
     */
    @Override
    public void turn() {
        if (state == State.Broken) {
            //System.out.println("SUCCESS - Pump.turn");
            return;
        }

        if (out != null) {
            if (water > 0 && !out.getWater() && !freshWater) {
                out.setWater(true);
                water--;
            }
        }

        destroy();

        freshWater = false;


        //System.out.println("SUCCESS - Pump.turn()");
        return;
    }

    /**
     * Az input cső lekérdezése
     * @return
     */
    public Pipe getPipeIn() {
        //System.out.println("SUCCESS - Pump.getPipeIn()");
        return in;
    }

    /**
     * Az output cső lekérdezése
     * @return
     */
    public Pipe getPipeOut() {
        //System.out.println("SUCCESS - Pump.getPipeOut()");
        return out;
    }

    /**
     * Lekapcsolja a megadott csövet a pumpáról
     *
     * @param p
     */
    @Override
    public void removePipe(Pipe p) {
        if (p == out)
            out = null;
        if (p == in)
            in = null;

        if (connectedPipes.contains(p)) {
            this.neighbours.remove(p.otherEnd(this));
            p.otherEnd(this).neighbours.remove(this);
            this.connectedPipes.remove(p);
            p.detach(this);
        } else {
            //System.out.println("Pipe is not connected");
        }
        //System.out.println("SUCCESS - Pump.removePipe()");
        return;
    }

    /**
     * Csatlakoztat egy új csövet a pumpához.
     *
     * @param p
     */
    @Override
    public void connectPipe(Pipe p) {
        if (p == null) {
            //System.out.println("Parameter Pipe does not exist!");
            return;
        }

        if (!this.connectedPipes.contains(p)) {
            this.connectedPipes.add(p);
            p.atach(this);
            if (p.otherEnd(this) != null) {
                this.addNeighour(p.otherEnd(this));
                p.otherEnd(this).addNeighour(this);
            }
        }


        /*if (connectedPipes.contains(p)) {
            //System.out.println("Parameter Pipe is already connected!");
            if (!(neighbours.contains(p.otherEnd(this))) && p.otherEnd(this) != null) {
                neighbours.add(p.otherEnd(this));
                p.otherEnd(this).neighbours.add(this);
                return;
            }
        }*/

        //connectedPipes.add(p);
        /*if (p.otherEnd(this) != null) {
            neighbours.add(p.otherEnd(this));
        }*/
        //System.out.println("SUCCESS - Pump.connectPipe()");
        return;
    }

    /**
     * Megváltoztatja a pumpa bemenetét egy másik, már a pumpához csatlakoztatott csőre.
     *
     * @param p
     */
    @Override
    public void changePipeIn(Pipe p) {
        if (connectedPipes.contains(p)) {
            in = p;
        } else {
            //System.out.println("Parameter Pipe is not connected!");
        }
        //System.out.println("SUCCESS - Pump.changePipeIn()");
        return;
    }

    /**
     * Megváltoztatja a pumpa kimenetét egy másik, már a pumpához csatlakoztatott csőre.
     *
     * @param p
     */
    @Override
    public void changePipeOut(Pipe p) {
        if (connectedPipes.contains(p)) {
            out = p;
        } else {
            //System.out.println("Parameter Pipe is not connected!");
        }
        //System.out.println("SUCCESS - Pump.changePipeOut()");
        return;
    }

    /**
     * Beállítja, hogy a ciszternában van-e pumpa a newPump adattag értékének változtatásával. Teszt miatt
     *
     * @param p
     */
    @Override
    public void setNewPump(Pump p) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pump.setNewPump()");
    }

    /**
     * A newPump adattagnak ez a gettere. Teszt miatt
     *
     * @return
     */

    public boolean getNewPump() {
        Cistern cist = (Cistern) this;
        return cist.getNewPump();
    }

    /**
     * Beállítja a newPipe adattagot a ciszternában. Teszt miatt.
     *
     * @param p
     */
    @Override
    public void setNewPipe(Pipe p) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pump.setNewPipe()");
    }

    /**
     * A newPipe adattagnak ez a gettere. Teszt miatt.
     *
     * @return
     */
    @Override
    public Pipe getNewPipe() {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pump.getNewPipe()");
    }

    /**
     * Visszaadja a pumpaban tarolt viz mennyiseget
     */
    @Override
    public int SpareWater() {
        //System.out.println("SUCCESS - Pump.SpareWater()");
        return water;
    }

    /**
     * Megadja hogy keresztül tud-e folyni rajta a víz.
     *
     * @return
     */
    @Override
    public boolean flowThru() {
        //System.out.println("SUCCESS - Pump.flowThru()");
        return (in != null && out != null && state == State.Working);
    }

    /**
     * Átadja önmagát paraméterként és megadja, hogy mi van a másik felén.
     *
     * @param p
     * @return
     */
    @Override
    public Pump otherEnd(Pump p) {

        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pump.otherEnd()");
    }

    /**
     * A csövek közepére lehelyez egy pumpát. Teszt miatt.
     */
    @Override
    public void placePump(Pump p) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pump.placePump()");
    }

    /**
     * Teszt miatt.
     */
    @Override
    public void detach(Pump pump) {

        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pump.detach()");
    }

    /**
     * Visszaadja a bekötött csöveket
     *
     * @return ArrayList<Pipe>
     */
    @Override
    public ArrayList<Pipe> getConnectedPipes() {
        //System.out.println("SUCCESS - Pump.getConnectedPipes()");
        return connectedPipes;
    }

    /**
     * Teszt miatt.
     *
     * @return
     */
    public ArrayList<Pump> getConnectedPumps() {

        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pump.getConnectedPumps()");
    }

    /**
     * A pumpaban talalhato vizmennyiseg novelese,  ha 1 egyseget tarol, beallitja hogy ebben-e a korben erkezett-e
     */
    public void addWater() {
        if (water == 0) {
            freshWater = true;
        }
        water++;
        //System.out.println("SUCCESS - Pump.addWater()");
    }

    /**
     * Visszaadja 2 pumpa kozott talalhato csovet
     *
     * @param pump szomszedos pumpa
     * @return Pipe
     */
    public Pipe getPipeInBetween(Pump pump) {
        for (Pipe pipe : this.connectedPipes) {
            if (pipe.otherEnd(this) == pump) {
                return pipe;
            }
        }
        return null;
    }

}
