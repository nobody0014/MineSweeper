package GameController;
import Buttons.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by wit on 7/7/2016.
 */
public class GameControl {
    Cell[] board;
    ArrayList<Integer> bombPos;
    HashSet<Integer> positionFilled;
    int x;
    int y;
    public GameControl(int boardSizeX, int boardSizeY){
        x = boardSizeX;
        y = boardSizeY;
        board = new Cell[x*y];
    }
    public void setBombs(int noBombs){
        bombPos = new ArrayList<>();
        Random rand = new Random();
        while (bombPos.size() < noBombs){
            int pos = rand.nextInt(bombPos.size());
            if(!bombPos.contains(pos)){
                bombPos.add(pos);
                board[pos] = new Bomb(pos);
                positionFilled.add(pos);
            }
        }
    }
    public boolean checkValidPos(int pos,String o){
        if(o.equals("tR") || o.equals("tL")){
            if((pos+1)%x == 0 || pos < 0) return false;
            else return true;
        }
        else if(o.equals("t")){
            if (pos < 0) return false;
            else return true;
        }
        else if (o.equals("l") || o.equals("r")){
            if (pos+1 % x == 0) return false;
            else return true;
        }
        else if (o.equals("b")){
            if (pos+x > x*y) return false;
            else return true;
        }
        else if (o.equals("bR")){
            if (pos%x == 0) return false;
            else return true;
        }
        else if (o.equals("bl")){
            if ((pos+1)%x == 0) return false;
            else return true;
        }
        return true;
    }
    public void setNumber(){
        HashMap<Integer, Integer> numPos = new HashMap<>();
        // This for loop will get all the possible numbers
        for(Integer i: bombPos){
            int t = i-x;
            int tR = i-x+1;
            int tL = i-x-1;
            int l = i-1;
            int r = i+1;
            int b = i + x;
            int bR = b+1;
            int bL = b-1;
            if(checkValidPos(t,"t")){
                if(numPos.containsKey(t)){numPos.put(t,numPos.get(t)+1);}
                else{numPos.put(t,1);}
            }
            if(checkValidPos(tR,"tR")){
                if(numPos.containsKey(tR)){numPos.put(tR,numPos.get(tR)+1);}
                else{numPos.put(tR,1);}
            }
            if(checkValidPos(tL,"t")){
                if(numPos.containsKey(tL)){numPos.put(tL,numPos.get(tL)+1);}
                else{numPos.put(tL,1);}
            }
            if(checkValidPos(l,"t")){
                if(numPos.containsKey(l)){numPos.put(l,numPos.get(l)+1);}
                else{numPos.put(l,1);}
            }
            if(checkValidPos(r,"t")){
                if(numPos.containsKey(r)){numPos.put(r,numPos.get(r)+1);}
                else{numPos.put(r,1);}
            }
            if(checkValidPos(b,"t")){
                if(numPos.containsKey(b)){numPos.put(b,numPos.get(b)+1);}
                else{numPos.put(b,1);}
            }
            if(checkValidPos(bR,"t")){
                if(numPos.containsKey(bR)){numPos.put(bR,numPos.get(bR)+1);}
                else{numPos.put(bR,1);}
            }
            if(checkValidPos(bL,"t")){
                if(numPos.containsKey(bL)){numPos.put(bL,numPos.get(bL)+1);}
                else{numPos.put(bL,1);}
            }
        }

        for (Integer i : numPos.keySet()){
            positionFilled.add(i);
        }

        


    }



}
