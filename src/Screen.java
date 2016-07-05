import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by wit on 7/5/2016.
 */
public class Screen {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;



    public void makeScreen(int x, int y){
        Toolkit tk =  Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();



        mainFrame = new JFrame("Main");
        //Add constant to Y dimension to give area for restart, timer, and number of bombs
        mainFrame.setSize(x * 40, y * 40 + 50);
        //Get X and Y locations where you can start the screen in the middle
        int midX =  dim.width/2 - mainFrame.getWidth()/2;
        int midY = dim.height/2 - mainFrame.getHeight()/2;
        mainFrame.setLocation(midX,midY);
        mainFrame.setLayout(new GridLayout(3,3));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(true);
        headerLabel = new JLabel("",JLabel.CENTER );
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void demo(){
        headerLabel.setText("");
    }
}
