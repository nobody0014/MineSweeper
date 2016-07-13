package Buttons;

import GameController.GameControl;

import javax.crypto.Cipher;
import java.awt.*;

/**
 * Created by wit on 7/5/2016.
 */
public class Bomb extends Cell {
    public Bomb(int[] i){
        super(i);
    }
    public int reveal(){
        if((!isLeftClicked && !isMarked) || GameControl.gameOver){
            this.setText("B");
            isLeftClicked = true;
            this.setBackground(Color.RED);
            this.setEnabled(false);
            GameControl.gameOver = true;
        }
        return 0;
    }
    public String toString(){
        return "Bomb";
    }
}

