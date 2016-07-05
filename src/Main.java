import java.util.ArrayList;

/**
 * Created by wit on 7/5/2016.
 */
public class Main {
    public static void main(String[] args){
        Screen someFrame = new Screen();
        GameStatus someStatus = new GameStatus();
        someFrame.makeScreen(someStatus.getGridX(), someStatus.getGridY());
    }

}
