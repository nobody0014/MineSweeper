import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by wit on 7/5/2016.
 */
public class Screen {
    private JFrame mainFrame;
    public void makeScreen(){
        mainFrame = new JFrame("Main");
        mainFrame.setSize(500, 500);
        mainFrame.setLayout(new GridLayout(3,3));

        mainFrame.setVisible(true);


    }
}
