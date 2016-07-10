package GameController;
import Buttons.*;
import Buttons.Number;

import java.util.*;

/**
 * Created by wit on 7/7/2016.
 */
public class GameControl {
    public static boolean firstClick = false;
    private Cell[][] board;
    private HashSet<Integer[]> bombPos;
    private HashSet<Integer[]> noBombArea;
    private HashSet<Integer[]> positionFilled;
    private int x;
    private int y;
    private int noBombs;

    public GameControl(){
        x = 10;
        y = 5;
        noBombs = 10;
        board = new Cell[x][y];
        bombPos = new HashSet<>();
        positionFilled = new HashSet<>();
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

    public void setUpBoard(Integer[] firstClickPos){
        if(!firstClick){
            System.out.println("Setting up no bombs area");
            setNoBombArea(firstClickPos);
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
    private void setNoBombArea(Integer[] pos){
        noBombArea = new HashSet<>();
        // I do not want any bomb to be beside the point where the user start clicking because it might result in
        // first click death, so i create an area to ward that off
        noBombArea.add(pos);
        Integer[] t = new Integer[2];
        Integer[] tR = new Integer[2];
        Integer[] tL = new Integer[2];
        Integer[] l = new Integer[2];
        Integer[] r = new Integer[2];
        Integer[] b = new Integer[2];
        Integer[] bR = new Integer[2];
        Integer[] bL = new Integer[2];
        int posX = pos[0];
        int posY = pos[1];
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
        noBombArea.add(bL);
        noBombArea.add(bR);
        noBombArea.add(b);
        noBombArea.add(r);
        noBombArea.add(l);
        noBombArea.add(t);
        noBombArea.add(tL);
        noBombArea.add(tR);
    }
    private void setBombs(){
        //create a Random object
        Random rand = new Random();
        int posX;
        int posY;
        for(Integer[] i : noBombArea){
            System.out.println(Arrays.toString(i));
        }
        while (bombPos.size() < noBombs){
            //Since we are using objects, we have to create new objects for every coordinates
            Integer[] coor = new Integer[2];
            posX = rand.nextInt(x-1);
            posY = rand.nextInt(y-1);
            coor[0] = posX;
            coor[1] = posY;
            contains(bombPos,coor);
            if(!contains(bombPos,coor) && !contains(noBombArea,coor)){
                bombPos.add(coor);
                board[posX][posY] = new Bomb(coor);
                positionFilled.add(coor);
            }
        }
        for(Integer[] i : bombPos){
            System.out.println(Arrays.toString(i));
        }
        System.out.println(boardString());
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

        for(int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                if(board[i][j] == null){
                    Integer[] coor = new Integer[2];
                    coor[0] = i;
                    coor[1] = j;
                    board[i][j] = new Empty(coor,0);
                }
            }
        }
    }

    private boolean contains(HashSet<Integer[]> aSet, Integer[] i){
        boolean checker = false;
        for(Integer[] someArray: aSet){
            checker = evaluate(someArray,i);
            if(checker){
                break;
            }
        }
        return checker;
    }
    //Evaluating Coordinate if they are the same or not (true if same) (false if not same)
    private boolean evaluate(Integer[] i, Integer[] j){
        if(i[0].equals(j[0]) && i[1].equals(j[1])){
            return true;
        }
        return false;
    }

    //Getter methods start here
    //Get the board back but not the string part.
    public Cell[][] getBoard(){
        return board;
    }
    //Get Height
    public int getGridY(){
        return y;
    }
    //Get Width
    public int  getGridX(){
        return x;
    }
    //Get NumberOfBombs
    public int getNumberOfBombs(){
        return noBombs;
    }
    //Get the state of the board (in string form).
    public String boardString(){
        String boardString = "[";
        for (int i = 0; i < y; i++){
            boardString += "[";
            for (int j = 0; j < x; j++){
                if(board[j][i] != null){
                    boardString += board[j][i].toString();
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
