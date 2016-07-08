package GameController;

import java.util.ArrayList;

/**
 * Created by wit on 7/5/2016.
 */
public class GameStatus {
    public static boolean firstClick = false;
    private int[] gameState;
    private int time;

    public GameStatus(){
        gameState = new int[3];
        gameState[0] = 16;
        gameState[1] = 30;
        gameState[2] = 99;
    }
    //Get Height
    public int getGridY(){
        return gameState[0];
    }
    //Get Width
    public int  getGridX(){
        return gameState[1];
    }

    public int getNumberOfBombs(){
        return gameState[2];
    }

    public void changeLevel(int lvl){
        //0 is the Height
        //1 is the Width
        //2 is the number of bombs
        if(lvl == 1){
            gameState[0] = 9;
            gameState[1] = 9;
            gameState[2] = 10;
        }
        else if(lvl == 2){
            gameState[0] = 16;
            gameState[1] = 16;
            gameState[2] = 40;
        }
        else if(lvl == 3){
            gameState[0] = 16;
            gameState[1] = 30;
            gameState[2] = 99;
        }
    }
    public void changeLevel(int x, int y, int bombs){
        gameState[0] = y;
        gameState[1] = x;
        gameState[2] = bombs;
    }
}
