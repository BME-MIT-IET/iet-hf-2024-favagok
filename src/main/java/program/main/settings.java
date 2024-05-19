package program.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

/**
 * beállítások ablakért felelős osztály
 */
public class settings extends JFrame {

    /**
     * háttérként szolgáló panel
     */
    private ImagePanel panel = new ImagePanel(LoadSave.getFile("start_background.png"));

    /**
     * az osztály konstruktora
     */
    public settings() {
        inicializewindow();
        addplayercooldown();
        addrounds();
    }

    /**
     * az ablak inicializálásáért felelős függvény
     */
    private void inicializewindow() {

        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(450, 190);
        panel.setSize(getMaximumSize());

        panel.setBackground(new Color(240, 204, 125));
        panel.setLayout(null);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * player cooldown beállításához szükséges dolgok meglenítését végző függvény
     */
    private void addplayercooldown() {

        JTextField textfield = new JTextField();
        Integer cooldown = Controller.getNomadCoolDown();
        textfield.setText("Cooldown time: " + cooldown);

        textfield.setBackground(new Color(240, 204, 125));
        textfield.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        textfield.setHorizontalAlignment(JTextField.CENTER);

        textfield.setBounds(0, 0, 450, 30);
        textfield.setVisible(true);
        textfield.setEditable(false);

        JTextField textfield2 = new JTextField();
        textfield2.setText("Set it to:");

        textfield2.setBackground(new Color(240, 204, 125));
        textfield2.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        textfield2.setHorizontalAlignment(JTextField.CENTER);

        textfield2.setBounds(0, 40, 100, 30);
        textfield2.setVisible(true);
        textfield2.setEditable(false);

        /**
         * ebbe a  textfieldbe adhatja meg afelhasználó a beállítani
         * kívánt értéket
         * */
        JTextField textfield3 = new JTextField();
        cooldown = Controller.getNomadCoolDown() + 2;
        textfield3.setText(cooldown.toString());


        textfield3.setBackground(new Color(255, 255, 255, 255));
        textfield3.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        textfield3.setHorizontalAlignment(JTextField.CENTER);

        textfield3.setBounds(100, 40, 80, 30);
        textfield3.setVisible(true);

        JButton setButton = new JButton("Set");
        setButton.setVisible(true);
        setButton.setBounds(180, 40, 80, 30);

        setButton.setBackground(new Color(240, 204, 125));
        setButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
        setButton.setHorizontalAlignment(JTextField.CENTER);

        /**
         * action listener rendelése a gombhoz, ennek hatására állítódik be a megadott érték
         * és a kiírás is megváltozik
         */
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Controller.setCoolDown(Integer.parseInt(textfield3.getText()));
                    int cooldown = Controller.getCoolDown();
                    textfield.setText("Cooldown time: " + cooldown);
                } catch (Exception ee) {

                }
            }
        });

        panel.add(textfield);
        panel.add(textfield2);
        panel.add(textfield3);
        panel.add(setButton);

    }

    private void addrounds() {

        JTextField textfield = new JTextField();
        Integer rounds = Controller.getRounds();
        textfield.setText("Rounds : " + rounds);


        textfield.setBackground(new Color(240, 204, 125));
        textfield.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        textfield.setHorizontalAlignment(JTextField.CENTER);

        textfield.setBounds(0, 80, 450, 30);
        textfield.setVisible(true);
        textfield.setEditable(false);

        JTextField textfield2 = new JTextField();
        textfield2.setText("Set it to:");

        textfield2.setBackground(new Color(240, 204, 125));
        textfield2.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        textfield2.setHorizontalAlignment(JTextField.CENTER);

        textfield2.setBounds(0, 120, 100, 30);
        textfield2.setVisible(true);
        textfield2.setEditable(false);

        /**
         * ebbe a  textfieldbe adhatja meg afelhasználó a beállítani
         * kívánt értéket
         * */
        JTextField textfield3 = new JTextField();
        rounds = Controller.getRounds() + 5;
        textfield3.setText(rounds.toString());


        textfield3.setBackground(new Color(255, 255, 255, 255));
        textfield3.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        textfield3.setHorizontalAlignment(JTextField.CENTER);

        textfield3.setBounds(100, 120, 80, 30);
        textfield3.setVisible(true);

        JButton setButton = new JButton("Set");
        setButton.setVisible(true);
        setButton.setBounds(180, 120, 80, 30);

        setButton.setBackground(new Color(240, 204, 125));
        setButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
        setButton.setHorizontalAlignment(JTextField.CENTER);

        /**
         * action listener rendelése a gombhoz, ennek hatására állítódik be a megadott érték
         * és a kiírás is megváltozik
         */
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Controller.setRounds(Math.max(1, Integer.parseInt(textfield3.getText())));
                    int rounds = Controller.getRounds();
                    textfield.setText("Rounds: " + rounds);
                } catch (Exception ee) {

                }
            }
        });

        panel.add(textfield);
        panel.add(textfield2);
        panel.add(textfield3);
        panel.add(setButton);

    }


}
