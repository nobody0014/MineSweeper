package GameController;
import Buttons.*;
import Buttons.Number;

import java.util.*;

/**
 * Created by wit on 7/7/2016.
 */
public class GameControl {
    Cell[][] board;
    ArrayList<Integer[]> bombPos = new ArrayList<>();
    HashSet<Integer[]> positionFilled = new HashSet<>();
    int x;
    int y;
    public GameControl(int boardSizeX, int boardSizeY){
        x = boardSizeX;
        y = boardSizeY;
        board = new Cell[x][y];
    }
    public void setBombs(int noBombs){
        Random rand = new Random();
        Integer[] coor;
        int posX;
        int posY;
        while (bombPos.size() < noBombs){
            //Make new object cus otherwise the program would detect the same old object
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
    public void setNumber(){
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
            board[i[0]][i[1]] = new Number(i,numPos.get(i));
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
    public Cell[][] getBoard(){
        return board;
    }
    public String boardString(){
        String boardString = "[";
        for (int i = 0; i < x; i++){
            boardString += "[";
            for (int j = 0; j < y; j++){
                boardString += board[i][j].toString();
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
