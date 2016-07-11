package Buttons;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wit on 7/5/2016.
 */
public class Cell extends JButton {
    CellListerner cellListen;
    int[] pos;
    int element;
    public Cell(int[] i){
        pos = i;
        cellListen = new CellListerner();
        addActionListener(cellListen);
        this.setPreferredSize(new Dimension(45,45));
        this.setText("");
    }
    public void reveal(){
        this.setText("a");
    }
    public int[] getPos(){
        return pos;
    }
    public String toString(){
        return "";
    }


}
