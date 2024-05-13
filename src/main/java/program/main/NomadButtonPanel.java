package program.main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NomadButtonPanel extends JPanel implements ActionListener {
    /**
     * Inicializáljuk a gombokat és beléjük írjuk a megfelelő szöveget.
     */
    JButton move = new JButton("move");
    JButton changePipeIn = new JButton("change pipe in");
    JButton changePipeOut = new JButton("change pipe out");

    public int lettersize = 12;

    /**
     * Az osztály konstruktora.
     */
    public NomadButtonPanel() {
        this.setVisible(false);
        this.setBackground(new Color(240, 204, 125));
        this.add(move);

        move.setVisible(true);
        move.addActionListener(this);
        move.setVisible(true);
        move.setBackground(new Color(240, 204, 125));
        move.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));

        this.add(changePipeIn);
        changePipeIn.addActionListener(this);
        changePipeIn.setVisible(true);
        changePipeIn.addActionListener(this);
        changePipeIn.setBackground(new Color(240, 204, 125));
        changePipeIn.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));

        this.add(changePipeOut);
        changePipeOut.addActionListener(this);
        changePipeOut.setVisible(true);
        changePipeOut.addActionListener(this);
        changePipeOut.setBackground(new Color(240, 204, 125));
        changePipeOut.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
    }

    /**
     * Visszaadja, hogy véget ért-e az adott kör.
     *
     * @return
     */
    public boolean getendturn() {
        boolean end = NomadPipeInteraction.getendturn();
        return end;
    }

    /**
     * Beállítja az adott kört, hogy például elinduljon.
     *
     * @param temp
     */
    public void resetTurn(boolean temp) {
        NomadPipeInteraction.setendturn(temp);
    }

    /**
     * Ez a függvény felelős azért, hogy amikor a felhasználó rányom egy gomra akkor az történjen ami a gomra van írva.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Controller.CurrentTurnIsNomad) {
            //this.setVisible(false);
            return;
        } else {
            // this.setVisible(true);
        }

        if (e.getSource() == move) {
            int possiblemoves = Controller.getCurrentNomad().getPosition().listNeighbours().size();
            NomadMoveWindow window = new NomadMoveWindow(possiblemoves);
        }
        if (e.getSource() == changePipeIn) {
            int possiblemoves = Controller.getCurrentNomad().getPosition().getConnectedPipes().size();
            NomadPipeInWindow window = new NomadPipeInWindow(possiblemoves);
        }
        if (e.getSource() == changePipeOut) {
            int possiblemoves = Controller.getCurrentNomad().getPosition().getConnectedPipes().size();
            NomadPipeOutWindow window = new NomadPipeOutWindow(possiblemoves);
        }
        Main.game.gamePanel.repaint();
    }
}
