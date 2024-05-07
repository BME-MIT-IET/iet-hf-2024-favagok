package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NomadMoveWindow extends JPanel implements ActionListener {
    /**
     * Megadjuk, hogy hány gom legyen.
     */
    int buttoncount;
    /**
     * Létrehozunk egy tömböt amibe később bele töljük a gombokat.
     */
    ArrayList<Button> gombok = new ArrayList<>();
    /**
     * Létrehozunk egy JFrame-t.
     */
    JFrame frame = new JFrame();

    /**
     * Az osztály konstruktora.
     * @param k
     */
    public NomadMoveWindow(int k){
        buttoncount = k;

        if(buttoncount == 0){
            frame.setVisible(true);
            Button button = new Button("nowhere to step");
            button.addActionListener(this);
            gombok.add(button);
            frame.add(button);
            frame.setSize(500,500);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        }
        for(int i = 0; i <  buttoncount; i++){
            frame.setVisible(true);
            Button button = new Button(Controller.getCurrentNomad().getPosition().listNeighbours().get(i).GetName());
            button.addActionListener(this);
            gombok.add(button);
            frame.add(button);
            frame.setSize(500,500);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        }
    }

    /**
     * Ez a függvény felelős azért, hogy amikor a felhasználó rányom egy gomra akkor az történjen ami a gomra van írva.
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e){
        for(int i = 0; i < gombok.size(); i++){
            if(e.getActionCommand().equals(gombok.get(i).getLabel())){

                int segedszam = 0;
                for(int j = 0; j < Main.game.Fields().size(); j++){
                    if(Main.game.Fields().get(j).GetName().equals(e.getActionCommand())){
                        segedszam = j;
                    }
                }
                Controller.getCurrentNomad().move(Main.game.Fields().get(segedszam));
                break;
            }
        }
        frame.dispose();
        NomadPipeInteraction ppi = new NomadPipeInteraction();
    }
}
