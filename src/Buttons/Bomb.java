package Buttons;

/**
 * Created by wit on 7/5/2016.
 */
public class Bomb extends Cell {
    BombListener bombListen;
    public Bomb(int[] i){
        super(i);
        bombListen = new BombListener();
        addActionListener(bombListen);
    }
    public void reveal(){this.setText("B");}
    public String toString(){
        return "Bomb";
    }
}

