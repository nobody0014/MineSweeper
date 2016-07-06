import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wit on 7/5/2016.
 */
public class Screen {

    private JFrame mainFrame;
    private Container controlContainer;
    private JPanel gamePanel;
    private JPanel infoPanel;
    private GridBagLayout controlLayout;
    private GridBagLayout gameLayout;
    private GridBagConstraints gridContraints;
    private GridBagConstraints infoConstraint;
    private GridBagConstraints gameConstraint;
    private JMenuBar menuBar;
    private JMenu menu;
    private Cell buttons[];



    public void makeScreen(int x, int y){
        Toolkit tk =  Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(true);
        menu = new JMenu("A Menu");
        menuBar.add(menu);
        mainFrame = new JFrame("Main");

        //Add constant to Y dimension to give area for restart, timer, and number of bombs
        //mainFrame.setSize(x * 40 + 100, y * 40 + 100);
        mainFrame.setSize(200 + x * 40 ,600);
        //Get X and Y locations where you can start the screen in the middle
        int midX =  dim.width/2 - mainFrame.getWidth()/2;
        int midY = dim.height/2 - mainFrame.getHeight()/2;
        mainFrame.setJMenuBar(menuBar);

        //
        mainFrame.setLocation(midX,midY);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        //Make Information Area

        //Make proper grid Panel according to the size of the game
        makeGrid(x,y);


        controlContainer = mainFrame.getContentPane();
        controlContainer.setSize(mainFrame.getWidth(),mainFrame.getHeight());
        controlLayout = new GridBagLayout();
        controlContainer.setLayout(controlLayout);

        infoPanel = new JPanel();
        infoPanel.add(new JLabel("h"));
        infoConstraint = new GridBagConstraints();
        infoConstraint.gridx = 0;
        infoConstraint.gridy = 0;
        infoConstraint.ipadx = 100;
        infoConstraint.ipady = 600;
        infoConstraint.anchor = GridBagConstraints.FIRST_LINE_START;
        controlContainer.add(infoPanel,infoConstraint);

        gameConstraint = new GridBagConstraints();
        gameConstraint.fill = GridBagConstraints.BOTH;
        gameConstraint.gridx = 1;
        gameConstraint.gridy = 0;
        gameConstraint.ipadx = x*40 + 100;
        gameConstraint.ipady = 600;
        gameConstraint.anchor = GridBagConstraints.FIRST_LINE_END;
        controlContainer.add(gamePanel);


        mainFrame.setVisible(true);

    }


    public void makeGrid(int x, int y){
        gamePanel = new JPanel();
        gameLayout = new GridBagLayout();
        gridContraints = new GridBagConstraints();
        gamePanel.setLayout(gameLayout);
        buttons = new Cell[x*y];
        int k = 0;
        for(int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                buttons[k] = new Cell();
                gridContraints.fill = GridBagConstraints.BOTH;
                gridContraints.gridy = i;
                gridContraints.gridx = j;
                gamePanel.add(buttons[k],gridContraints);
                k++;
            }
        }

    }

}
