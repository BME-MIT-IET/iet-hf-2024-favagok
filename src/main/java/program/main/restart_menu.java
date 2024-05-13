package program.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * a játék végén megjelenő menü megjelenítéséért felelős osztály
 */
public class restart_menu extends JFrame{

    /**
     * újraindítást jelző boolean
     */
    private static boolean restart_boolean = false;

    /**
     * kilépést jelző boolean
     */
    private static boolean quit_boolean= false;

    /**
     * háttérként használt panel
     */
    private ImagePanel panel = new ImagePanel(LoadSave.getFile("start_background.png"));

    /**
     * újraíndító gomb
     */
    private JButton restart_button = new JButton("restart");
    /**
     * kilépésre szolgáló gomb
     */
    private JButton quit_button = new JButton("quit");

    /**
     * nomadok pontjait megjelenítő textfiled
     */
    private JTextField nomad_textfield = new JTextField(1);

    /**
     * szerelők pontjait megjelenítő textfiled
     */
    private JTextField plumber_textfield = new JTextField(1);

    /**
     * játék győztesét megjelnítő textfield
     */
    private JTextField winner_textfield = new JTextField(1);

    /**
     * az osztály konstruktora
     */
    restart_menu()
    {
        quit_boolean=false;
        restart_boolean=false;

        addQuitbutton();
        addRestartbutton();

        addWinner();
        addNomadPoints();
        addPlumberPoints();

        initializewindow();

    }

    /**
     * restart boolean lekérésére szolgáló függvény
     * @return restart boolean
     */
    public boolean getRestart()
    {
        return restart_boolean;
    }

    /**
     * quit boolean lekérésére szolgáló függvény
     * @return quit boolean
     */
    public boolean getQuit()
    {
        return quit_boolean;
    }


    /**
     * ablak inicializáslására szolgáló függvény
     */
    private void initializewindow()
    {

        this.setTitle("Restart Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setLayout(null);
        this.setSize(800, 600);
        this.setResizable(false);
        panel.setSize(getMaximumSize());

        panel.setBackground(new Color(240, 204, 125));
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * kilépésre szolgáló gomb ablakhoz adására szolgáló függvény
     */
    private void addQuitbutton()
    {
        quit_button.setVisible(true);
        quit_button.setBounds(300,460,200,50);

        quit_button.setBackground(new Color(240, 204, 125));
        quit_button.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        quit_button.setHorizontalAlignment(JTextField.CENTER);

        /**
         * action listener hozzáadása a gombhoz, ez állítja majd a jelzőbiteket
         */
        quit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                restart_boolean=false;
                quit_boolean = true;
                dispose();
            }
        });

        this.add(quit_button);
    }

    /**
     * újraindításra szolgáló gomb ablakhoz adására szolgáló függvény
     */
    private void addRestartbutton()
    {
        restart_button.setVisible(true);
        restart_button.setBounds(300,400,200,50);

        restart_button.setBackground(new Color(240, 204, 125));
        restart_button.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        restart_button.setHorizontalAlignment(JTextField.CENTER);

        /**
         * action listener hozzáadása a gombhoz, ez állítja majd a jelzőbitet
         */
        restart_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                restart_boolean = true;
                dispose();
            }
        });

        this.add(restart_button);
    }

    /**
     * győztes kiírására szolgáló textfieldért felelős függvény
     */
    private void addWinner()
    {
        if(Controller.getPlumberPoints()>Controller.getNomadPoints())
            winner_textfield.setText("Plumbers won!");
        if(Controller.getPlumberPoints()<Controller.getNomadPoints())
            winner_textfield.setText("Nomads won!");
        if(Controller.getNomadPoints()==Controller.getPlumberPoints())
            winner_textfield.setText("Its a tie!");

        winner_textfield.setBackground(new Color(240, 204, 125));
        winner_textfield.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        winner_textfield.setHorizontalAlignment(JTextField.CENTER);

        winner_textfield.setBounds(0,50,800,50);
        winner_textfield.setVisible(true);
        winner_textfield.setEditable(false);
        this.add(winner_textfield);
    }

    /**
     * nomadok pontjainak kiírására szolgáló textfieldért felelős függvény
     */
    private void addNomadPoints()
    {
        int points = Controller.getNomadPoints();
        nomad_textfield.setText("Nomads: "+ points);

        nomad_textfield.setBackground(new Color(240, 204, 125));
        nomad_textfield.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        nomad_textfield.setHorizontalAlignment(JTextField.CENTER);

        nomad_textfield.setBounds(300,160,200,30);
        nomad_textfield.setVisible(true);
        nomad_textfield.setEditable(false);
        this.add(nomad_textfield);
    }

    /**
     * szerelők pontjainak kiírására szolgáló textfieldért felelős függvény
     */
    private void addPlumberPoints()
    {
        int points = Controller.getPlumberPoints();
        plumber_textfield.setText("Plumbers: "+ points);

        plumber_textfield.setBackground(new Color(240, 204, 125));
        plumber_textfield.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        plumber_textfield.setHorizontalAlignment(JTextField.CENTER);

        plumber_textfield.setBounds(300,240,200,30);
        plumber_textfield.setVisible(true);
        plumber_textfield.setEditable(false);
        this.add(plumber_textfield);
    }

}
