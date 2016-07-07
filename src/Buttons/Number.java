package Buttons;

/**
 * Created by wit on 7/5/2016.
 */
public class Number extends Cell {
    NumberListener numListen;
    public Number(Integer[] i,int noBombs){
        super(i);
        element = noBombs;
        numListen = new NumberListener();
    }
    public String toString(){
        return "Number";
    }
}
