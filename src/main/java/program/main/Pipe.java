package program.main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static program.main.Main.game;

public class Pipe extends Field {

    int x1;
    int y1;
    int x2;
    int y2;

    /**
     * Megadja, hogy a csőben van-e víz.
     */
    private boolean water;

    /**
     * A csőhöz csatlakoztatott pumpák tömbje.
     */
    private Pump connectedPumps[] = new Pump[2];

    /**
     * Megadja, hogy hány körig nem lehet még kilyukasztani a csövet.
     */
    private int turntoDestroy = 0;

    /**
     * Megadja, hogy hány körig csúszos még a cső.
     */
    private int turnsToNotSlippery = 0;

    /**
     * Megadja, hogy hány körig ragadós még a cső.
     */
    private int turnToNotSticky = 0;

    private Plumber inPocketOf = null;


    /**
     * Pipe osztálynak a paraméter nélküli konstruktora.
     */
    public Pipe() {
        ID = "eeee";
        connectedPumps[0] = null;
        connectedPumps[1] = null;
    }

    /**
     * Pipe konstruktora, az új cső felvételéhez szükséges
     */
    public Pipe(Pump pump, Plumber plumber, String name) {
        ID = name;
        connectedPumps[0] = pump;
        connectedPumps[1] = null;
        pump.connectPipe(this);

        inPocketOf = plumber;

        x1 = pump.getX();
        y1 = pump.getY();

        x2 = plumber.getX();
        y2 = plumber.getY();


    }

    /**
     * Pipe osztálynak a konstruktora.
     *
     * @param pump1
     * @param pump2
     * @param name
     */
    public Pipe(Pump pump1, Pump pump2, String name) {
        ID = name;
        connectedPumps[0] = pump1;
        connectedPumps[1] = pump2;
        pump1.connectPipe(this);
        pump2.connectPipe(this);
        pump1.addNeighour(pump2);
        pump2.addNeighour(pump1);

        x1 = pump1.getX();
        y1 = pump1.getY();
        x2 = pump2.getX();
        y2 = pump2.getY();


    }

    /**
     * Beallitja a Pipe koordinatait annak a Plumbernek a kordinataira, amelyik felvette (PickUpPipe)
     */
    public void setCoords() {
        if (inPocketOf != null) {
            if (connectedPumps[0] == null) {
                x1 = inPocketOf.getX();
                y1 = inPocketOf.getY();
            }
            if (connectedPumps[1] == null) {
                x2 = inPocketOf.getX();
                y2 = inPocketOf.getY();
            }
        }
    }

    /**
     * A csövek közepére lehelyez egy pumpát.
     *
     * @param p
     */
    public void placePump(Pump p) {
        // Létrehozunk egy új csövet olyan névvel amilyennel még nincsen mező
        for (int counter = 0; counter < Integer.MAX_VALUE; counter++) {
            boolean egyezik = false;
            for (Field f : Main.game.Pipes()) {
                if (f != null && f.GetName().equals("pipe" + counter)) {
                    egyezik = true;
                }
            }

            if (!egyezik) {
                Pipe cso = new Pipe(connectedPumps[0], p, "pipe" + counter);
                p.connectPipe(this);
                p.connectPipe(cso);
                this.connectedPumps[0] = p;

                cso.connectedPumps[0].addNeighour(cso.connectedPumps[1]);
                cso.connectedPumps[1].addNeighour(cso.connectedPumps[0]);
                this.connectedPumps[1].addNeighour(this.connectedPumps[0]);
                this.connectedPumps[0].addNeighour(this.connectedPumps[1]);
                cso.connectedPumps[0].removeNeighour(this.connectedPumps[1]);
                cso.connectedPumps[1].removeNeighour(this.connectedPumps[0]);

                cso.connectedPumps[0].removePipe(this);
                cso.connectedPumps[1].connectPipe(this);
                cso.connectedPumps[1].connectPipe(cso);

                cso.connectedPumps[0].addNeighour(cso.connectedPumps[1]);
                cso.connectedPumps[1].addNeighour(cso.connectedPumps[0]);
                this.connectedPumps[1].removeNeighour(cso.connectedPumps[0]);

                game.Pipes().add(cso);
                game.Fields().add(p);
                p.setX(this.getX1() + (this.getX2() - this.getX1()) / 2);
                p.setY(this.getY1() + (this.getY2() - this.getY1()) / 2);
                cso.setX1(p.getX());
                cso.setY1(p.getY());
                cso.setX2(cso.otherEnd(p).getX());
                cso.setY2(cso.otherEnd(p).getY());
                this.setX1(p.getX());
                this.setY1(p.getY());
                this.setX2(this.otherEnd(p).getX());
                this.setY2(this.otherEnd(p).getY());

                Main.game.gamePanel.repaint();
                break;
            }
        }
    }

    /**
     * Lekapcsolódik a cső a pumpától.
     */
    public void detach(Pump pump) {
        if (this.connectedPumps[0] == pump) {
            this.connectedPumps[0].removePipe(this);
            this.connectedPumps[0] = null;
        }
        if (this.connectedPumps[1] == pump) {
            this.connectedPumps[1].removePipe(this);
            this.connectedPumps[1] = null;
        }
        //System.out.println("SUCCESS - Pump.Detach()");
    }

    /**
     * Cső pumpához csatlakoztatása
     * @param pump
     */
    public void atach(Pump pump) {
        if (this.connectedPumps[0] == pump || this.connectedPumps[1] == pump)
            return;
        if (this.connectedPumps[0] == null)
            this.connectedPumps[0] = pump;
        else if (this.connectedPumps[1] == null)
            this.connectedPumps[1] = pump;
    }

    public void pickedUp(Plumber plumber) {
        inPocketOf = plumber;
    }

    public void placedDown() {
        inPocketOf = null;
    }

    /**
     * Megadja hogy keresztül tud-e folyni rajta a víz.
     *
     * @return
     */
    public boolean flowThru() {
        if (this.connectedPumps[0].getPipeIn() == this && this.connectedPumps[1].getPipeOut() == this) {
            //System.out.println("SUCCESS - Pump.flowThru()");
            return true;
        }
        if (this.connectedPumps[1].getPipeIn() == this && this.connectedPumps[0].getPipeOut() == this) {
            //System.out.println("SUCCESS - Pump.flowThru()");
            return true;
        }
        //System.out.println("SUCCESS - Pump.flowThru()");
        return false;
    }

    /**
     * Átadja önmagát paraméterként és megadja, hogy mi van a másik felén.
     *
     * @param p
     * @return
     */
    public Pump otherEnd(Pump p) {
        //Pump temp = connectedPumps[0];
        //System.out.println(this.connectedPumps[0]);
        //System.out.println(this.connectedPumps[1]);
        if (connectedPumps[0] != p) {
            return connectedPumps[0];
        }
        //System.out.println("SUCCESS - Pump.otherEnd()");
        return connectedPumps[1];
    }

    /**
     * Beállítja, hogy a ciszternában van-e pumpa a newPump adattag értékének változtatásával.
     *
     * @param p
     */
    @Override
    public void setNewPump(Pump p) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.setNewPump()");
    }

    /**
     * A newPump adattagnak ez a gettere.
     *
     * @return false
     */
    @Override
    public boolean getNewPump() {
        throw new UnsupportedOperationException("Nincs ilyen függvénye" + "Pipe.getNewPump()");
    }

    /**
     * A Pipe osztály turn metódusa
     */
    public void turn() {
        if (turntoDestroy > 0) {
            turntoDestroy--;
        }
        if (turnsToNotSlippery > 0) {
            turnsToNotSlippery--;
        }
        if (turnToNotSticky > 0) {
            turnToNotSticky--;
        }

        if (this.connectedPumps[0] != null) {
            if (this.connectedPumps[0].getPipeIn() == this && this.connectedPumps[0].getPipeIn().getState() == State.Working) {
                if (this.getWater() == true && this.getState() == State.Working) {
                    this.connectedPumps[0].addWater();
                    this.setWater(false);
                }
            }
        }

        if (this.connectedPumps[1] != null) {
            if (this.connectedPumps[1].getPipeIn() == this && this.connectedPumps[1].getPipeIn().getState() == State.Working) {
                if (this.getWater() == true && this.getState() == State.Working) {
                    this.connectedPumps[1].addWater();
                    this.setWater(false);
                }
            }
        }

        if (this.getState() == State.Broken && this.getWater() == true) {
            this.setWater(false);
            Controller.addPointsToNomad();
        }
        //System.out.println("SUCCESS - Pump.turn()");
    }

    /**
     * Kilistázza annak a Fieldnek a szomszédait amin éppen állunk.
     */
    @Override
    public ArrayList<Pump> listNeighbours() {

        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.listNeighbours()");

    }

    /**
     * Lekapcsolja a megadott csövet a pumpáról
     *
     * @param p
     */
    @Override
    public void removePipe(Pipe p) {

        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.removePipe()");
    }

    /**
     * Csatlakoztat egy új csövet a pumpához.
     *
     * @param p
     */
    @Override
    public void connectPipe(Pipe p) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.connectPipe()");

    }

    /**
     * Megváltoztatja a pumpa bemenetét egy másik, már a pumpához csatlakoztatott csőre.
     *
     * @param p
     */
    @Override
    public void changePipeIn(Pipe p) {

        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.changePipeIn()");

    }

    /**
     * Megváltoztatja a pumpa kimenetét egy másik, már a pumpához csatlakoztatott csőre.
     *
     * @param p
     */
    @Override
    public void changePipeOut(Pipe p) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.changePipeOut()");

    }

    /**
     * Beállítja a newPipe adattagot a ciszternában.
     *
     * @param p
     */
    @Override
    public void setNewPipe(Pipe p) {

        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.setNewPipe()");
    }

    /**
     * A newPipe adattagnak ez a gettere.
     *
     * @return
     */
    @Override
    public Pipe getNewPipe() {

        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.getNewPipe()");

    }

    /**
     * Visszaadja a pumpaban tarolt viz mennyiseget
     */
    @Override
    public int SpareWater() {

        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.SpareWater()");

    }


    /**
     * ConnectedPumps getter függvénye
     *
     * @return
     */
    public ArrayList<Pump> getConnectedPumps() {
        ArrayList<Pump> lista = new ArrayList<Pump>();
        lista.add(this.connectedPumps[0]);
        lista.add(this.connectedPumps[1]);
        //System.out.println("SUCCESS - Pump.getConnectedPumps()");
        return lista;
    }

    /**
     * ConnectedPipes getter függvénye
     *
     * @return
     */
    public ArrayList<Pipe> getConnectedPipes() {

        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.getConnectedPipes()");
    }

    /**
     * turnNotToSlippery setter függvénye
     *
     * @param i
     */
    public void SetTurnNotToSlippery(int i) {
        turnsToNotSlippery = i;
        //System.out.println("SUCCESS - Pump.SetTurnNotToSlippery()");
    }

    /**
     * Visszadja, hogy csúszós-e éppen a cső.
     *
     * @return
     */
    public boolean GetSlippery() {
        if (turnsToNotSlippery > 0) {
            //System.out.println("SUCCESS - Pump.GetSlippery()");
            return true;
        }
        //System.out.println("SUCCESS - Pump.GetSlippery()");
        return false;
    }

    /**
     * turnToNotSticky getter függvénye
     *
     * @return
     */
    public int getTurnToNotSticky() {
        //System.out.println("SUCCESS - Pump.getTurnToNotSticky()");
        return turnToNotSticky;
    }

    /**
     * turnToNotSlippery getter függvénye
     *
     * @return
     */
    public int getTurnsToNotSlippery() {
        //System.out.println("SUCCESS - Pump.getTurnsToNotSlippery()");
        return turnsToNotSlippery;
    }

    /**
     * Visszadja, hogy ragadós-e a cső
     *
     * @return
     */
    public boolean GetSticky() {
        if (turnToNotSticky > 0) {
            return true;
        }
        return false;
    }

    /**
     * turnToNotSticky setter függvénye
     *
     * @param i
     */
    public void setTurnToNotSticky(int i) {
        //System.out.println("SUCCESS - Pump.setTurnToNotSticky");
        turnToNotSticky = i;
    }

    /**
     * Megadja, hogy a játékos keresztül tud-e menni a csövön. Igazzal tér vissza ha át tudsz menni a csövön.
     *
     * @return
     */
    public boolean canPlayerStep() {
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        if (randomNumber == 1) {
            //System.out.println("SUCCESS - Pump.canPlayerStep()");
            return true;
        } else {
            //System.out.println("SUCCESS - Pump.canPlayerStep()");
            return false;
        }
    }

    /**
     * Tönkre teszi a csövet.
     */
    public void destroy() {
        if (this.getState() == State.Working) {
            if (turntoDestroy == 0) {
                //System.out.println("SUCCESS - Pump.destroy()");
                this.setState(State.Broken);
            }
        }

    }

    /**
     * Megjavítja a csövet.
     */
    public void repair() {
        if (this.getState() == State.Broken) {
            this.setState(State.Working);
            Random random = new Random();
            int randomNumber = random.nextInt(4);
            randomNumber = randomNumber + 2;
            turntoDestroy = randomNumber;
            //System.out.println("SUCCESS - Pump.repair()");
        }
    }

    /**
     * Visszaadja, hogy van-e a csőben víz
     *
     * @return
     */
    public boolean getWater() {
        //System.out.println("SUCCESS - Pump.getWater()");
        return water;
    }

    /**
     * Beállítjq q water adattag értékét.
     *
     * @param state
     */
    public void setWater(boolean state) {
        //System.out.println("SUCCESS - Pump.setWater()");
        water = state;
    }


    public void removeNeighour(Pump p) {

        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.removeNeighour()");
    }

    public void addNeighour(Pump p) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.addNeighour()");
    }

    public void addWater() {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.addWater()");
    }

    public Pipe getPipeIn() {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.getPipeIn()");
    }

    public Pipe getPipeOut() {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Pipe.getPipeOut()");
    }

    /**
     * A Pipe grafikus megjeleniteset vegzi az aktualis allapotoknak megfeleloen
     *
     * @param g
     */
    public void render(Graphics g) {
        Graphics2D pen = (Graphics2D) g;

        if (state == State.Working) {
            if (water == false) {
                if (turnToNotSticky > 0 && turnsToNotSlippery > 0) {
                    empty_Working_SlipperyAndSticky(pen);
                    return;
                }
                if (turnToNotSticky > 0) {
                    empty_Working_Sticky(pen);
                    return;
                }
                if (turnsToNotSlippery > 0) {
                    empty_Working_Slippery(pen);
                    return;
                } else {
                    empty_Working(pen);
                    return;
                }
            } else {
                if (turnToNotSticky > 0 && turnsToNotSlippery > 0) {
                    full_Working_SlipperyAndSticky(pen);
                    return;
                }
                if (turnsToNotSlippery > 0) {
                    full_Working_Slippery(pen);
                    return;
                }
                if (turnToNotSticky > 0) {
                    full_Working_Sticky(pen);
                    return;
                } else {
                    full_Working(pen);
                    return;
                }
            }
        } else {
            if (water == false) {
                if (turnToNotSticky > 0 && turnsToNotSlippery > 0) {
                    empty_Broken_SlipperyAndSticky(pen);
                    return;
                }
                if (turnToNotSticky > 0) {
                    empty_Broken_Sticky(pen);
                    return;
                }
                if (turnsToNotSlippery > 0) {
                    empty_Broken_Slippery(pen);
                    return;
                } else {
                    empty_Broken(pen);
                    return;
                }
            } else {
                if (turnToNotSticky > 0 && turnsToNotSlippery > 0) {
                    full_Broken_SlipperyAndSticky(pen);
                    return;
                }
                if (turnsToNotSlippery > 0) {
                    full_Broken_Slippery(pen);
                    return;
                }
                if (turnToNotSticky > 0) {
                    full_Broken_Sticky(pen);
                    return;
                } else {
                    full_Broken(pen);
                    return;
                }
            }
        }
    }

    /**
     * Ures, nem lyukas cso kirajzolasa
     *
     * @param line
     */
    private void empty_Working(Graphics2D line) {
        line.setColor(Color.GRAY);
        line.setStroke(new BasicStroke(10));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Vizzel tele, nem lyukas cso kirajzolasa
     *
     * @param line
     */
    private void full_Working(Graphics2D line) {
        line.setColor(Color.BLUE);
        line.setStroke(new BasicStroke(10));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Ures, nem lyukas, ragados cso kirajzolasa
     *
     * @param line
     */
    private void empty_Working_Sticky(Graphics2D line) {
        line.setColor(Color.GRAY);
        line.setStroke(new BasicStroke(12));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.YELLOW);
        line.setStroke(new BasicStroke(4));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Ures, nem lyukas, csuszos cso kirajzolasa
     *
     * @param line
     */
    private void empty_Working_Slippery(Graphics2D line) {
        line.setColor(Color.GRAY);
        line.setStroke(new BasicStroke(12));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.RED);
        line.setStroke(new BasicStroke(4));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Vizzel tele, nem lyukas, ragados cso kirajzolasa
     *
     * @param line
     */
    private void full_Working_Sticky(Graphics2D line) {
        line.setColor(Color.BLUE);
        line.setStroke(new BasicStroke(12));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.YELLOW);
        line.setStroke(new BasicStroke(4));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Vizzel tele, nem lyukas, csuszos cso kirajzolasa
     *
     * @param line
     */
    private void full_Working_Slippery(Graphics2D line) {
        line.setColor(Color.BLUE);
        line.setStroke(new BasicStroke(12));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.RED);
        line.setStroke(new BasicStroke(4));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Ures, nem lyukas, ragados es csuszos cso kirajzolasa
     *
     * @param line
     */
    private void empty_Working_SlipperyAndSticky(Graphics2D line) {
        line.setColor(Color.GRAY);
        line.setStroke(new BasicStroke(12));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.RED);
        line.setStroke(new BasicStroke(4));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.YELLOW);
        line.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4, 4}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Vizzel tele, nem lyukas, ragados es csuszos cso kirajzolasa
     *
     * @param line
     */
    private void full_Working_SlipperyAndSticky(Graphics2D line) {
        line.setColor(Color.BLUE);
        line.setStroke(new BasicStroke(12));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.RED);
        line.setStroke(new BasicStroke(4));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.YELLOW);
        line.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4, 4}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Ures, lyukas cso kirajzolasa
     *
     * @param line
     */
    private void empty_Broken(Graphics2D line) {
        line.setColor(Color.GRAY);
        line.setStroke(new BasicStroke(10, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Vizzel tele, lyukas cso kirajzolasa
     *
     * @param line
     */
    private void full_Broken(Graphics2D line) {
        line.setColor(Color.BLUE);
        line.setStroke(new BasicStroke(10, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Ures, lyukas, ragados cso kirajzolasa
     *
     * @param line
     */
    private void empty_Broken_Sticky(Graphics2D line) {
        line.setColor(Color.GRAY);
        line.setStroke(new BasicStroke(12, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.YELLOW);
        line.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Ures, lyukas, csuszos cso kirajzolasa
     *
     * @param line
     */
    private void empty_Broken_Slippery(Graphics2D line) {
        line.setColor(Color.GRAY);
        line.setStroke(new BasicStroke(12, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.RED);
        line.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Vizzel tele, lyukas, ragados cso kirajzolasa
     *
     * @param line
     */
    private void full_Broken_Sticky(Graphics2D line) {
        line.setColor(Color.BLUE);
        line.setStroke(new BasicStroke(12, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.YELLOW);
        line.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Vizzel tele, lyukas, csuszos cso kirajzolasa
     *
     * @param line
     */
    private void full_Broken_Slippery(Graphics2D line) {
        line.setColor(Color.BLUE);
        line.setStroke(new BasicStroke(12, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.RED);
        line.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Ures, lyukas, csuszos es ragados cso kirajzolasa
     *
     * @param line
     */
    private void empty_Broken_SlipperyAndSticky(Graphics2D line) {
        line.setColor(Color.GRAY);
        line.setStroke(new BasicStroke(12, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.RED);
        line.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.YELLOW);
        line.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{7, 13}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }

    /**
     * Vizzel tele, lyukas, csuszos es ragados cso kirajzolasa
     *
     * @param line
     */
    private void full_Broken_SlipperyAndSticky(Graphics2D line) {
        line.setColor(Color.BLUE);
        line.setStroke(new BasicStroke(12, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.RED);
        line.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{14, 6}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.YELLOW);
        line.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{7, 13}, 0));
        line.drawLine(x1 + 25, y1 + 25, x2 + 25, y2 + 25);

        line.setColor(Color.BLACK);
        line.drawString(ID, (x1 + 25) + ((x2 + 25) - (x1)) / 2, (y1 + 25) + ((y2 + 25) - (y1)) / 2);
    }


    public void draw(Graphics g) {
        render(g);
    }

    public void setX1(int x) {
        this.x1 = x;
    }

    public void setY1(int y) {
        this.y1 = y;
    }

    public void setX2(int x) {
        this.x2 = x;
    }

    public void setY2(int y) {
        this.y2 = y;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }


}
