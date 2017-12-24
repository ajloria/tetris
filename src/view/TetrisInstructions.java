/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This panel displays the keys needed to play the game correctly.
 * @author Andrew Joshua Loria
 * @version 11/27/17
 *
 */
public class TetrisInstructions extends JPanel {


    
    /**
     * Generated ID.
     */
    private static final long serialVersionUID = -406546313489582068L;
    
    /**Navy color.*/
    private static final Color NAVY = new Color(0, 21, 51);
    
    /**Size of panel.*/
    private static final Dimension PANEL_SIZE = new Dimension(50, 50);
            
    /**Default font style.*/
    private static final Font DEFAULT_FONT = new Font("Trebuchet MS", Font.PLAIN, 13);



    /**
     * Constructs new panel of TetrisInstructions.
     */
    public TetrisInstructions() {
        super();
        setPreferredSize(PANEL_SIZE);
        setBackground(NAVY);
        makePanel();
    }
    
    /**
     * Prints out instructions to panel.
     */
    private void makePanel() {
        final JLabel label1 = new JLabel("Move Left: left arrow or 'a' or 'A'");
        label1.setFont(DEFAULT_FONT);
        label1.setForeground(Color.WHITE);

        
        final JLabel label2 = new JLabel("Move Right: right arrow or 'd' or 'D'");
        label2.setFont(DEFAULT_FONT);
        label2.setForeground(Color.WHITE);

        
        final JLabel label3 = new JLabel("Rotate: up arrow or 'w' or 'W'");
        label3.setFont(DEFAULT_FONT);
        label3.setForeground(Color.WHITE);

        
        final JLabel label4 = new JLabel("Move Down: down arrow or 's' or 'S'");
        label4.setFont(DEFAULT_FONT);
        label4.setForeground(Color.WHITE);

        
        final JLabel label5 = new JLabel("Drop: space");
        label5.setFont(DEFAULT_FONT);
        label5.setForeground(Color.WHITE);

        
        final JLabel label6 = new JLabel("This is an invisible label this is an invisible label");
        label6.setFont(DEFAULT_FONT);
        label6.setForeground(NAVY);
        
        final JLabel label7 = new JLabel("Start New Game: 'n'");
        label7.setFont(DEFAULT_FONT);
        label7.setForeground(Color.WHITE);

        
        final JLabel label8 = new JLabel("Pause/Resume Game: 'p'");
        label8.setFont(DEFAULT_FONT);
        label8.setForeground(Color.WHITE);

        
        final JLabel label9 = new JLabel("End Game: 'e'");
        label9.setFont(DEFAULT_FONT);
        label9.setForeground(Color.WHITE);

        
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(label8);
        add(label9);
        

    }
    


}
