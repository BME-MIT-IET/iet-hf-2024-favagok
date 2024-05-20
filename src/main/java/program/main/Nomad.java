package program.main;

public class Nomad extends Player {

    /**
     * Nomád konstruktora.
     *
     * @param name
     * @param cooldown
     * @param currentPosition
     */
    public Nomad(String name, int cooldown, Pump currentPosition, String fileName) {

        super(name, cooldown, currentPosition, fileName);


    }


    /**
     * A nomád turn metódusa
     */
    public void turn() {

        this.decreaseCooldown();
        this.decreaseStuckTimeLeft();
        //System.out.println("SUCCESS - Nomad.turn()");


    }


    @Override
    public void repair(Field f) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye");
    }


    @Override
    public void pickUpPump() {
        throw new UnsupportedOperationException("Nincs ilyen függvénye");
    }


    @Override
    public void pickUpPipe(Pipe p) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye");
    }


    @Override
    public void placePump(Pipe p) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye");
    }


    @Override
    public void placePipe() {
        throw new UnsupportedOperationException("Nincs ilyen függvénye");
    }


    @Override
    public void setPipeInPocket(Pipe p) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye");
    }


    @Override
    public void setPumpInPocket(Pump pump) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye");
    }

    @Override
    public Pump getPumpInPocket() {
        throw new UnsupportedOperationException("Nincs ilyen függvénye");
    }


    @Override
    public Pipe getPipeInPocket() {
        throw new UnsupportedOperationException("Nincs ilyen függvénye");
    }

    /**
     * A nomád csúszóssá teszi a csóvet amin átment.
     *
     * @param p
     */
    @Override
    public void makeSlippery(Pipe p) {
        if (p != null && this.getPosition().getConnectedPipes().contains(p) && p.getTurnsToNotSlippery() <= 0) {
            if (this.cooldown <= 0) {
                p.SetTurnNotToSlippery(5);
                cooldown = Controller.getNomadCoolDown();
                //System.out.println("SUCCESS - Nomad.makeSlippery()");
            }
        }
    }
}
