/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.Board;

/**
 * The game panel in which tetris will be played. Displays graphics.
 * @author Andrew Joshua Loria
 * @version 11/24/17
 *
 */
public class TetrisPanel extends JPanel implements Observer, PropertyChangeListener {
    
    /**
     * Generated serial version ID.
     */
    private static final long serialVersionUID = 9101452085834044443L;
    
    /**Font.*/
    private static final String FONT = "Trebuchet MS";
    
    /**Navy color.*/
    private static final Color NAVY = new Color(0, 21, 51);
    
    /**Green color.*/
    private static final Color GREEN = new Color(77, 255, 0);
    
    /**Gray color.*/
    private static final Color GRAY = new Color(155, 161, 162);
    
    /**Small multiplier.*/
    private static final int SMALL_MULT = 20;
    
    /**Medium multiplier.*/
    private static final int MED_MULT = 30;
    
    /**Large multiplier.*/
    private static final int LARGE_MULT = 33;
    
    /**Large multiplier.*/
    private static final int BORDER_VAL = 5;
     
    
    /**Medium dimension default size.*/
    private static final Dimension MED_DIM = new Dimension(320, 610);  
    
    /** How many pixels the width/height that each piece that composes the block is.*/
    private int myMultiplier;
    
    /** The default size of the game panel. */
    private final Dimension myPanelSize; 
    
    /**The rectangle to paint.*/
    private Shape myRect;
        
    /** The array list of strings sent from board class.*/
    private String [] myBlocks;

    /** Block fill color.*/
    private Color myBlockColor;
    
    /** Block outline color.*/
    private Color myDrawColor;
    
    /** Whether or not the game is paused.*/
    private boolean myPause;
    
    /** Whether or not the game has ended.*/
    private boolean myEnd;
    
    /** Number of blocks per row. */
    private int myNumBlocksRow;
    
    /** Number of blocks per column. */
    private int myNumBlocksCol; 

    
    /**
     * Constructor for the tetris panel.
     * @param theBar the menubar.
     */
    public TetrisPanel(final TetrisMenuBar theBar) {
        super();
        final TetrisMenuBar menuBar = theBar;
        menuBar.addPropertyChangeListener(this);

        final Color backgroundColor = GRAY;
        setBackground(backgroundColor);
        setBorder(BorderFactory.createMatteBorder(BORDER_VAL, BORDER_VAL,
                                                  BORDER_VAL, BORDER_VAL, GREEN));
        myPanelSize = MED_DIM; //default medium size
        myMultiplier = MED_MULT;
        setUpPanel();
        
    }

    /**
     * Declare the defaults for the panel.
     */
    private void setUpPanel() {
        final int row = 10;
        final int col = 20;
        myNumBlocksRow = row;
        myNumBlocksCol = col;
        myBlockColor = NAVY;
        myDrawColor = GREEN;
        setPreferredSize(myPanelSize);
    }


    /**
     * Paint blocks.
     * @param theGraphics the graphic to paint.
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        if (myMultiplier == LARGE_MULT) {
            largeBoard(g2d);
        } else if (myMultiplier == SMALL_MULT) {
            smallBoard(g2d);
        } else {
            
            final int shift = 3;
          
            //each block is 30 pixels by 30 pixels
            //first multiplier is how far you go in the x direction
            //second multiplier is how far you go in the y direction
    
        
            for (int y = 0; y < myNumBlocksCol; y++) {
                for (int x = 1; x < myNumBlocksRow + 1; x++) {
                    if (myBlocks[y].charAt(x) != ' ') { 
                            // look at the yth row, the xth column
                        myRect = new Rectangle(x * myMultiplier - (2 * myMultiplier / shift),
                                                   y * myMultiplier + shift, 
                                                   myMultiplier, myMultiplier); 
                            //fill color
                        g2d.setColor(myBlockColor);
                        g2d.fill(myRect);
                        g2d.setColor(myDrawColor); //outline color
                        g2d.draw(myRect);
                    }
                }
            }                    
        }
        
        //To Address Checkstyle warnings
        final int stringHeight = 120;
        final int stringWidth = 63;
        final int pauseWidth = 75;
        final int height = 100;
        final int bigFont = 25;
        final int medFont = 20;
        final int smallFont = 15;
        final int stringHeightEnd = 90;
        
        if (myPause) {
            g2d.setFont(new Font(FONT, Font.BOLD, bigFont));
            g2d.drawString("Game Paused", pauseWidth, height);  
            g2d.setFont(new Font(FONT, Font.BOLD, medFont));
            g2d.drawString("Press 'p' to resume", stringWidth, stringHeight);
        }
        
        if (myEnd) {
            g2d.setFont(new Font(FONT, Font.BOLD, medFont));
            g2d.drawString("Game Ended", stringHeightEnd, height);  
            g2d.setFont(new Font(FONT, Font.BOLD, smallFont));
            g2d.drawString("Press 'n' for a new game", stringWidth + 2, stringHeight);
        }
               
        
        

    }    


    /**
     * Creates blocks for small board setting.
     * @param g2 the graphic to paint.
     */
    private void smallBoard(final Graphics2D g2) {
        for (int y = 0; y < myNumBlocksCol; y++) {  
            for (int x = 1; x < myNumBlocksRow + 1; x++) {
                //alternatively x = 1, x < 11 no need for checking '|'
                if (myBlocks[y].charAt(x) != ' ') { 
                    // look at the yth row, the xth column
                    myRect = new Rectangle(x * myMultiplier + myMultiplier,
                                           y * myMultiplier + myMultiplier, 
                                           myMultiplier, myMultiplier); 
                    g2.setColor(myBlockColor);
                    g2.fill(myRect);
                    g2.setColor(myDrawColor); //outline color
                    g2.draw(myRect);
                }
            }
        }
    }
    
    /**
     * Creates blocks for large board setting.
     * @param g2 the graphic to paint.
     */
    private void largeBoard(final Graphics2D g2) {
        
        final int xShift = 10;
        final int yShift = 70;
        for (int y = 0; y < myNumBlocksCol; y++) {  
            for (int x = 1; x < myNumBlocksRow + 1; x++) {
                if (myBlocks[y].charAt(x) != ' ') { 
                    // look at the yth row, the xth column

                    myRect = new Rectangle(x * myMultiplier - xShift,
                                           y * myMultiplier - (myMultiplier * 2) + yShift, 
                                           myMultiplier, myMultiplier); 
                    g2.setColor(myBlockColor);
                    g2.fill(myRect);
                    g2.setColor(myDrawColor); //outline color
                    g2.draw(myRect);
                }
            }
        }
    }
        
    

    @Override
    public void update(final Observable arg0, final Object arg1) {
        if (arg0 instanceof Board && arg1 instanceof String) { 
            /*In my if statement above I am checking if I am receiving a String 
            for arg1 because in notifyObservers from Board's down() method, 
            they are sending a toString. */
                        
            //add to array of strings
            final String [] temp = arg1.toString().split("\n");
            myBlocks = temp.clone();
            final int startingPoint = 5; 
            final int endingPoint = myBlocks.length;
            //only want from index 5 to 21
            myBlocks = Arrays.copyOfRange(myBlocks, startingPoint, endingPoint);
            repaint();
            
        }
        
    }

    /**
     * Checking for property changes and then changing value of fields
     * according to the value of the event.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("size".equals(theEvent.getPropertyName())) {
            if ("small".equals(theEvent.getNewValue())) {
                myMultiplier = SMALL_MULT;
            } else if ("medium".equals(theEvent.getNewValue())) {
                myMultiplier = MED_MULT;
            } else {
                myMultiplier = LARGE_MULT;                
            }
        }       
        
        if ("color".equals(theEvent.getPropertyName())) {
            if ("green".equals(theEvent.getNewValue())) {
                myBlockColor = GREEN;
                myDrawColor = NAVY;
            } else if ("navy".equals(theEvent.getNewValue())) {
                myBlockColor = NAVY;
                myDrawColor = GREEN;
            } else {
                myBlockColor = GRAY; 
                myDrawColor = NAVY;
            }
        }
        
    }
    
    /**
     * Sets the pause button to be enabled or not.
     * @param theBoolean true if enabled, false if not.
     */
    public void setPause(final boolean theBoolean) {
        myPause = theBoolean;
        repaint(); //notify user game paused
    }
    
    /**
     * Sets the end game button to be enabled or not.
     * @param theBool true if enabled, false if not.
     */
    public void setEnd(final boolean theBool) {
        myEnd = theBool;
        repaint(); //notify user game has ended 
    }


}
