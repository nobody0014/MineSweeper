package Buttons;

import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * Created by wit on 7/7/2016.
 */
public class BombListener extends CellListerner {

    public void actionPerformed(ActionEvent e) {
        System.out.println("This is a bomb");
        Cell c = (Bomb) e.getSource();
    }
}
