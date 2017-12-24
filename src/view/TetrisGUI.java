/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Board;

/**
 * This class creates the JFrame, the GUI for the actual Tetris
 * game. Many required elements are added here.
 * @author Andrew Joshua Loria
 * @version 11/23/17
 *
 */
public class TetrisGUI extends JFrame implements Observer, PropertyChangeListener {
    
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 8296069764415225921L;

    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /** The minimum size. */
    private static final Dimension MIN_SIZE = new Dimension(500, 500);
    
    /**Small dimension.*/
    private static final Dimension SMALL_DIM = new Dimension(50, 50);
    
    /**Medium dimension.*/
    private static final Dimension MED_DIM = new Dimension(545, 670); 
    
    /**Large dimension.*/
    private static final Dimension LARGE_DIM = new Dimension(600, 730);
    
    /**Timer initial delay.*/
    private static final int INIT_DELAY = 1000;
    
    
    /**
     * The numerator for initial delay calculations.
     */
    private final int myDelay = INIT_DELAY;

    /**Timer for Tetris.*/
    private Timer myTimer;
    
    /**Menu bar for GUI.*/
    private final TetrisMenuBar myMenuBar;
    
    /**Game panel.*/
    private final TetrisPanel myTetrisPanel;
    
    /**Preview panel.*/
    private final TetrisPreviewPanel myPreview;  
    
    /**Instructions for key presses.*/
    private final TetrisInstructions myInstructions;
    
    /**Score panel.*/
    private final TetrisScorePanel myScorePanel;
    
    /**Tetris board.*/
    private final Board myBoard;
    
    /**The tetris keys used to control the pieces.*/
    private final TetrisKeys myTetrisKeys;
 
    
    /**
     * Constructor for Tetris GUI.
     * Initializes fields.
     */
    public TetrisGUI() {
        super("Seahawks Tetris");
        makeTimer();      
        myMenuBar = new TetrisMenuBar(this);
        myPreview = new TetrisPreviewPanel();
        myInstructions = new TetrisInstructions();
        myBoard = new Board();
        myTetrisPanel = new TetrisPanel(myMenuBar);       
        myTetrisKeys = new TetrisKeys(myBoard);
        myScorePanel = new TetrisScorePanel(myTimer);

        makeGUI();        
    }
    
    /**
     * In this methods, initial tasks are performed before starting
     * a new game.
     */
    private void initialGame() {
        myBoard.newGame();
        myTimer.stop();
        removeKeyListener(myTetrisKeys);      
    }

    /**
     * Creates timer for game.
     */
    private void makeTimer() {
        myTimer = new Timer(myDelay, new TimerListener());
        
    }

    /**
     * Start the game of Tetris.
     */
    private void start() {
        addKeyListener(myTetrisKeys);
        myBoard.newGame();
        myTimer.start();      
        
    }

    /**
     * Method that helps set up the GUI.
     */
    private void makeGUI() {       
        //PropertyChangeListeners
        myMenuBar.addPropertyChangeListener(this);
        
        //Icon
        final URL url = TetrisGUI.class.getResource("/resources/12.jpg");
        setIconImage(new ImageIcon(url).getImage()); 
        
        myBoard.addObserver(myTetrisPanel);
        myBoard.addObserver(myPreview);
        myBoard.addObserver(myScorePanel);
        myBoard.addObserver(this);
        
        final JPanel eastPan = new JPanel();
        eastPan.setLayout(new BoxLayout(eastPan, BoxLayout.PAGE_AXIS));
        eastPan.setBackground(Color.DARK_GRAY);
        eastPan.add(myPreview, BorderLayout.NORTH);
        eastPan.add(myScorePanel, BorderLayout.EAST);
        eastPan.add(myInstructions, BorderLayout.SOUTH);
            
        add(eastPan, BorderLayout.EAST);
        this.setBackground(Color.WHITE);
        addKeyListener(myTetrisKeys);
        setJMenuBar(myMenuBar);
        add(myTetrisPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(MIN_SIZE);
        setResizable(true);
        pack();
        
        // position the frame in the center of the screen
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                    SCREEN_SIZE.height / 2 - getHeight() / 2);
        setVisible(true);
        
        initialGame();
        
    }

    /**
     * In update, when game is over, a dialog will display.
     */
    @Override
    public void update(final Observable theArg0, final Object theArg1) {
        if (theArg1 instanceof Boolean) {
            myTimer.stop();
            removeKeyListener(myTetrisKeys);
            myScorePanel.highScore();
            JOptionPane.showMessageDialog(this, "GAME OVER! To play a new game,"
                            + " click OK and press 'n' on your keyboard.", "Game Over", 
                                            JOptionPane.WARNING_MESSAGE, 
                                            null);
            myMenuBar.getMyNewButton().setEnabled(true);
            myMenuBar.getMyPauseButton().setEnabled(false);
            myMenuBar.getMyEndButton().setEnabled(false);
            firePropertyChange("status", "something", "over");

        }
        
    }
    
    /**
     * Make frame larger or smaller depending on selection by user
     * of the GUI size.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("size".equals(theEvent.getPropertyName())) {
            if ("small".equals(theEvent.getNewValue())) {
                setSize(SMALL_DIM);
                
                //Center frame
                setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                            SCREEN_SIZE.height / 2 - getHeight() / 2);
            } else if ("medium".equals(theEvent.getNewValue())) {
                setSize(MED_DIM);
                
                //Center frame
                setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                            SCREEN_SIZE.height / 2 - getHeight() / 2);
            } else {
                setSize(LARGE_DIM);   //600 700
                
                //Center frame
                setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                            SCREEN_SIZE.height / 2 - getHeight() / 2);
            }
        }
        
        if ("pause".equals(theEvent.getPropertyName())) {
            if ("paused".equals(theEvent.getNewValue())) {
                removeKeyListener(myTetrisKeys);
                myTimer.stop();
                removeKeyListener(myTetrisKeys);
                myTetrisPanel.setPause(true);
            } else if ("stop".equals(theEvent.getNewValue())) {
                removeKeyListener(myTetrisKeys);
                myScorePanel.highScore();
                myTimer.stop();
                firePropertyChange("status", "not", "over");
                myTetrisPanel.setPause(false);
                myTetrisPanel.setEnd(true);
                removeKeyListener(myTetrisKeys);
            } else {
                addKeyListener(myTetrisKeys);
                myTetrisPanel.setPause(false);
                myTimer.start();
            }
        }         
        
        
        if ("new game".equals(theEvent.getPropertyName()) 
                        && "new".equals(theEvent.getNewValue())) {
            myTetrisPanel.setEnd(false);
            myScorePanel.reset();
            initialGame();
            start();
        } 
    }
    
    
    
    /**
     * My timer listener.
     * @author Andrew Joshua Loria
     * @version 11/30/17
     *
     */
    private class TimerListener implements ActionListener {
        
        /**
         * While timer is running, move piece down on board.
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myBoard.down(); 
        }
    }          
    


}
