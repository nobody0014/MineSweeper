package Buttons;

/**
 * Created by wit on 7/5/2016.
 */
public class Bomb extends Cell {
    BombListener bombListen;
    public Bomb(Integer[] i){
        super(i);
        bombListen = new BombListener();
        addActionListener(bombListen);
    }
    public void changeLabel(){
        label.setText("Bomb");
    }
    public String toString(){
        return "Bomb";
    }
}

