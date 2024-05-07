package things;

import main.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Cactus {
    /**
     * attributumok
     */
    BufferedImage file_pic;
    int x;
    int y;
    int w;
    int h;

    /**
     * Cactus konstruktora
     */
    public Cactus(String fileName) {
        file_pic = LoadSave.getFile(fileName);

        Random rand = new Random();
        w = rand.nextInt(16, 50);
        h = w;
        x = rand.nextInt(800 - w);
        y = rand.nextInt(600 - h);
    }
    /**
     * Cactus render fv-e
     */
    public void render(Graphics g) {
        g.drawImage(file_pic, x, y, w, h, null);
    }

    /**
     * Cactus draw fv-e
     */
    public void draw(Graphics g) {
        render(g);
    }

}
