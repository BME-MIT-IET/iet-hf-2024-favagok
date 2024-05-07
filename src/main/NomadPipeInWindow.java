package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NomadPipeInWindow implements ActionListener {
    /**
     * Megadja, hogy hány gomb lesz.
     */
    int buttoncount;
    /**
     * Ebbe bele fogjuk pakolni a gombokat.
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
    public NomadPipeInWindow(int k){
        buttoncount = k;

        for(int i = 0; i <  buttoncount; i++){
            frame.setVisible(true);
            if(Controller.getCurrentNomad().getPosition().getConnectedPipes().get(i) != Controller.getCurrentNomad().getPosition().getPipeOut()){
                Button button = new Button(Controller.getCurrentNomad().getPosition().getConnectedPipes().get(i).GetName());
                button.addActionListener(this);
                gombok.add(button);
                frame.add(button);
            }

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
                for(int j = 0; j < Main.game.Pipes().size(); j++){
                    if(Main.game.Pipes().get(j).GetName().equals(e.getActionCommand())){
                        segedszam = j;
                    }
                }
                Controller.getCurrentNomad().changePumpIn(Main.game.Pipes().get(segedszam));
                break;
            }
        }
        frame.dispose();
        //NomadPipeInteraction ppi = new NomadPipeInteraction();
    }
}
