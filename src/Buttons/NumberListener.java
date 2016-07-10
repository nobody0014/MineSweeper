package Buttons;

import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * Created by wit on 7/6/2016.
 */
public class NumberListener extends CellListerner {
    public void actionPerformed(ActionEvent e) {
        System.out.println("This is a Number");
        Cell c = (Number) e.getSource();
        System.out.println(Arrays.toString(c.getPos()));
        c.changeLabel();
    }
}
