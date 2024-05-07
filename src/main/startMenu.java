package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

/**
 * start menu kirajzolásáért és működéséért felelős osztály
 */

public class startMenu extends JFrame {

    /**
     * háttérként használt panel
     */
    private ImagePanel panel = new ImagePanel(LoadSave.getFile("start_background.png"));
    /**
     * jelzi, hogy meg lett-e nyomva a start gomg
     */
    private boolean startbool = false;

    /**
     * játék indítására szolgáló gomb
     */
    private JButton startButton = new JButton("Start");

    /**
     * történetet megjelenítő gomb
     */
    private JButton storyButton = new JButton("Story");

    /**
     * beállítások megjelenítésére szolgáló gomb
     */
    private JButton settingsButton = new JButton("Settings");

    /**
     * az osztály konstruktora
     */
    public startMenu() {
        addStartButton();
        addStoryButton();
        addSettingsButton();
        initializeWindow();
    }

    /**
     * start attribútum lekérdezésére szolgáló függvény
     * @return startbool
     */
    public boolean getstart() {
        return startbool;
    }

    /**
     * ablak inicializáslását végző függvény
     */
    private void initializeWindow() {

        Controller.setRounds(10);
        this.setTitle("Start Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(800, 600);
        this.setResizable(false);
        panel.setSize(getMaximumSize());

        panel.setBackground(new Color(240, 204, 125));
        panel.setLayout(null);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * start gomb hozzáadását végző függvény
     */
    private void addStartButton() {

        startButton.setVisible(true);
        startButton.setBounds(300,460,200,50);

        startButton.setBackground(new Color(240, 204, 125));
        startButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        startButton.setHorizontalAlignment(JTextField.CENTER);

        /**
         * action listener rendelése a  gombhoz, így fogja beállítani a jelzőbitet
         */
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                startbool = true;
                dispose();
            }
        });

        panel.add(startButton);
    }

    /**
     * történetet megjelenítő gomb hozzáadását végző függvény
     */
    private void addStoryButton() {

        storyButton.setVisible(true);
        storyButton.setBounds(10,500,100,40);

        storyButton.setBackground(new Color(240, 204, 125));
        storyButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
        storyButton.setHorizontalAlignment(JTextField.CENTER);

        /**
         * action listener rendelése a gombhoz, a történet megjelenítséért felelős
         * függvényt hívja meg
         */
        storyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StoryBoard();

            }
        });

        panel.add(storyButton);
    }

    /**
     * történetet megjelenítő ablak megjelenítéséért felelős függvény
     */
    private void StoryBoard()
    {
        JFrame storyboard = new JFrame();
        storyboard.setVisible(true);
        storyboard.setTitle("Story");
        storyboard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        storyboard.setSize(1000, 610);

        JTextArea storytext = new JTextArea();
        storytext.setBounds(0,0,1000,610);
        storytext.setBackground(new Color(240, 204, 125));
        storytext.setFont(new Font("Arial", Font.PLAIN, 15));
        storytext.setEditable(false);
        storyboard.add(storytext);

        storytext.setLineWrap(true);
        storytext.setWrapStyleWord(true);

        /**
         * történet beolvasása a megfelelő fájlból
         */
        String story = "";
        try
        {
            Scanner sc = new Scanner(new File("story"));

            StringBuilder fileContents = new StringBuilder();
            while (sc.hasNextLine()) {
                fileContents.append(sc.nextLine());
                fileContents.append(System.lineSeparator());
            }
            storytext.setText(fileContents.toString());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    /**
     * beállítások gomb hozzáadása
     */
    private void addSettingsButton() {

        settingsButton.setVisible(true);
        settingsButton.setBounds(630,500,120,40);

        settingsButton.setBackground(new Color(240, 204, 125));
        settingsButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
        settingsButton.setHorizontalAlignment(JTextField.CENTER);

        /**
         * action listener rendelése a gombhoz, ami létrehoz egy beállítások ablakot
         */
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               settings settingswindow = new settings();

            }
        });

        panel.add(settingsButton);
    }


}
