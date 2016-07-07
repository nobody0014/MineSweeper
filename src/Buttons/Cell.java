package Buttons;

import javax.swing.*;

/**
 * Created by wit on 7/5/2016.
 */
public class Cell extends JButton {
    CellListerner cellListen;
    String element;
    int pos;
    public Cell(int i){
        pos = i;
        cellListen = new CellListerner();
    }
    public String reveal(){
        return element;
    }
}
