import GameController.GameControl;
import GameController.GameStatus;
import ScreenController.Screen;

/**
 * Created by wit on 7/5/2016.
 */
public class Main {
    public static void main(String[] args){
        Screen someFrame = new Screen();
        GameStatus someStatus = new GameStatus();
        GameControl gameControl = new GameControl();

        someStatus.changeLevel(3);
        someFrame.makeScreen(someStatus.getGridX(), someStatus.getGridY());
    }

}
