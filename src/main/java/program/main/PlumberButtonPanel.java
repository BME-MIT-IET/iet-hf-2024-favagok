package program.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class PlumberButtonPanel extends JPanel implements ActionListener {
    /**
     * Inicializáljuk a gombokat.
     */
    JButton move = new JButton("move");
    JButton pickUpPump = new JButton("pick up pump");
    JButton pickUpPipe = new JButton("pick up pipe");
    JButton repairPump = new JButton("repair pump");
    JButton changePipeIn = new JButton("change pipe in");
    JButton changePipeOut = new JButton("change pipe out");
    JButton placePipe = new JButton("place pipe");
    JButton pickupnewpipe = new JButton("pick up new pipe");

    /**
     * Az osztály konstruktora.
     */
    public PlumberButtonPanel() {
        this.setVisible(true);
        this.setBackground(new Color(240, 204, 125));

        this.add(move);
        move.addActionListener(this);
        move.setVisible(true);
        move.setBackground(new Color(240, 204, 125));
        move.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));

        this.add(pickUpPump);
        pickUpPump.addActionListener(this);
        pickUpPump.setVisible(true);
        pickUpPump.setBackground(new Color(240, 204, 125));
        pickUpPump.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));

        this.add(pickUpPipe);
        pickUpPipe.addActionListener(this);
        pickUpPipe.setVisible(true);
        pickUpPipe.setBackground(new Color(240, 204, 125));
        pickUpPipe.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));

        this.add(repairPump);
        repairPump.addActionListener(this);
        repairPump.setVisible(true);
        repairPump.setBackground(new Color(240, 204, 125));
        repairPump.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));

        this.add(changePipeIn);
        changePipeIn.addActionListener(this);
        changePipeIn.setVisible(true);
        changePipeIn.setBackground(new Color(240, 204, 125));
        changePipeIn.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));

        this.add(changePipeOut);
        changePipeOut.addActionListener(this);
        changePipeOut.setVisible(true);
        changePipeOut.setBackground(new Color(240, 204, 125));
        changePipeOut.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));

        this.add(placePipe);
        placePipe.addActionListener(this);
        placePipe.setVisible(true);
        placePipe.setBackground(new Color(240, 204, 125));
        placePipe.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));

        this.add(pickupnewpipe);
        pickupnewpipe.addActionListener(this);
        pickupnewpipe.setVisible(true);
        pickupnewpipe.setBackground(new Color(240, 204, 125));
        pickupnewpipe.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));
    }

    /**
     * Visszaadja a turn értékét.
     *
     * @return
     */
    public boolean getendturn() {
        boolean end = PlumberPipeInteraction.getendturn();
        return end;
    }

    /**
     * Beállítja a turn értékét.
     *
     * @param temp
     */
    public void resetTurn(boolean temp) {
        PlumberPipeInteraction.setendturn(temp);
    }

    /**
     * Ez a függvény felelős azért, hogy amikor a felhasználó rányom egy gomra akkor az történjen ami a gomra van írva.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Controller.CurrentTurnIsNomad) {
            //this.setVisible(false);
            return;
        } else {
            // this.setVisible(true);
        }

        if (e.getSource() == move) {
            int possiblemoves = Controller.getCurrentPlumber().getPosition().listNeighbours().size();
            PlumberMoveWindow window = new PlumberMoveWindow(possiblemoves);
            //Main.game.Nomads().get(0).move(Main.game.Fields().get(3));
        }
        if (e.getSource() == pickUpPump) {
            Controller.getCurrentPlumber().pickUpPump();
        }
        if (e.getSource() == pickUpPipe) {
            int possiblemoves = Controller.getCurrentPlumber().getPosition().getConnectedPipes().size();
            PlumberPickUpWindow window = new PlumberPickUpWindow(possiblemoves);
        }
        if (e.getSource() == repairPump) {
            Controller.getCurrentPlumber().getPosition().repair();
        }
        if (e.getSource() == changePipeIn) {
            int possiblemoves = Controller.getCurrentPlumber().getPosition().getConnectedPipes().size();
            PlumberPipeInWindow window = new PlumberPipeInWindow(possiblemoves);
        }
        if (e.getSource() == changePipeOut) {
            int possiblemoves = Controller.getCurrentPlumber().getPosition().getConnectedPipes().size();
            PlumberPipeOutWindow window = new PlumberPipeOutWindow(possiblemoves);
        }
        if (e.getSource() == placePipe) {
            Controller.getCurrentPlumber().placePipe();
        }
        if(e.getSource() == pickupnewpipe){
            Controller.getCurrentPlumber().pickUpNewPipe();
        }
        Main.game.gamePanel.repaint();
    }
}
