package ScreenController;

import Buttons.*;
import GameController.GameControl;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private JButton newGameB;
    private JTextField markersNo;
    private JTextField timeField;
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
        if(x*45 > 450){
            mainFrame.setSize(x * 45 + 25, 150 + y*45);
        }
        else{
            mainFrame.setSize(450, 150 + y*45);
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
        infoConstraint = new GridBagConstraints();
        infoConstraint.gridy = 0;
        infoConstraint.fill = GridBagConstraints.HORIZONTAL;
        infoConstraint.anchor = GridBagConstraints.PAGE_START;
        controlContainer.add(infoPanel,infoConstraint);

        GridBagConstraints innerInfor = new GridBagConstraints();

        markersNo = new JTextField();
        markersNo.setPreferredSize(new Dimension(80,30));
        markersNo.setLayout(new GridBagLayout());
        markersNo.setText("Markers = " + GC.getNoMarkersAvail());
        innerInfor.gridx = 0;
        infoPanel.add(markersNo,innerInfor);

        newGameB = new JButton();
        newGameB.addActionListener(new NewGameListener());
        newGameB.setPreferredSize(new Dimension(50,50));
        newGameB.setText("NG");
        newGameB.setLayout(new GridBagLayout());
        innerInfor.gridx = 1;
        infoPanel.add(newGameB,innerInfor);

        timeField = new JTextField();
        timeField.setPreferredSize(new Dimension(80,30));
        timeField.setLayout(new GridBagLayout());
        timeField.setText("Time = stuff");
        innerInfor.gridx = 2;
        infoPanel.add(timeField,innerInfor);

        infoPanel.setVisible(true);

        gamePanel = new JPanel();
        gameLayout = new GridBagLayout();
        gameConstraint = new GridBagConstraints();
        gamePanel.setLayout(gameLayout);
        gameConstraint.gridy = 1;
        gameConstraint.anchor = GridBagConstraints.CENTER;
        gameConstraint.fill = GridBagConstraints.BOTH;
        controlContainer.add(gamePanel,gameConstraint);
        makeGrid(x,y);
        markersNo.setText("Markers = " + GC.getNoMarkersAvail());
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
        for(int i = 0; i < GC.getGridY(); i++){
            for (int j = 0; j < GC.getGridX(); j++){
                buttons[j][i] = actualGrid[j][i];
                if(buttons[j][i] instanceof Empty){
                    buttons[j][i].addMouseListener(new GameControlEmptyListener());
                }
                buttons[j][i].addMouseListener(new GameControlMarkListener());
                gridContraints.gridx = j;
                gridContraints.gridy = i;
                gamePanel.add(buttons[j][i],gridContraints);
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
    private class NewGameListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(GameControl.firstClick){
                GameControl.firstClick = false;
                System.out.println("Resetting.....  ");
                GC = new GameControl();
                gamePanel.removeAll();
                gamePanel.setVisible(false);
                makeGrid(GC.getGridX(),GC.getGridY());
                controlContainer.add(gamePanel,gameConstraint);
                gamePanel.setVisible(true);
                System.out.println("Done");
                markersNo.setText("Markers = " + GC.getNoMarkersAvail());
            }

        }
    }
    private class GameControlMarkListener implements MouseListener {
        public void mouseClicked(MouseEvent arg0){
            if(SwingUtilities.isRightMouseButton(arg0)){
                Cell c = (Cell) arg0.getSource();
                int result = c.reveal("right");
                if(result == 1){
                    GC.addNoBombsMarked();
                    GC.minusNoMarkersAvail();
                }
                else if(result == -1){
                    GC.minusNoBombsMarked();
                    GC.addNoMarkersAvail();
                }
                markersNo.setText("Markers = " + GC.getNoMarkersAvail());
            }
        }
        public void mouseEntered(MouseEvent arg0){}
        public void mouseReleased(MouseEvent arg0) {}
        public void mouseExited(MouseEvent arg0){}
        public void mousePressed(MouseEvent arg0){}
    }



    private class GameControlEmptyListener implements MouseListener{
        public void mouseClicked(MouseEvent arg0){
            if(SwingUtilities.isLeftMouseButton(arg0)){
                Cell c = (Empty) arg0.getSource();
                int[] pos = new int[2];
                pos[0] = c.getPos()[0];
                pos[1] = c.getPos()[1];
                Set<int[]> someArea = GC.getAreaToReveal(pos, new HashSet<>());
                someArea.add(pos);
                for (int[] i: someArea){
                    System.out.println(Arrays.toString(i));
                    GC.revealedArea.add(i);
                    buttons[i[0]][i[1]].reveal();
                }
            }
        }
        public void mouseEntered(MouseEvent arg0){}
        public void mouseReleased(MouseEvent arg0) {}
        public void mouseExited(MouseEvent arg0){}
        public void mousePressed(MouseEvent arg0){}
    }
}
