package program.main;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    /**
     * attributumok
     */
    private JFrame jframe;
    final private NomadButtonPanel nomadButtonPanel;
    final private PlumberButtonPanel plumberButtonPanel;

    public NomadButtonPanel getNomadButtonPanel() {
        return nomadButtonPanel;
    }

    public PlumberButtonPanel getPlumberButtonPanel() {
        return plumberButtonPanel;
    }

    /**
     * Játék ablak beállítása
     */
    GameWindow(GamePanel gamePanel, PlumberButtonPanel plumberButtonPanel, NomadButtonPanel nomadButtonPanel) {
        jframe = new JFrame();
        this.nomadButtonPanel = nomadButtonPanel;
        this.plumberButtonPanel = plumberButtonPanel;
        jframe.setTitle("Sivatagi Vizhalozat");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLayout(new BoxLayout(jframe.getContentPane(), BoxLayout.Y_AXIS));
        jframe.add(gamePanel);
        jframe.add(nomadButtonPanel);
        jframe.add(plumberButtonPanel);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setBackground(new Color(240, 204, 125));
        jframe.getContentPane().setBackground(new Color(240, 204, 125));
        jframe.setVisible(true);

    }
}
