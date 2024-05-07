package main;

import entities.Nomad;
import entities.Player;
import entities.Plumber;
import things.*;
import things.Spring;


import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Ez a Main class csak a teszetléshez készült, annak adja a keretet,
 * a végső programban nem/nem ilyen formában fog szerpelni
 */
public class Main {


    public static Game game = new Game();
    /**
     * a cmd ArrayListben tároljuk el a konzolról beérkezett parancsokat
     */
    static ArrayList<String> cmds = new ArrayList<>();


    /**
     * A játékban véletlenszerű események is szerpelnek, ezeket lehet véletlenszerure,
     * vagy determinisztikusra állítani a random booleannal
     */
    static boolean random = true;

    public static void main(String[] args) {
        Player.setGame(game);
    }

}