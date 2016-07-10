package Buttons;

import GameController.GameControl;

import java.awt.event.*;
import java.util.Arrays;

/**
 * Created by wit on 7/6/2016.
 */
public class CellListerner implements ActionListener {
    Cell cellClicked;
    public void actionPerformed(ActionEvent e){
        System.out.println("This is a cell, it does nothing.");
        // There is no need to use if, im sure that the this listener will only be in Cell and its sub classses
        cellClicked = (Cell) e.getSource();
        if(!GameControl.firstClick){
            GameControl.firstClick = true;
        }
        System.out.println(Arrays.toString(cellClicked.getPos()));
    }
}
