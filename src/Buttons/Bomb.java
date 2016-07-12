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
    public int reveal(){
        if(!isLeftClicked && !isMarked){
            this.setText("B");
            isLeftClicked = true;
        }
        return 0;
    }
    public String toString(){
        return "Bomb";
    }
}

