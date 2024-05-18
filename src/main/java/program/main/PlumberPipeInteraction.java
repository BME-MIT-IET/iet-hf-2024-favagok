package program.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlumberPipeInteraction implements ActionListener {
    /**
     * Inicializáljuk a gombokat.
     */
    Button sticky = new Button("make sticky");
    Button placePump = new Button("place pump");
    Button destroy = new Button("destroy pipe");
    Button repair = new Button("repair pipe");
    Button end = new Button("end turn");
    /**
     * Megadja, hogy a kör tart-e.
     */
    private static boolean endturn = false;
    /**
     * Létrehozunk egy JFrame-t.
     */
    JFrame frame = new JFrame();

    /**
     * Az osztály konstruktora.
     */
    public PlumberPipeInteraction(){
        frame.setVisible(true);
        sticky.addActionListener(this);
        placePump.addActionListener(this);
        repair.addActionListener(this);
        destroy.addActionListener(this);
        end.addActionListener(this);
        //gombok.add(button);
        frame.add(sticky);
        frame.add(placePump);
        frame.add(repair);
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
            Pipe seged = Controller.getCurrentPlumber().getPreviousPosition().getPipeInBetween(Controller.getCurrentPlumber().getPosition());
            Controller.getCurrentPlumber().makeSticky(seged);
            Main.game.gamePanel.repaint();
        }
        if (e.getSource() == placePump) {
            Pipe seged = Controller.getCurrentPlumber().getPreviousPosition().getPipeInBetween(Controller.getCurrentPlumber().getPosition());
            Controller.getCurrentPlumber().placePump(seged);
            Main.game.gamePanel.repaint();
        }
        if (e.getSource() == repair) {
            Pipe seged = Controller.getCurrentPlumber().getPreviousPosition().getPipeInBetween(Controller.getCurrentPlumber().getPosition());
            Controller.getCurrentPlumber().repair(seged);
            Main.game.gamePanel.repaint();
        }
        if (e.getSource() == destroy) {
            Pipe seged = Controller.getCurrentPlumber().getPreviousPosition().getPipeInBetween(Controller.getCurrentPlumber().getPosition());
            Controller.getCurrentPlumber().destroy(seged);
            Main.game.gamePanel.repaint();
        }
        if (e.getSource() == end) {
            endturn = true;
            frame.dispose();
        }
    }

    /**
     * Az endturn gettere.
     * @return
     */
    public static boolean getendturn(){
        return endturn;
    }

    /**
     * Az endturn settere.
     * @param temp
     */
    public static void setendturn(boolean temp){
        endturn = temp;
    }

    public Object getSticky() {
        return sticky;
    }

    public Object getDestroy() {return destroy;}
}
