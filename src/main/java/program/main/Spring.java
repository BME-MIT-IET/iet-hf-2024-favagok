package program.main;


public class Spring extends Pump {

    /**
     * Forras konstruktora
     *
     * @param name
     * @param fileName
     */
    public Spring(String name, String fileName) {
        super(name, fileName);
    }

    @Override
    public String toString() {
        return (ID + " Spring");
    }


    private int SumOfPumpedWater;

    /**
     * Visszaadja a kinyomott vízmennyiséget
     *
     * @return SumOfPumpedWater
     */
    public int getSumOfPumpedWater() {
        //System.out.println("SUCCESS - Cistern.getOfPumpedWater()");
        return SumOfPumpedWater;
    }

    /**
     * Beállítja a kinyomott vízmennyiséget
     *
     * @param value
     */
    public void setSumOfPumpedWater(int value) {
        //System.out.println("SUCCESS - Cistern.setOfPumpedWater()");
        SumOfPumpedWater = value;
    }


    /**
     * Megvalositja a lepest
     */
    @Override
    public void turn() {
        for (Pipe pipe : connectedPipes) {
            if (pipe != null) {
                if (!pipe.getWater()) {
                    pipe.setWater(true);
                    SumOfPumpedWater++;
                }
            }
        }
    }

    @Override
    public boolean flowThru() {
        return true;
    }


    public void repair() {

    }

    // public Pipe getPipeIn() {
    // throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Spring.getPipeIn()");
    // }


    public int SpareWater() {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Spring.SpareWater()");
    }

    public void destroy() {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Spring.destroy()");
    }

    public void setWater(boolean state) {
        throw new UnsupportedOperationException("Nincs ilyen függvénye: " + "Spring.setWater()");
    }

    public void addWater() {
        throw new UnsupportedOperationException("Nincs ilyen függvénye" + "Spring.addWater()");
    }
}