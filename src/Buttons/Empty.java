package Buttons;

/**
 * Created by wit on 7/6/2016.
 */
public class Empty extends Number {
    EmptyListener emptListen;
    public Empty(Integer[] i,int noBombs){
        super(i,noBombs);
        emptListen = new EmptyListener();
        addActionListener(emptListen);
    }
    public void changeLabel(){
        label.setText("Empty");
    }
    public String toString(){
        return "Empty";
    }
}
