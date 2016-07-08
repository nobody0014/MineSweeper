package Buttons;

/**
 * Created by wit on 7/5/2016.
 */
public class Bomb extends Cell {

    public Bomb(Integer[] i){
        super(i);
    }
    public void changeLabel(){
        label.setText("Bomb");
    }
    public String toString(){
        return "Bomb";
    }
}

