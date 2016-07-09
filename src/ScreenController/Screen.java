package ScreenController;

import Buttons.*;
import GameController.GameControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private Cell buttons[][];
    private GameControl GC;

    public Screen(){
        GC = new GameControl();
    }
    public void makeScreen(){
        int x = GC.getGridX();
        int y = GC.getGridY();
        int HeightUsed;
        Toolkit tk =  Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(true);
        menu = new JMenu("A Menu");
        menuBar.add(menu);
        mainFrame = new JFrame("Main");
        if(y * 40 > 600){
            HeightUsed = y*40;
            mainFrame.setSize(100 + x * 30 ,y*30);
        }
        else{
            HeightUsed = 600;
            mainFrame.setSize(100 + x * 30 ,600);
        }
        //Add constant to Y dimension to give area for restart, timer, and number of bombs

        //Get X and Y locations where you can start the screen in the middle
        int midX =  dim.width/2 - mainFrame.getWidth()/2;
        int midY = dim.height/2 - mainFrame.getHeight()/2;
        mainFrame.setJMenuBar(menuBar);

        //Set some screen properties
        mainFrame.setLocation(midX,midY);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        controlContainer = mainFrame.getContentPane();
        controlContainer.setSize(mainFrame.getWidth(),mainFrame.getHeight());
        controlLayout = new GridBagLayout();
        controlContainer.setLayout(controlLayout);

        infoPanel = new JPanel();
        infoPanel.add(new JLabel("h"));
        infoConstraint = new GridBagConstraints();
        infoConstraint.gridx = 0;
        infoConstraint.gridy = 0;
        infoConstraint.anchor = GridBagConstraints.FIRST_LINE_START;
        controlContainer.add(infoPanel,infoConstraint);
        infoPanel.setVisible(true);

        gamePanel = new JPanel();
        gameLayout = new GridBagLayout();
        gameConstraint = new GridBagConstraints();
        gamePanel.setLayout(gameLayout);
        gameConstraint.gridx = 1;
        gameConstraint.gridy = 0;
        gameConstraint.anchor = GridBagConstraints.FIRST_LINE_END;
        gameConstraint.fill = GridBagConstraints.BOTH;
        controlContainer.add(gamePanel,gameConstraint);
        makeGrid(x,y);
        mainFrame.setVisible(true);
    }

    public void makeGrid(int x, int y){
        gridContraints = new GridBagConstraints();
        gridContraints.anchor = GridBagConstraints.FIRST_LINE_START;
        buttons = new Cell[x][y];
        Integer[] pos = new Integer[2];
        for(int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                pos[0] = i;
                pos[1] = j;
                buttons[i][j] = new Cell(pos);
                gridContraints.gridx = i;
                gridContraints.gridy = j;
                buttons[i][j].addActionListener(new FirstButtonClickListener());
                gamePanel.add(buttons[i][j],gridContraints);
            }
        }
    }
    public void makeGrid(Cell[][] actualGrid){
        gamePanel.removeAll();
        gamePanel.setVisible(false);
        for(int i = 0; i < actualGrid.length; i++){
            for (int j = 0; j < actualGrid[i].length; j++){
                buttons[i][j] = actualGrid[i][j];
                gridContraints.gridx = i;
                gridContraints.gridy = j;
                gamePanel.add(buttons[i][j],gridContraints);
            }
        }
        controlContainer.add(gamePanel,gameConstraint);
        gamePanel.setVisible(true);
    }
    private class FirstButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(!GameControl.firstClick){
                GC.setUpBoard();
                makeGrid(GC.getBoard());
                GameControl.firstClick = true;
            }
        }
    }

}
