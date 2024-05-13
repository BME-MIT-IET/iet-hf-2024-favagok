package program.main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GamePanel extends JPanel {
    /**
     * attributumok
     */
    private static Game game;

    private JTextField plumberpoints = new JTextField();

    private JTextField nomadpoints = new JTextField();

    private JTextField roundsleft = new JTextField();
    /**
     * GamePanel konstruktora
     **/
    public GamePanel(Game game) {


        this.game = game;

        this.setBackground(new Color(240, 204, 125));
        this.setPanelSize(810, 600);
        this.setLayout(null);
        this.setVisible(true);


        plumberpoints.setBounds(610, 0, 200, 50);
        plumberpoints.setBackground(new Color(240, 204, 125));
        plumberpoints.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        this.add(plumberpoints);
        plumberpoints.setBorder(new LineBorder(Color.BLACK, 2));
        plumberpoints.setEditable(false);
        plumberpoints.setVisible(true);


        nomadpoints.setBounds(610, 50, 200, 50);
        nomadpoints.setBackground(new Color(240, 204, 125));
        nomadpoints.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        this.add(nomadpoints);
        nomadpoints.setBorder(new LineBorder(Color.BLACK, 2));
        nomadpoints.setEditable(false);
        nomadpoints.setVisible(true);

        roundsleft.setBounds(103, 0, 200, 50);
        roundsleft.setBackground(new Color(240, 204, 125));
        roundsleft.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        this.add(roundsleft);
        roundsleft.setBorder(new LineBorder(Color.BLACK, 2));
        roundsleft.setEditable(false);
        roundsleft.setVisible(true);

    }


    /**
     * Panel mérete
     */
    private void setPanelSize(int x, int y) {
        Dimension size = new Dimension(x, y);
        setMaximumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    /**
     * Elemek kirajzolása
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            game.render(g);
            nomadpoints.setText("Nomads: " + Controller.getNomadPoints());
            plumberpoints.setText("Plumbers: " + Controller.getPlumberPoints());
            roundsleft.setText("Turns left: " + Controller.getRounds());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @return game
     */
    public Game getGame() {
        return game;
    }
}
