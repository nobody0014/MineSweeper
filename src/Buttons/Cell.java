package Buttons;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wit on 7/5/2016.
 */
public class Cell extends JButton {
    int[] pos;
    int element;
    boolean isLeftClicked;
    boolean isMarked;
    public Cell(int[] i){
        pos = i;
        this.setPreferredSize(new Dimension(45,45));
        this.setText("");
        isMarked = false;
        isLeftClicked = false;
        this.setBackground(Color.WHITE);
    }
    // this reveal version is the left click version
    public int reveal(){
        if(!isLeftClicked && !isMarked){
            this.setText("a");
            isLeftClicked = true;
        }
        return 0;
    }
    // this overload version will make the actually place the beware sign
    public int reveal(String s){
        if(!isLeftClicked && s.equals("right")){
            if(!isMarked){
                this.setText("M");
                isMarked = true;
                this.setBackground(Color.YELLOW);
                return 1;
            }
            else if(isMarked){
                this.setText("");
                this.setBackground(Color.WHITE);
                isMarked = false;
                return -1;
            }
        }
        return 0;
    }
    public boolean getIsLeftClicked(){
        return isLeftClicked;
    }
    public boolean getIsMarked(){
        return isMarked;
    }
    public int[] getPos(){
        return pos;
    }
    public String toString(){
        return "";
    }


}
