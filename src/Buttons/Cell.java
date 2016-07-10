package Buttons;

import javax.swing.*;
import java.awt.*;

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
        this.setPreferredSize(new Dimension(25,25));
        label = new JLabel();
    }
    public void changeLabel(){
        label.setText("");
    }
    public Integer[] getPos(){
        return pos;
    }
    public String toString(){
        return "";
    }


}
