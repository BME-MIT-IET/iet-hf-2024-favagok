package program.main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    /**
     * File nevek
     */
    public static final String NOMAD_1 = "nomad_0.png";
    public static final String NOMAD_2 = "nomad_1.png";
    public static final String NOMAD_3 = "nomad_2.png";
    public static final String NOMAD_4 = "nomad_3.png";
    public static final String PLUMBER_1 = "plumber_0.png";
    public static final String PLUMBER_2 = "plumber_1.png";
    public static final String PLUMBER_3 = "plumber_2.png";
    public static final String PLUMBER_4 = "plumber_3.png";

    public static final String CISTERN = "cistern.png";
    public static final String SOURCE = "source.png";

    public static final String PUMP_BROKEN_FULL = "pump_broken_full.png";
    public static final String PUMP_BROKEN_EMPTY = "pump_broken_empty.png";
    public static final String PUMP_WORKING_EMPTY = "pump_working_empty.png";
    public static final String PUMP_WORKING_FULL = "pump_working_full.png";
    public static final String MENU_BACK_G = "menuBackGr.png";
    public static final String ENEMY_ATLAS = "enemy_spritesheet.png";
    public static final String STATUS_BAR = "Health_Bar.png";

    public static final String CACTUS = "cactus.png";

    /**
     * Betölti a képeket
     */
    public static BufferedImage getFile(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("resources/" + fileName);
        try {
            if(is!=null)img = ImageIO.read(is);
            /**megprobál kivételt elkapni IO-ból**/
        } catch (IOException e) {
            e.printStackTrace();
            /**bezárja az InputStream-et**/
        } finally {
            try {
                if(is!=null)is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }


}


