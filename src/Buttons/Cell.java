package Buttons;

import javax.swing.*;

/**
 * Created by wit on 7/5/2016.
 */
public class Cell extends JButton {
    CellListerner cellListen;
    Integer[] pos;
    JLabel label;
    int element;
    public Cell(Integer[] i){
        pos = i;
        cellListen = new CellListerner();
        addActionListener(cellListen);
        label = new JLabel();
    }
    public void changeLabel(){
        label.setText("");
    }
    public String toString(){
        return "";
    }

}
