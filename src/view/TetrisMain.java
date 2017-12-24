/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view;

import java.awt.EventQueue;



/**
 * Main method that starts the program up.
 * @author Andrew Joshua Loria
 * @version 11/23/17
 *
 */
public final class TetrisMain {
   
    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private TetrisMain() {
        throw new IllegalStateException();
    }
    
    
    /**
     * Main method.
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                new TetrisGUI();

            }
        });
    }

}
