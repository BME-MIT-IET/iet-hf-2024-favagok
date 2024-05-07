package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlumberPickUpWindow implements ActionListener {
    /**
     * Ennyi darab gombot fogunk tárolni.
     */
    int buttoncount;
    /**
     * Ebben fogjuk tárolni a gombokat.
     */
    ArrayList<Button> gombok = new ArrayList<>();
    /**
     * Létrehozunk egy JFrame-t.
     */
    JFrame frame = new JFrame();

    /**
     * Az osztály konstruktora
     * @param k
     */
    public PlumberPickUpWindow(int k){
        buttoncount = k;

        for(int i = 0; i <  buttoncount; i++){
            frame.setVisible(true);
            Button button = new Button(Controller.getCurrentPlumber().getPosition().getConnectedPipes().get(i).GetName());
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
                for(int j = 0; j < Main.game.Pipes().size(); j++){
                    if(Main.game.Pipes().get(j).GetName().equals(e.getActionCommand())){
                        segedszam = j;
                    }
                }
                Controller.getCurrentPlumber().pickUpPipe(Main.game.Pipes().get(segedszam));
                break;
            }
        }
        frame.dispose();
        //PlumberPipeInteraction ppi = new PlumberPipeInteraction();
    }
}
