package states;

import entities.Nomad;
import entities.Player;
import entities.Plumber;
import main.Game;
import main.GameWindow;
import main.LoadSave;
import things.*;
import things.Spring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class InGame {
    Game game;

    private boolean gameOver = false;
    /**
     * InGame konstruktora
     **/
    public InGame(Game g) {
        game = g;
        inClasses();
    }
    /**
     * inClasse létre hozza a pályát és beállítja az értékeket
     **/
    private void inClasses() {
        Cistern cistern1 = new Cistern("cistern1", LoadSave.CISTERN);
        cistern1.setX(700);
        cistern1.setY(300);
        game.Fields().add(cistern1);

        Spring spring1 = new Spring("spring1", LoadSave.SOURCE);
        spring1.setX(100);
        spring1.setY(300);
        game.Fields().add(spring1);

        Pump pump1 = new Pump("pump1", LoadSave.PUMP_WORKING_EMPTY);
        pump1.setX(300);
        pump1.setY(100);


        game.Fields().add(pump1);
        Pump pump2 = new Pump("pump2", LoadSave.PUMP_WORKING_EMPTY);
        pump2.setX(300);
        pump2.setY(500);
        game.Fields().add(pump2);
        Pump pump3 = new Pump("pump3", LoadSave.PUMP_WORKING_EMPTY);
        pump3.setX(500);
        pump3.setY(100);

        game.Fields().add(pump3);
        Pump pump4 = new Pump("pump4", LoadSave.PUMP_WORKING_EMPTY);
        pump4.setX(500);
        pump4.setY(500);
        game.Fields().add(pump4);


        Nomad nomad1 = new Nomad("nomad_1", 10, pump1, LoadSave.NOMAD_1);
        game.Nomads().add(nomad1);

        Nomad nomad2 = new Nomad("nomad_2", 10, pump2, LoadSave.NOMAD_2);
        game.Nomads().add(nomad2);

        Nomad nomad3 = new Nomad("nomad_3", 10, pump3, LoadSave.NOMAD_3);
        game.Nomads().add(nomad3);

        Nomad nomad4 = new Nomad("nomad_4", 10, pump4, LoadSave.NOMAD_4);
        game.Nomads().add(nomad4);

        Plumber plumber1 = new Plumber("plumber_1", 10, cistern1, LoadSave.PLUMBER_1);
        game.Plumbers().add(plumber1);

        Plumber plumber2 = new Plumber("plumber_2", 10, cistern1, LoadSave.PLUMBER_2);
        game.Plumbers().add(plumber2);

        Plumber plumber3 = new Plumber("plumber_3", 10, cistern1, LoadSave.PLUMBER_3);
        game.Plumbers().add(plumber3);

        Plumber plumber4 = new Plumber("plumber_4", 10, cistern1, LoadSave.PLUMBER_4);
        game.Plumbers().add(plumber4);

        Pipe pipe1 = new Pipe(pump2, pump4, "pipe1");
        game.Pipes().add(pipe1);

        Pipe pipe2 = new Pipe(pump1, pump3, "pipe2");
        game.Pipes().add(pipe2);

        Pipe pipe3 = new Pipe(pump1, pump4, "pipe3");
        game.Pipes().add(pipe3);

        Pipe pipe4 = new Pipe(pump2, cistern1, "pipe4");
        game.Pipes().add(pipe4);

        Pipe pipe5 = new Pipe(pump1, spring1, "pipe5");
        game.Pipes().add(pipe5);

        Pipe pipe6 = new Pipe(pump2, spring1, "pipe6");
        game.Pipes().add(pipe6);

        Pipe pipe7 = new Pipe(pump3, cistern1, "pipe7");
        game.Pipes().add(pipe7);

        Pipe pipe8 = new Pipe(pump4, cistern1, "pipe8");
        game.Pipes().add(pipe8);

        pump1.changePipeIn(pipe5);
        pump1.changePipeOut(pipe3);

        pump2.changePipeIn(pipe6);
        pump2.changePipeOut(pipe4);

        pump3.changePipeIn(pipe2);
        pump3.changePipeOut(pipe7);

        pump4.changePipeIn(pipe3);
        pump4.changePipeOut(pipe8);

        cistern1.changePipeIn(pipe4);

    }
    /**
     * meghivja a pálya elemek draw() fv-ét
     **/
    public void draw(Graphics g) {
        for (Cactus c : game.Cactuses()) c.draw(g);
        for (Pipe p : game.Pipes()) p.draw(g);
        for (Pump p : game.Fields()) p.draw(g);
        for (Player p : game.GetPlayers()) p.draw(g);
    }


}
