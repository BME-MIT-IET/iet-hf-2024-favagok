package program.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NomadPipeInteraction implements ActionListener {
    /**
     * Inicializáljuk a gombokat.
     */
    Button sticky = new Button("make sticky");
    Button slippery = new Button("make slippery");
    Button destroy = new Button("destroy pipe");
    Button end = new Button("end turn");
    /**
     * Ez a változó mondja meg, hogy a kör véget ért-e.
     */
    private static boolean endturn = false;
    /**
     *Létrehozunk egy JFrame-t.
     */
    JFrame frame = new JFrame();

    /**
     * Az osztály konstruktora.
     */
    public NomadPipeInteraction(){
        frame.setVisible(true);
        sticky.addActionListener(this);
        slippery.addActionListener(this);
        destroy.addActionListener(this);
        end.addActionListener(this);
        //gombok.add(button);
        frame.add(sticky);
        frame.add(slippery);
        frame.add(destroy);
        frame.add(end);
        frame.setSize(500,500);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    }

    /**
     * Ez a függvény felelős azért, hogy amikor a felhasználó rányom egy gomra akkor az történjen ami a gomra van írva.
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == sticky) {
            Pipe seged = Controller.getCurrentNomad().getPreviousPosition().getPipeInBetween(Controller.getCurrentNomad().getPosition());
            Controller.getCurrentNomad().makeSticky(seged);
            Main.game.gamePanel.repaint();
        }
        if (e.getSource() == slippery) {
            Pipe seged = Controller.getCurrentNomad().getPreviousPosition().getPipeInBetween(Controller.getCurrentNomad().getPosition());
            Controller.getCurrentNomad().makeSlippery(seged);
            Main.game.gamePanel.repaint();
        }
        if (e.getSource() == destroy) {
            Pipe seged = Controller.getCurrentNomad().getPreviousPosition().getPipeInBetween(Controller.getCurrentNomad().getPosition());
            Controller.getCurrentNomad().destroy(seged);
            Main.game.gamePanel.repaint();
        }
        if (e.getSource() == end) {
            endturn = true;
            frame.dispose();
        }
    }

    /**
     * Az endturn getter függvénye.
     * @return
     */
    public static boolean getendturn(){
        return endturn;
    }

    /**
     * Az endturn setter függvénye.
     * @param temp
     */
    public static void setendturn(boolean temp){
        endturn = temp;
    }

    public Object getSlippery() {
        return slippery;
    }

    public Object getEnd() {
        return end;
    }
}
