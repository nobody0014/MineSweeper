package ScreenController;

import Buttons.*;
import GameController.GameControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wit on 7/5/2016.
 */
public class Screen {
    private JFrame mainFrame;
    private TimeThread timeThread;
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
    private JMenuItem changeLevel1;
    private JMenuItem changeLevel2;
    private JMenuItem changeLevel3;
    private Toolkit tk;
    private Dimension dim;



    public Screen(){
        GC = new GameControl();
        timeThread = new TimeThread("Clock");
    }
    public void makeScreen(){
        int x = GC.getGridX();
        int y = GC.getGridY();
        tk =  Toolkit.getDefaultToolkit();
        dim = tk.getScreenSize();

        mainFrame = new JFrame("Main");


        //Call resize frame to set size for you
        resizeFrame();

        //Set up menuBar and add its item
        setUpMenu();
        mainFrame.setJMenuBar(menuBar);


        //Set some screen properties
        centerTheFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);


        controlContainer = mainFrame.getContentPane();
        controlContainer.setSize(mainFrame.getWidth(),mainFrame.getHeight());
        controlLayout = new GridBagLayout();
        controlContainer.setLayout(controlLayout);

        //Creating info panel to keep all the stuff on top of the frame
        infoPanel = new JPanel();
        infoConstraint = new GridBagConstraints();
        infoConstraint.gridy = 0;
        infoConstraint.fill = GridBagConstraints.HORIZONTAL;
        infoConstraint.anchor = GridBagConstraints.PAGE_START;
        controlContainer.add(infoPanel,infoConstraint);

        //this constraint is used for all the components in the infopanel
        GridBagConstraints innerInfor = new GridBagConstraints();

        //Creating and setting the field that has the number of markers
        markersNo = new JTextField();
        markersNo.setPreferredSize(new Dimension(80,30));
        markersNo.setLayout(new GridBagLayout());
        markersNo.setText("Markers = " + GC.getNoMarkersAvail());
        innerInfor.gridx = 0;
        infoPanel.add(markersNo,innerInfor);

        //Creating the new game button
        newGameB = new JButton();
        newGameB.addActionListener(new NewGameListener());
        newGameB.setPreferredSize(new Dimension(50,50));
        newGameB.setText("NG");
        newGameB.setLayout(new GridBagLayout());
        innerInfor.gridx = 1;
        infoPanel.add(newGameB,innerInfor);

        //Creating the field to show how much time has elapsed since the game started
        timeField = new JTextField();
        timeField.setPreferredSize(new Dimension(80,30));
        timeField.setLayout(new GridBagLayout());
        timeField.setText("Time = " + 0);
        innerInfor.gridx = 2;
        infoPanel.add(timeField,innerInfor);

        infoPanel.setVisible(true);

        //The actual game area creation
        gamePanel = new JPanel();
        gameLayout = new GridBagLayout();
        gameConstraint = new GridBagConstraints();
        gamePanel.setLayout(gameLayout);
        gameConstraint.gridy = 1;
        gameConstraint.anchor = GridBagConstraints.CENTER;
        gameConstraint.fill = GridBagConstraints.BOTH;
//        gameConstraint.ipady = GC.getGridY()*45;
        controlContainer.add(gamePanel,gameConstraint);

        //Only the game and info panel are needed to be added into controlcontainer

        //Make the actual game grid
        makeGrid(x,y);

        mainFrame.setVisible(true);
    }

    public void setUpMenu(){
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(true);
        menu = new JMenu("Change Level");

        //Changing to level one
        changeLevel1 = new JMenuItem();
        changeLevel1.setText("Level 1");
        changeLevel1.addActionListener(new ChangeLevelListener(1));

        changeLevel2 = new JMenuItem();
        changeLevel2.setText("Level 2");
        changeLevel2.addActionListener(new ChangeLevelListener(2));

        changeLevel3 = new JMenuItem();
        changeLevel3.setText("Level 3");
        changeLevel3.addActionListener(new ChangeLevelListener(3));
        PopupMenu customMenu = new PopupMenu();

        menu.add(changeLevel1);
        menu.add(changeLevel2);
        menu.add(changeLevel3);
//        menu.add(customMenu);
        menuBar.add(menu);
    }

    public void centerTheFrame(){
        //Get mid location according to its current width and height
        int midX =  dim.width/2 - mainFrame.getWidth()/2;
        int midY = dim.height/2 - mainFrame.getHeight()/2;
        //Set some screen properties
        mainFrame.setLocation(midX,midY);
    }

    public void resizeFrame(){
        if(GC.getGridX()*45 > 450){
            mainFrame.setSize(GC.getGridX() * 45 + 25, 150 + GC.getGridY()*45);
        }
        else{
            mainFrame.setSize(450, 150 + GC.getGridY()*45);
        }
    }

    //For changing level from the main
    public void changeLevel(int level){
        GC.changeLevel(level);
    }

    //Just use to make a simple grid
    public void makeGrid(int x, int y){
        gridContraints = new GridBagConstraints();
        gridContraints.anchor = GridBagConstraints.FIRST_LINE_START;
        buttons = new Cell[x][y];
//        gamePanel.p
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
//                gamePanel.paintImmediately(new Rectangle(j,i,40,40));
            }
        }
    }

    //Make grid using the grid from the GC
    //This is used after the first click and the game has started
    public void makeGrid(Cell[][] actualGrid){
        gamePanel.removeAll();
        gamePanel.setVisible(false);
        for(int i = 0; i < GC.getGridY(); i++){
            for (int j = 0; j < GC.getGridX(); j++){
                buttons[j][i] = actualGrid[j][i];
                if(buttons[j][i] instanceof Empty){
                    buttons[j][i].addMouseListener(new GameControlEmptyListener());
                    buttons[j][i].addActionListener(new CellListener());
                }
                else if(buttons[j][i] instanceof Bomb){
                    buttons[j][i].addActionListener(new BombListener());
                }
                else{
                    buttons[j][i].addActionListener(new CellListener());
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


    public void newGame(){
        GameControl.firstClick = false;
        GameControl.gameOver = false;
        System.out.println("Resetting.....  ");
        GC = new GameControl(GC.getGridX(),GC.getGridY(),GC.getNumberOfBombs());
        gamePanel.removeAll();
        gamePanel.setVisible(false);
        makeGrid(GC.getGridX(),GC.getGridY());
        controlContainer.add(gamePanel,gameConstraint);
        gamePanel.setVisible(true);
        System.out.println("Done");
        markersNo.setText("Markers = " + GC.getNoMarkersAvail());
        timeThread.stop();
        timeField.setText("Time = " + 0);
    }

    //Overloaded method for changing level
    public void newGame(int level){
        GC.changeLevel(level);
        newGame();
    }





    //Start of Inner classes (The end is thread)




    //For all cell in the board that is not started yet
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
                timeThread.start();
            }
        }
    }

    //For the new game button, reset the board
    private class NewGameListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(GameControl.firstClick){
                newGame();
            }
        }
    }

    //This listener is given to the most cell except bomb
    private class CellListener implements ActionListener {
        Cell cellClicked;
        public void actionPerformed(ActionEvent e){
            cellClicked = (Cell) e.getSource();
            System.out.println(Arrays.toString(cellClicked.getPos()));
            System.out.println("A Cell");
            cellClicked.reveal();
        }
    }

    //Put this into bomb
    private class BombListener implements  ActionListener{
        Bomb cellClicked;
        public void actionPerformed(ActionEvent e){
            cellClicked = (Bomb) e.getSource();
            System.out.println(Arrays.toString(cellClicked.getPos()));
            System.out.println("A Cell");
            cellClicked.reveal();
            if(GameControl.gameOver){
                timeThread.stop();
                Cell[][] board = GC.getBoard();
                for(int i = 0; i < GC.getGridY(); i++){
                    for (int j = 0; j < GC.getGridX(); j++){
                        //If it is a not bomb and marked, reveal and set background to show that it is wrong
                        if(board[j][i].getIsMarked() && !(board[j][i] instanceof Bomb)){
                            board[j][i].setText("");
                            board[j][i].reveal();
                            board[j][i].setBackground(Color.MAGENTA);

                        }
                        else if(!board[j][i].getIsMarked()){ //reveal everything that is not marked
                            board[j][i].reveal();
                        }
                        //inefficient way of doing things i know, im just too lazy to change stuff
                        board[j][i].setEnabled(false);
                    }
                }
            }
        }
    }
    //Class listener to all buttons after the first click
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


    //Attached this to all empty after the first click
    //This would do the same work as the first click
    private class GameControlEmptyListener implements MouseListener{
        public void mouseClicked(MouseEvent arg0){
            revealArea(arg0);
        }
        public void mouseEntered(MouseEvent arg0){}
        public void mouseReleased(MouseEvent arg0) {
            revealArea(arg0);
        }
        public void mouseExited(MouseEvent arg0){}
        public void mousePressed(MouseEvent arg0){}
        public void revealArea(MouseEvent arg0){
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
    }


    //For the menu
    private class ChangeLevelListener implements ActionListener{
        private int level;
        public ChangeLevelListener(int level){
            this.level = level;
        }
        public void actionPerformed(ActionEvent e){
            if(GC.getLevel() != level){
                newGame(level);
                resizeFrame();
                centerTheFrame();
            }
        }
    }

    //This is the class that create another thread and let it count the time
    private class TimeThread implements Runnable {
        Thread t;
        double timeStart;
        double timePrevious;
        double timeCurrent;
        int timePassed;
        boolean gameStarted;
        String name;

        //Abit of contructor
        public TimeThread(String name){
            gameStarted = false;
            this.name = name;
        }
        //Main running loop
        public void run(){
            timeStart = 0;
            timeCurrent = 0;
            timePassed = 0;
            timePrevious = 0;
            timeStart = System.nanoTime();
            while (gameStarted){
                timeCurrent = System.nanoTime();
                timePassed = (int) ((timeCurrent - timeStart)/1000000000.0);
                timeField.setText("Time = " + timePassed);
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        //Always start the class with start() because it will create a new thread and properly start it
        public void start(){
            toggleGameStarted();
            t = new Thread(this,name);
            t.start();
        }

        //Getter methods
        public int getTime(){
            return timePassed;
        }
        public boolean getGameStarted(){
            return gameStarted;
        }
        //Setter methods
        //Use this to toggle from outside
        public void stop(){
            toggleGameStarted();
        }
        //A setter that is used to toggle gameStarted
        public void toggleGameStarted(){
            if(gameStarted){
                gameStarted = false;
            }
            else{
                gameStarted = true;
            }
        }

    }

}
