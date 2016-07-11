package ScreenController;

import Buttons.*;
import GameController.GameControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        Toolkit tk =  Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(true);
        menu = new JMenu("A Menu");
        menuBar.add(menu);
        mainFrame = new JFrame("Main");
        mainFrame.setSize(x * 45 + 25,200 + y*45);
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
        infoConstraint.anchor = GridBagConstraints.PAGE_START;
        controlContainer.add(infoPanel,infoConstraint);
        infoPanel.setVisible(true);

        gamePanel = new JPanel();
        gameLayout = new GridBagLayout();
        gameConstraint = new GridBagConstraints();
        gamePanel.setLayout(gameLayout);
        gameConstraint.gridx = 0;
        gameConstraint.gridy = 1;
        gameConstraint.anchor = GridBagConstraints.CENTER;
        gameConstraint.fill = GridBagConstraints.BOTH;
        controlContainer.add(gamePanel,gameConstraint);
        makeGrid(x,y);
        mainFrame.setVisible(true);
    }

    public void makeGrid(int x, int y){
        gridContraints = new GridBagConstraints();
        gridContraints.anchor = GridBagConstraints.FIRST_LINE_START;
        buttons = new Cell[x][y];
        for(int i = 0; i < y; i++){
            for (int j = 0; j < x; j++){
                int[] pos = new int[2];
                pos[0] = j;
                pos[1] = i;
                buttons[j][i] = new Cell(pos);
                gridContraints.gridx = j;
                gridContraints.gridy = i;
                buttons[j][i].addActionListener(new FirstButtonClickListener());
                gamePanel.add(buttons[j][i],gridContraints);
            }
        }
    }
    public void makeGrid(Cell[][] actualGrid){
        gamePanel.removeAll();
        gamePanel.setVisible(false);
        for(int i = 0; i < actualGrid[i].length; i++){
            for (int j = 0; j < actualGrid.length; j++){
                buttons[j][i] = actualGrid[j][i];
                gridContraints.gridx = j;
                gridContraints.gridy = i;
                gamePanel.add(buttons[j][i],gridContraints);
            }
            //The reason I have to break is because there is an unknown bug that does not stop i
            //Feel free to remove the break and start breaking the game
            if(i == 8){
                break;
            }
        }
        controlContainer.add(gamePanel,gameConstraint);
        gamePanel.setVisible(true);
    }

    private class FirstButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(!GameControl.firstClick){
                Cell firstClickCell = (Cell) e.getSource();
                int[] pos = ((Cell) e.getSource()).getPos();
                System.out.println("Set up new board");
                GC.setUpBoard(firstClickCell.getPos());
                System.out.println("Done");
                System.out.println("Put in the UI");
                makeGrid(GC.getBoard());
                System.out.println("Done");
                GameControl.firstClick = true;
                System.out.println(GC.boardString());
                System.out.println("Revealing the opening moves");
                System.out.println("Getting area to reveal");
                Set<int[]> someArea = GC.getAreaToReveal(pos, new HashSet<>());
                someArea.add(pos);
                System.out.println("Done");
                for (int[] i: someArea){
                    System.out.println(Arrays.toString(i));
                    GC.revealedArea.add(i);
                    buttons[i[0]][i[1]].reveal();
                }
                System.out.println("Done");

            }
        }
    }
}
