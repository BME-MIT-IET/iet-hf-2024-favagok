package program.main;

import javax.crypto.spec.PSource;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Game implements Runnable {
    /**
     * attributumok
     */
    private GameWindow gameWindow;

    public GamePanel gamePanel;
    private startMenu startmenu;
    public PlumberButtonPanel plumberButtonPanel;
    public NomadButtonPanel nomadButtonPanel;

    private restart_menu restartmenu;

    private Controller controller;

    private InGame ingame;
    private ArrayList<Plumber> plumbers = new ArrayList<Plumber>();
    private ArrayList<Nomad> nomads = new ArrayList<Nomad>();
    private ArrayList<Pump> fields = new ArrayList<Pump>();
    private ArrayList<Pipe> pipes = new ArrayList<Pipe>();
    private ArrayList<Cactus> cactuses = new ArrayList<Cactus>();

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private int frames = 0;
    private int update = 0;

    /**
     * Game konstruktora
     **/
    public Game() {

        try {
            startmenu = new startMenu();
            while (!startmenu.getstart()) {
                Thread.sleep(10);
            }
            ;
        } catch (Exception e) {

        }

        initalize();
        startGameLoop();

    }

    /**
     * Elindítja a játék fő ciklusát futtató szálat.
     */
    private void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Game inicializalo metodusa egy alap játék létrehozása a testhez
     */
    public void initalize() {
        controller = new Controller();
        cactuses.clear();
        for (int i = 0; i < 30; i++) {
            cactuses.add(new Cactus("cactus.png"));
        }
        nomads.clear();
        plumbers.clear();
        fields.clear();
        pipes.clear();
        ingame = new InGame(this);
        gamePanel = new GamePanel(this);
        plumberButtonPanel = new PlumberButtonPanel();
        nomadButtonPanel = new NomadButtonPanel();
        gameWindow = new GameWindow(gamePanel, plumberButtonPanel, nomadButtonPanel);
        gamePanel.requestFocus();
    }

    /**
     * Game Playerek listája
     *
     * @return players
     */
    public ArrayList<Player> GetPlayers() {
        // System.out.printf("%s.%s()%n", "game", "Players");
        ArrayList<Player> temp = new ArrayList<Player>();
        temp.addAll(nomads);
        temp.addAll(plumbers);
        return temp;
    }

    /**
     * Visszaadja a nomád játékosokat tartalmazó listát.
     *
     * @return Az ArrayList objektum, amely tartalmazza a nomád játékosokat.
     */
    public ArrayList<Nomad> Nomads() {
        // System.out.printf("%s.%s()%n", "game", "Players");
        return nomads;
    }

    /**
     * Visszaadja a vízszerelő játékosokat tartalmazó listát.
     *
     * @return Az ArrayList objektum, amely tartalmazza a vízszerelő játékosokat.
     */
    public ArrayList<Plumber> Plumbers() {
        // System.out.printf("%s.%s()%n", "game", "Players");
        return plumbers;
    }

    /**
     * Game Pumpok listája
     *
     * @return fields
     */
    public ArrayList<Pump> Fields() {
        // System.out.printf("%s.%s()%n", "game", "Fields");
        return fields;
    }

    public ArrayList<Cactus> Cactuses() {
        return cactuses;
    }

    /**
     * Az aktuális játékállapotot megjelenítő metódus.
     *
     * @param g Az `Graphics` objektum, amelyen a játékállapotot megjelenítjük.
     * @throws InterruptedException Ha a szál megszakad, kivétel keletkezhet.
     */
    public void render(Graphics g) throws InterruptedException {
        ingame.draw(g);
        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(2, 0, 100, 70);
        g2d.setColor(new Color(240, 204, 125));
        g2d.fillRect(3, 3, 99, 67);
        g2d.setColor(Color.BLACK);

        g2d.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));
        g2d.drawString("Current Player:", 5, 12);


        if (!Controller.getCurrentTurnIsNomad()) {
            if (controller.CurrentPlumberTurn() != null)
                g.drawImage(controller.CurrentPlumberTurn().getPicture(), 13, 15, 50, 50, null);
        } else if (controller.CurrentNomadTurn() != null)
            g.drawImage(controller.CurrentNomadTurn().getPicture(), 13, 15, 50, 50, null);
    }

    /**
     * Visszaadja a mezők számát.
     *
     * @return A mezők száma.
     */
    public int Fieldsize() {
        return fields.size();
    }

    /**
     * Visszaadja a csöveket tartalmazó listát.
     *
     * @return Az ArrayList objektum, amely tartalmazza a csöveket.
     */
    public ArrayList<Pipe> Pipes() {
        return pipes;
    }

    /**
     * Futtatja a játék fő ciklusát.
     */
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdata = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();
        double lamdaU = 0;
        double lamdaF = 0;
        while (true) {
            long currentTime = System.nanoTime();

            lamdaU += (currentTime - previousTime) / timePerUpdata;
            lamdaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if (lamdaU >= 1) {
                controller.turn();
                Main.game.gamePanel.repaint();
                update++;
                lamdaU--;
            }
            if (lamdaF >= 1) {
                Main.game.gamePanel.repaint();
                frames++;
                lamdaF--;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                //System.out.println("FPS " + frames + " | UPS " + update);
                frames = 0;
                update = 0;
            }
            if (controller.GameOver) {

                restartmenu = new restart_menu();
                try {

                    while (!(restartmenu.getRestart() || restartmenu.getQuit())) {
                        //restart = !restartmenu.getRestart();
                        Thread.sleep(10);
                    }
                    //ingame = new InGame(this);
                    //initalize();


                } catch (Exception e) {
                }
                if (restartmenu.getQuit()) System.exit(0);
            }
        }

    }

    /**
     * Visszaadja a játékablak objektumot.
     *
     * @return A játékablak objektum.
     */
    public GameWindow getGameWindow() {
        return gameWindow;
    }
}
