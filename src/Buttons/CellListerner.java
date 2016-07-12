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
        cellClicked = (Cell) e.getSource();
        System.out.println(Arrays.toString(cellClicked.getPos()));
        System.out.println("A Cell");
        cellClicked.reveal();

    }
}
