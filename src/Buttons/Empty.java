package Buttons;

/**
 * Created by wit on 7/6/2016.
 */
public class Empty extends Number {
    EmptyListener emptListen;
    public Empty(int[] i,int noBombs){
        super(i,noBombs);
        emptListen = new EmptyListener();
        addActionListener(emptListen);
    }
    public void reveal(){
        if(!isLeftClicked){
            this.setText("E");
            isLeftClicked = true;
        }
    }
    public String toString(){
        return "Empty";
    }
}
