import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by wit on 7/5/2016.
 */
public class Screen {

    private JFrame mainFrame;
    private Container controlContainer;
    private JPanel gridPanel;
    private JPanel infoPanel;
    private JMenuBar menuBar;
    private JMenu menu;
    private Cell buttons[];
    private final int space = 3;



    public void makeScreen(int x, int y){
        Toolkit tk =  Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(true);
        menu = new JMenu("A Menu");
        menuBar.add(menu);
        mainFrame = new JFrame("Main");

        //Add constant to Y dimension to give area for restart, timer, and number of bombs
        mainFrame.setSize(x * 40, y * 40 + 100);

        //Get X and Y locations where you can start the screen in the middle
        int midX =  dim.width/2 - mainFrame.getWidth()/2;
        int midY = dim.height/2 - mainFrame.getHeight()/2;
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setLocation(midX,midY);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        //Make Information Area
        infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        infoPanel.setSize(x*40, 100);

        //Make proper grid Panel according to the size of the game
        makeGrid(x,y);


        controlContainer = new Container();
        controlContainer.setLayout(new GridLayout(2,1));
        controlContainer.add(infoPanel);
        controlContainer.add(gridPanel);
        mainFrame.add(controlContainer);
        mainFrame.setVisible(true);
    }
    public void makeGrid(int x, int y){
        int gridSize;
        if(x > y){
            gridSize = y;
        }
        else{
            gridSize = x;
        }
        buttons = new Cell[x*y];
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gridSize,gridSize));
        for(int i = 0; i < x*y; i++){
            buttons[i] =  new Cell();
            gridPanel.add(buttons[i]);
        }
    }

}
