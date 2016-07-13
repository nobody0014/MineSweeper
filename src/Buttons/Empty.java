package Buttons;

import java.awt.*;

/**
 * Created by wit on 7/6/2016.
 */
public class Empty extends Number {
    public Empty(int[] i,int noBombs){
        super(i,noBombs);
    }
    public int reveal(){
        if(!isLeftClicked && !isMarked){
            isLeftClicked = true;
            this.setBackground(Color.GRAY);
            this.setEnabled(false);
        }
        return 0;
    }
    public String toString(){
        return "Empty";
    }
}
