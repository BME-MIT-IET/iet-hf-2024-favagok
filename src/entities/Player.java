package entities;

import main.*;
import states.InGame;
import things.Field;
import things.Pump;
import things.Pipe;
import main.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Player implements Round, Serializable {
    BufferedImage file_pic;
    int x;
    int y;
    protected String name;
    protected int cooldown;
    protected Pump currentPosition;
    protected Boolean moved;

    protected Pump previousPosition;
    protected int stuckTimeLeft;

    static protected Game game;

    /**
     * Player konstruktora.
     *
     * @param name
     * @param cooldown
     * @param currentPosition
     */

    public Player(String name, int cooldown, Pump currentPosition, String fileName) {
        stuckTimeLeft = 0;
        previousPosition = currentPosition;
        file_pic = LoadSave.getFile(fileName);
        this.name = name;
        this.cooldown = 0;
        this.currentPosition = currentPosition;
        this.moved = false;
        x = currentPosition.getX();
        y = currentPosition.getY();
    }

    /**
     * Player render metodusa.
     * Ezt használjuk a játékosok kirajzolására.
     *
     * @param g
     */
    public void render(Graphics g) {
        int i = 0;
        loop:
        for (Player p : game.GetPlayers()) {
            if (p.currentPosition.equals(this.currentPosition)) i++;
            if (p.equals(this)) break loop;
        }
        int new_x = x + (i - 1) * 20;
        int new_y = y;
        if (i % 2 == 0) new_y += 5;
        if (i % 2 == 1) new_y -= 5;
        g.drawImage(file_pic, new_x, new_y, 50, 50, null);
        // g.drawString(name, x,y);
    }

    public void draw(Graphics g) {
        render(g);
    }

    /**
     * Player name getter metodusa.
     *
     * @return
     */
    public String GetName() {
        //System.out.println("SUCCESS - Player.GetName()");
        return name;
    }

    /**
     * Player name setter metodusa.
     *
     * @param s
     */
    public void SetName(String s) {
        //System.out.println("SUCCESS - Player.SetName()");
        name = s;
    }

    /**
     * Player turn metodusa : Elvégzi a játékos által kijelölt lépést.
     **/
    public abstract void turn();

    /**
     * A játékos az f Field-re lép (ha az megoldható).
     *
     * @param p
     */
    public void move(Pump p) {
        //System.out.printf("\t%s.%s(%s)%n", name, "move", "p");
        if (this.stuckTimeLeft > 0) {
            //System.out.println("FALIED - You are still stuck for " + this.stuckTimeLeft + " more turns");
            this.moved = true;
            return;
        }
        if (!this.moved) {
            if (p != null && this.getPosition().listNeighbours().contains(p)) {

                for (Pipe pipe : this.getPosition().getConnectedPipes()) {
                    if (pipe.otherEnd(this.getPosition()) != null) {
                        if (pipe.otherEnd(this.getPosition()).equals(p)) {

                            // Ha ragadós volt akkor a kiindulási helyére ragad
                            if (pipe.getTurnToNotSticky() > 0) {
                                //System.out.println("FALIED - You are now stuck for " + pipe.getTurnToNotSticky() + " more turns");
                                this.stuckTimeLeft = pipe.getTurnToNotSticky();
                                this.moved = true;
                                return;
                            }

                            // nem sticky           és  nem csúszós                nem sticky és csúszós és léphet rá
                            if (!pipe.GetSticky() && !pipe.GetSlippery() || (!pipe.GetSticky() && pipe.GetSlippery() && pipe.canPlayerStep())) {
                                x = p.getX();
                                y = p.getY();
                                previousPosition = this.getPosition();
                                this.setPosition(p);
                                Main.game.gamePanel.repaint();
                                if (!Controller.getCurrentTurnIsNomad()) {

                                    if (getPipeInPocket() != null)
                                        getPipeInPocket().setCoords(); //Nomadra nincs ertelmezve!!!
                                    Main.game.gamePanel.repaint();

                                    //System.out.println("SUCCESS - Player.move()");
                                    this.moved = true;
                                }
                            } else {
                                //System.out.println("FALIED - The pipe was sticky or slippery and you slipped");
                            }
                        }
                    } else {
                        //System.out.println("FALIED - The pipe was not connected to the pump (other end is null)");
                    }
                }
            } else {
                //System.out.println("FALIED - The pump was not connected to the pump");
            }
        } else {
            //System.out.println("FALIED - You already moved");
        }
    }

    /** 1-gyel csökkenti a cooldown-t, ha az nem 0. **/
    /**
     * 1-gyel csökkenti a cooldown-t, ha az nem 0.
     **/
    public void decreaseCooldown() {
        if (cooldown > 0)
            cooldown--;
        // System.out.println("SUCCESS - Player.decreaseCooldown()");
    }

    /**
     * A játékos képességeinek állapotát adja vissza. (ha elérhetőek, vagyis cooldown = 0, akkor true)
     **/
    public boolean isAble() {
        //System.out.println("SUCCESS - Player.isAble()");
        return cooldown == 0;
    }

    /**
     * Visszaadja a játékos jelenlegi pozícióját.
     *
     * @return currentPosition
     */
    public Pump getPosition() {
        //System.out.println("SUCCESS - Player.getPosition()");
        return currentPosition;
    }

    /**
     * Beállítja a játékos jelenlegi pozícióját.
     *
     * @param f
     */
    public void setPosition(Pump f) {
        this.currentPosition = f;
        //System.out.println("SUCCESS - Player.setPosition()");
    }

    /**
     * Player changePumpIn Pump bemenetének cseréje.
     *
     * @param p
     */
    public void changePumpIn(Pipe p) {
        if (this.getPosition().getConnectedPipes().contains(p)) {
            this.getPosition().changePipeIn(p);
            //System.out.println("SUCCESS - Player.changePumpIn()");
            //this.done = true;
        } else {
            //System.out.println("FALIED TO CHANGE THE PUMP'S INPUT - The pipe was not connected to the pump");
        }
    }

    /**
     * Player changePumpOut Pump kimenetének cseréje.
     *
     * @param p
     */
    public void changePumpOut(Pipe p) {
        if (this.getPosition().getConnectedPipes().contains(p)) {
            this.getPosition().changePipeOut(p);
            //System.out.println("SUCCESS - Player.changePumpOut()");
            //this.done = true;
        } else {
            //System.out.println("FALIED TO CHANGE THE PUMP'S OUTPUT - The pipe was not connected to the pump");
        }
    }

    /**
     * A player tönkre teszi a csövet. Abstract.
     *
     * @param p
     */
    public void destroy(Pipe p) {
        if (this.getCooldown() <= 0) {
            if (this.getPosition().getConnectedPipes().contains(p)) {
                p.destroy();
                //System.out.println("SUCCESS - Player.destroy()");

                cooldown = main.Controller.getNomadCoolDown();
                // this.done = true;
            } else {
                //System.out.println("FALIED TO DESTROY THE PIPE - The pipe was not connected to the pump");
            }
        } else {
            //System.out.printf("FAILED TO DESTROY PIPE - Your ability is on cooldown!\n");
        }
    }

    /**
     * Ha el volt romolva az a pumpa vagy cső, amin a szerelő áll, akkor megjavítja. Abstract.
     *
     * @param f
     */
    abstract public void repair(Field f);

    /**
     * A szerelő felvesz egy pumpát a ciszternából. Abstract.
     **/
    abstract public void pickUpPump();

    /**
     * A szerelő felveszi egy csőnek azt a felét, amelyik be van kötve a pumpához, amin éppen áll. Abstract.
     *
     * @param p
     */
    abstract public void pickUpPipe(Pipe p);

    /**
     * A szerelő leteszi a pumpát egy mellette levő (a szerelő egy pumpán áll) cső közepére. Abstract.
     *
     * @param p
     */
    abstract public void placePump(Pipe p);

    /**
     * A szerelő beköti a nála levő csövet ahhoz a pumpához, ahol áll. Abstract.
     */
    abstract public void placePipe();

    /**
     * Plumber setPipeInPocket abstract metódusa
     *
     * @param p
     */
    abstract public void setPipeInPocket(Pipe p);

    /**
     * plumber getPipeInPocket abstract metódusa
     */
    abstract public Pipe getPipeInPocket();

    /**
     * plumber setPumpInPocket abstract metódusa
     *
     * @param pump
     */

    abstract public void setPumpInPocket(Pump pump);

    /**
     * plumber getPumpInPocket abstract metódusa
     */
    abstract public Pump getPumpInPocket();

    /**
     * plumber getCooldown metódusa
     *
     * @return cooldown
     */
    public int getCooldown() {
        //System.out.println("SUCCESS - Player.getCooldown()");
        return cooldown;
    }

    /**
     * Csúszóssá teszi a csövet amin a játékos áll
     *
     * @param p
     */
    abstract public void makeSlippery(Pipe p);

    /**
     * Ragadossá teszi a csövet amin áthalad
     *
     * @param p
     */
    public void makeSticky(Pipe p) {
        if (p != null) {
            if (this.getPosition().getConnectedPipes().contains(p) && p.getTurnToNotSticky() <= 0) {

                if (this.getCooldown() <= 0) {
                    p.setTurnToNotSticky(5);
                    this.cooldown = main.Controller.getNomadCoolDown();
                    //System.out.println("SUCCESS - Player.makeSticky()");
                }
            } else {
                //System.out.println("FALIED - The pipe was not connected to the pump or it was already sticky");
            }
        } else {
            //System.out.println("FALIED - The pipe was null");
        }
    }

    public boolean getMoved() {
        //System.out.println("SUCCESS - Player.getMoved()");
        return this.moved;
    }

    public void setMoved(boolean asd) {
        //System.out.println("SUCCESS - Player.setMoved()");
        this.moved = asd;
    }


    public int getStuckTimeLeft() {
        return stuckTimeLeft;
    }

    public void setStuckTimeLeft(int stuckTimeLeft) {
        this.stuckTimeLeft = stuckTimeLeft;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Pump getPreviousPosition() {
        return this.previousPosition;
    }

    public void decreaseStuckTimeLeft() {
        if (stuckTimeLeft > 0)
            stuckTimeLeft--;
    }

    public static void setGame(Game g) {
        game = g;
    }

    public BufferedImage getPicture() {
        return file_pic;
    }
}

