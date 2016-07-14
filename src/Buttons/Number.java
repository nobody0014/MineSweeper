package Buttons;

import GameController.GameControl;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wit on 7/5/2016.
 */
public class Number extends Cell {
    //need this because when the button is set disabled, its text become grey out, so we creat container inside
    JLabel numberLabel;
    public Number(int[] i,int noBombs){
        super(i);
        element = noBombs;
        numberLabel = new JLabel();
        numberLabel.setBackground(Color.GRAY);
        this.add(numberLabel);
    }
    public int reveal(){
        if((!isLeftClicked && !isMarked) || GameControl.gameOver){
            numberLabel.setText(String.valueOf(element));
            isLeftClicked = true;
            if(this.element == 1){
                numberLabel.setForeground(Color.BLACK);
            }
            else if(this.element == 2){
                numberLabel.setForeground(Color.GREEN);
            }
            else if(this.element == 3){
                numberLabel.setForeground(Color.RED);
            }
            else if(this.element == 4){
                numberLabel.setForeground(Color.BLUE);
            }
            else if(this.element == 5){
                numberLabel.setForeground(Color.MAGENTA);
            }
            this.setBackground(Color.GRAY);
            this.setEnabled(false);
            numberLabel.setEnabled(true);
        }
        return 0;
    }
    public String toString(){
        return "Number";
    }
}
