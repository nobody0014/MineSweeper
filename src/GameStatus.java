import java.util.ArrayList;

/**
 * Created by wit on 7/5/2016.
 */
public class GameStatus {
    private ArrayList<Integer> gameState;
    public GameStatus(){
        gameState = new ArrayList<>();
        gameState.add(16);
        gameState.add(30);
        gameState.add(99);
    }
    //Get Height
    public int getGridY(){
        return gameState.get(0);
    }
    //Get Width
    public int  getGridX(){
        return gameState.get(1);
    }
    public int getNumberOfBombs(){
        return gameState.get(2);
    }
    public void changeLevel(int lvl){
        //0 is the Height
        //1 is the Width
        //2 is the number of bombs
        if(lvl == 1){
            gameState.add(9);
            gameState.add(9);
            gameState.add(10);
        }
        else if(lvl == 2){
            gameState.add(16);
            gameState.add(16);
            gameState.add(40);
        }
        else if(lvl == 3){
            gameState.add(16);
            gameState.add(30);
            gameState.add(99);
        }
    }
}
