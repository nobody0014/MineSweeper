package Buttons;

/**
 * Created by wit on 7/5/2016.
 */
public class Number extends Cell {
    NumberListener numListen;
    public Number(int i,String noBombs){
        super(i);
        element = noBombs;
        numListen = new NumberListener();
    }
}
