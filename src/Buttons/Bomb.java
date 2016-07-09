package Buttons;

/**
 * Created by wit on 7/5/2016.
 */
public class Bomb extends Cell {
    BombListener bombListen;
    public Bomb(Integer[] i){
        super(i);
    }
    public void changeLabel(){
        bombListen = new BombListener();
        addActionListener(bombListen);
        label.setText("Bomb");
    }
    public String toString(){
        return "Bomb";
    }
}

