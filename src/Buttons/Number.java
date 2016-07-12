package Buttons;

/**
 * Created by wit on 7/5/2016.
 */
public class Number extends Cell {
    NumberListener numListen;
    public Number(int[] i,int noBombs){
        super(i);
        element = noBombs;
        numListen = new NumberListener();
        addActionListener(numListen);
    }
    public void reveal(){
        if(!isLeftClicked){
            this.setText(String.valueOf(element));
            isLeftClicked = true;
        }
    }
    public String toString(){
        return "Number";
    }
}
