package Buttons;

import GameController.GameControl;

import java.awt.event.*;
import java.util.Arrays;

/**
 * Created by wit on 7/7/2016.
 */
public class EmptyListener extends NumberListener {
    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() instanceof Cell){
//
//        }
        System.out.println("This is an Empty Listener");
        Cell c = (Empty) e.getSource();
        if(!GameControl.firstClick){

        }
    }

}
