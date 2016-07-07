import GameController.GameControl;
import GameController.GameStatus;
import ScreenController.Screen;

import java.util.Arrays;

/**
 * Created by wit on 7/5/2016.
 */
public class Main {
    public static void main(String[] args){
//        Screen someFrame = new Screen();
        System.out.println("Making Game Status");
        GameStatus someStatus = new GameStatus();
        System.out.println("Done Making Game Status");
        System.out.println("Changing Game Level to: 1");
        someStatus.changeLevel(1);
        System.out.println("Complete");
        System.out.println("Make Game Control");
        GameControl gameControl = new GameControl(someStatus.getGridX(),someStatus.getGridY());
        System.out.println("Complete");
        System.out.println("Set Bombs");
        gameControl.setBombs(someStatus.getNumberOfBombs());
        System.out.println("Complete");
        System.out.println("Set Numbers");
        gameControl.setNumber();
        System.out.println("Complete");
        System.out.println(gameControl.boardString());
//        someStatus.changeLevel(3);
//        someFrame.makeScreen(someStatus.getGridX(), someStatus.getGridY());
    }
}
