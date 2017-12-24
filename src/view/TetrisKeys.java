/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import model.Board;

/**
 * This class listens to the keys pressed on the keyboard 
 * and responds based on the key that is pressed.
 * @author Andrew Joshua Loria
 * @version 11/24/17
 *
 */
public class TetrisKeys extends KeyAdapter {
    
    /**The Tetris board.*/
    private final Board myBoard;
 
    
    /**
     * Initializes board field.
     * @param theBoard Tetris Board.
     */
    public TetrisKeys(final Board theBoard) {
        super();
        myBoard = theBoard;
    }

    
    /**
     * Calls the correct method from the Board class based on the key press.
     * @param theEvent the Key Event associated.
     */
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        if (theEvent.getKeyCode() == KeyEvent.VK_LEFT
                        || theEvent.getKeyCode() == KeyEvent.VK_A) {
            myBoard.left();
        }
        if (theEvent.getKeyCode() == KeyEvent.VK_RIGHT
                        || theEvent.getKeyCode() == KeyEvent.VK_D) {
            myBoard.right();
        }
        if (theEvent.getKeyCode() == KeyEvent.VK_UP
                        || theEvent.getKeyCode() == KeyEvent.VK_W) {
            myBoard.rotate();
        }
        if (theEvent.getKeyCode() == KeyEvent.VK_DOWN
                        || theEvent.getKeyCode() == KeyEvent.VK_S) {
            myBoard.down();
        }
        if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
            myBoard.drop();
        }
    }

}
