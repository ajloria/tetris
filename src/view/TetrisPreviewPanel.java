/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.MovableTetrisPiece;

/**
 * This panel previews the next piece that will fall
 * onto the main game panel.
 * @author Andrew Joshua Loria
 * @version 11/27/17
 *
 */
public class TetrisPreviewPanel extends JPanel implements Observer {

    /**
     * Generated ID.
     */
    private static final long serialVersionUID = 6426667991276253882L;
    
    /**Size of panel.*/
    private static final Dimension PREVIEW_PANEL_SIZE = new Dimension(210, 20); //225, 50
    
    /**Default font style.*/
    private static final Font DEFAULT_FONT = new Font("Trebuchet MS", Font.BOLD, 13);
    
    /**Green color.*/
    private static final Color GREEN = new Color(77, 255, 0);
    
    /**Navy color.*/
    private static final Color NAVY = new Color(0, 21, 51);
    
    /**Scale multiplier.*/
    private static final int MULTIPLIER = 20;
   
    /**Next piece.*/
    private String[] myUpcomingPiece;

    /**Rectangle to paint.*/
    private Rectangle myUpcomingRect;
    
   
    /**
     * Constructs new tetris preview panel.
     */
    public TetrisPreviewPanel() {
        super();
        setPreferredSize(PREVIEW_PANEL_SIZE);
        setBackground(NAVY);
        makeLabel();
    }
    
    /**
     * Make label for top of preview panel.
     */
    private void makeLabel() {
        final JLabel label = new JLabel("Next Piece Preview");
        label.setFont(DEFAULT_FONT);
        label.setForeground(Color.WHITE);
        add(label);      
        
    }
    
    /**
     * Paint the upcoming block.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setStroke(new BasicStroke(2));
      //  System.out.println("next" + myUpcomingPiece);
        for (int i = 0; i < myUpcomingPiece.length - 1; i++) {
            for (int j = 0; j < myUpcomingPiece[i].length() - 1; j++) {
                if (myUpcomingPiece[i].charAt(j) != ' ') {
                    if (myUpcomingPiece[i].charAt(j) == 'I') {
                        myUpcomingRect = new Rectangle(j * MULTIPLIER + 65,
                                                       i * MULTIPLIER + 60,
                                                       MULTIPLIER, MULTIPLIER);
                        
                        /*create an extra rectangle to address issue of drawing only
                        3 rectangles initially.*/
                        final Rectangle rect = new Rectangle((j + 1) * MULTIPLIER + 65,
                                                       i * MULTIPLIER + 60,
                                                       MULTIPLIER, MULTIPLIER);
                        g2d.setColor(GREEN); //fill color
                        g2d.fill(myUpcomingRect);
                        g2d.setColor(Color.WHITE); //outline color
                        g2d.draw(myUpcomingRect);
                        
                        //Fill, draw extra rectangle.
                        g2d.setColor(GREEN); //fill color
                        g2d.fill(rect);
                        g2d.setColor(Color.WHITE); //outline color
                        g2d.draw(rect);
                    } else if (myUpcomingPiece[i].charAt(j) == 'O') {
                        //to center O block in panel
                        myUpcomingRect = new Rectangle(j * MULTIPLIER + 65,
                                                       i * MULTIPLIER + 60,
                                                       MULTIPLIER, MULTIPLIER);

                        g2d.setColor(GREEN); //fill color
                        g2d.fill(myUpcomingRect);
                        g2d.setColor(Color.WHITE); //outline color
                        g2d.draw(myUpcomingRect);

                    } else {
                        myUpcomingRect = new Rectangle(j * MULTIPLIER + 75,
                                                   i * MULTIPLIER + 50,
                                                   MULTIPLIER, MULTIPLIER);  
                        //fill color
                        g2d.setColor(GREEN);
                        g2d.fill(myUpcomingRect);
                        g2d.setColor(Color.WHITE); //outline color
                        g2d.draw(myUpcomingRect);
                    }
                }
            }
        }

    }    

    /**
     * Get the next piece from the board, cast it as movable tetris piece, get its
     * toString and get the first 5 lines of the array.
     */
    @Override
    public void update(final Observable arg0, final Object arg1) {
        if (arg1 instanceof MovableTetrisPiece) {
            myUpcomingPiece = ((MovableTetrisPiece) arg1).toString().split("\n");
            //only want top part of board
            myUpcomingPiece = Arrays.copyOfRange(myUpcomingPiece, 0, 5);
            repaint();
        }
        
    }




}
