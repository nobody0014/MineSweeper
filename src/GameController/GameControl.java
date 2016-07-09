package GameController;
import Buttons.*;
import Buttons.Number;

import java.util.*;

/**
 * Created by wit on 7/7/2016.
 */
public class GameControl {
    public static boolean firstClick = false;
    Cell[][] board;
    ArrayList<Integer[]> bombPos;
    HashSet<Integer[]> positionFilled;
    private int x;
    private int y;
    private int noBombs;
    public GameControl(){
        x = 30;
        y = 16;
        noBombs = 99;
        board = new Cell[x][y];
        bombPos = new ArrayList<>();
        positionFilled = new HashSet<>();
    }
    //Get Height
    public int getGridY(){
        return y;
    }
    //Get Width
    public int  getGridX(){
        return x;
    }

    public int getNumberOfBombs(){
        return noBombs;
    }

    public void changeLevel(int lvl){
        //0 is the Height
        //1 is the Width
        //2 is the number of bombs
        if(lvl == 1){
            x = 9;
            y = 9;
            noBombs = 10;
        }
        else if(lvl == 2){
            x = 16;
            y = 16;
            noBombs = 40;
        }
        else if(lvl == 3){
            x = 30;
            y = 16;
            noBombs = 99;
        }
    }
    public void changeLevel(int x, int y, int bombs){
        this.x = x;
        this.y = y;
        noBombs = bombs;
    }

    public void setUpBoard(){
        if(!firstClick){
            System.out.println("Put in bombs");
            setBombs();
            System.out.println("Complete");
            System.out.println("Put in Numbers and Empties");
            setNumber();
            System.out.println("Complete");
        }
        else{
            System.out.println("Game has already started");
        }

    }
    private void setBombs(){
        //create a Random object
        Random rand = new Random();
        //An Integer[] Object to keep the coordinates
        Integer[] coor;
        int posX;
        int posY;
        while (bombPos.size() < noBombs){
            //Since we are using objects, we have to create new objects for every coordinates
            coor = new Integer[2];
            posX = rand.nextInt(x-1);
            posY = rand.nextInt(y-1);
            coor[0] = posX;
            coor[1] = posY;
            if(!bombPos.contains(coor)){
                bombPos.add(coor);
                board[posX][posY] = new Bomb(coor);
                positionFilled.add(coor);
            }
        }
    }
    //Use this after obtaining the bombs coordinates
    private void setNumber(){
        HashMap<Integer[], Integer> numPos = new HashMap<>();
        // This for loop will get all the possible numbers
        Integer[] t;
        Integer[] tR;
        Integer[] tL;
        Integer[] l;
        Integer[] r;
        Integer[] b ;
        Integer[] bR;
        Integer[] bL;
        int posX;
        int posY;
        for(Integer[] i: bombPos){
            //Due to the structure of how objects work, we have to crete new one everytime
            //Otherwise we would be changing the value of the same object over and over again
            t = new Integer[2];
            tR = new Integer[2];
            tL = new Integer[2];
            l = new Integer[2];
            r = new Integer[2];
            b = new Integer[2];
            bR = new Integer[2];
            bL = new Integer[2];

            //We use the int so that we would not mess with the actual object in bombPos
            posX = i[0];
            posY = i[1];
            t[0] = posX;
            t[1] = posY - 1;
            tR[0] = posX + 1;
            tR[1] = posY - 1;
            tL[0] = posX - 1;
            tL[1] = posY - 1;
            l[0] = posX - 1;
            l[1] = posY;
            r[0] = posX + 1;
            r[1] = posY;
            b[0] = posX;
            b[1] = posY + 1;
            bR[0] = posX + 1;
            bR[1] = posY + 1;
            bL[0] = posX - 1;
            bL[1] = posY + 1;
            //System.out.println(Arrays.toString());
            if (!(bL[0] < 0 || bL[1] > y)){
                if(numPos.containsKey(bL)){numPos.put(bL,numPos.get(bL));}
                else{numPos.put(bL,1);}
            }
            if (!(bR[0] > x || bR[1] > y)){
                if(numPos.containsKey(bR)){numPos.put(bR,numPos.get(bR));}
                else{numPos.put(bR,1);}
            }
            if (!(b[1] > y)){
                if(numPos.containsKey(b)){numPos.put(b,numPos.get(b));}
                else{numPos.put(b,1);}
            }
            if (!(r[0] > x)){
                if(numPos.containsKey(r)){numPos.put(r,numPos.get(r));}
                else{numPos.put(r,1);}
            }
            if (!(l[0] < 0)){
                if(numPos.containsKey(l)){numPos.put(l,numPos.get(l));}
                else{numPos.put(l,1);}
            }
            if (!(t[1] < 0)){
                if(numPos.containsKey(t)){numPos.put(t,numPos.get(t));}
                else{numPos.put(t,1);}
            }
            if (!(tL[0] < 0 || tL[1] < 0 )){
                if(numPos.containsKey(tL)){numPos.put(tL,numPos.get(tL));}
                else{numPos.put(tL,1);}
            }
            if (!(tR[0] > x || tR[1] < 0)){
                if(numPos.containsKey(tR)){numPos.put(tR,numPos.get(tR));}
                else{numPos.put(tR,1);}
            }
        }
        for (Integer[] i : numPos.keySet()){
            positionFilled.add(i);
            if(board[i[0]][i[1]]  == null){
                board[i[0]][i[1]] = new Number(i,numPos.get(i));
            }

        }
        Integer[] coor = new Integer[2];
        for(int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                if(board[i][j] == null){
                    coor[0] = i;
                    coor[1] = j;
                    board[i][j] = new Empty(coor,0);
                }
            }
        }
    }


    //Getter methods start here
    //Get the board back but not the string part.
    public Cell[][] getBoard(){
        return board;
    }
    //Get the state of the board.
    public String boardString(){
        String boardString = "[";
        for (int i = 0; i < x; i++){
            boardString += "[";
            for (int j = 0; j < y; j++){
                if(board[i][j] != null){
                    boardString += board[i][j].toString();
                }
                else{
                    boardString += "null";
                }
                if(j != x-1){
                    boardString += ",";
                }
            }
            boardString += "]";
            if (i != x-1){
                boardString += "\n";
            }
        }
        boardString += "]";

        return boardString;
    }
}
