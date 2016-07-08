package Buttons;

import GameController.GameControl;
import GameController.GameStatus;

import java.awt.event.*;

/**
 * Created by wit on 7/6/2016.
 */
public class CellListerner implements ActionListener {
    Cell cellClicked;
    public void actionPerformed(ActionEvent e){
        System.out.println("This is a cell, it does nothing.");
        // There is no need to use if, im sure that the this listener will only be in Cell and its sub classses
        cellClicked = (Cell) e.getSource();
        if(!GameStatus.firstClick){

            GameStatus.firstClick = true;
        }

    }
}
