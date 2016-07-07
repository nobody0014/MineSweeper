package Buttons;

import javax.swing.*;

/**
 * Created by wit on 7/5/2016.
 */
public class Cell extends JButton {
    CellListerner cellListen;
    int element;
    Integer[] pos;
    public Cell(Integer[] i){
        pos = i;
        cellListen = new CellListerner();
    }
    public int reveal(){
        return element;
    }
    public String toString(){
        return "";
    }

}
