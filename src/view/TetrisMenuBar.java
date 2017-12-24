/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 * This class creates the menu bar for Tetris.
 * @author Andrew Joshua Loria
 * @version 11/24/17
 *
 */
public class TetrisMenuBar extends JMenuBar {

    /**
     * Generated ID.
     */
    private static final long serialVersionUID = -8436264661459312329L;
    
    /**Options menu for the menubar.*/
    private final JMenu mySizeMenu;
    
    /**Help menu which includes about.*/
    private final JMenu myHelpMenu;

    /**The frame for the game.*/
    private final TetrisGUI myFrame;
    
    /**My button group for radio buttons.*/
    private ButtonGroup myButtonGroup;
    
    /**My button group for second menu of radio buttons.*/
    private ButtonGroup myButtonGroup2;

    /**Color menu for menubar.*/
    private final JMenu myColorMenu;

    /**Options menu for menubar.*/
    private final JMenu myOptionsMenu;
    
    /**The number of times the keyboard 'p' has been pressed.*/
    private int myCount;
    
    /**New game button.*/
    private JMenuItem myNewButton;
    
    /**Pause button.*/
    private JMenuItem myPauseButton;
    
    /**End game button.*/
    private JMenuItem myEndButton;

    
    /**
     * Constructs TetrisMenuBar with the JMenus and
     * buttongroups.
     * @param theFrame game frame.
     */
    public TetrisMenuBar(final TetrisGUI theFrame) {
        super();
        myFrame = theFrame;
        myOptionsMenu = new JMenu("Options");
        mySizeMenu = new JMenu("Size");
        myColorMenu = new JMenu("Color");
        myHelpMenu = new JMenu("Help");
       
        menuHelperMethods();
        
    }
    
    /**
     * Call the menu helper methods.
     */
    private void menuHelperMethods() {
        myCount = 0;
        myButtonGroup = new ButtonGroup();
        myButtonGroup2 = new ButtonGroup();
        add(makeOptions());
        add(makeSize());
        add(makeColor());
        add(makeHelp());
    }

    
    /**
     * An options menu for the game.
     * @return menu of options.
     */
    private JMenu makeOptions() {
        
        
        myPauseButton = new JMenuItem("Pause");
        myPauseButton.setAccelerator(KeyStroke.getKeyStroke('p'));
        
        final String propertyName = "pause";
        myPauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                if (myCount % 2 == 0) {
                    firePropertyChange(propertyName, "an old property", "paused"); 
                    /*I can ignore this CheckStyle warning because 
                     * I am sending different events
                    for the property named "pause".*/
                    myCount++;
                } else {
                    firePropertyChange(propertyName, "previous", "resumed");
                    myCount++;
                }
            }
        });
        
        myEndButton = new JMenuItem("End Game");
        myEndButton.setAccelerator(KeyStroke.getKeyStroke('e'));
        myEndButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(propertyName, "previous property", "stop");
                myEndButton.setEnabled(false);
                myPauseButton.setEnabled(false);
                myNewButton.setEnabled(true);
            }
        });
        
        myNewButton = new JMenuItem("New Game");
        myNewButton.setAccelerator(KeyStroke.getKeyStroke('n'));
        myNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("new game", "old", "new");
                myEndButton.setEnabled(true);
                myPauseButton.setEnabled(true);
                myNewButton.setEnabled(false);
            }
        });

        
        myEndButton.setEnabled(false);
        myPauseButton.setEnabled(false);
        myNewButton.setEnabled(true);
        myOptionsMenu.add(myNewButton);
        myOptionsMenu.add(myEndButton);
        myOptionsMenu.add(myPauseButton);
        return myOptionsMenu;
    }

    /**
     * A size menu for the game.
     * @return menu of sizes.
     */
    private JMenu makeSize() {
        final JRadioButtonMenuItem smallButton = new JRadioButtonMenuItem("Small Board");
        final JRadioButtonMenuItem mediumButton = new JRadioButtonMenuItem("Medium Board");
        final JRadioButtonMenuItem largeButton = new JRadioButtonMenuItem("Large Board");
        
        final String propertyName = "size";
        
        smallButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(propertyName, "a size", "small");
            }
        });
        
        mediumButton.setSelected(true);
        mediumButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(propertyName, "old size", "medium");
            }
        });
        
        largeButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(propertyName, "previous size", "large");
            }
        });
        
        myButtonGroup.add(smallButton);
        myButtonGroup.add(mediumButton);
        myButtonGroup.add(largeButton);
        
        mySizeMenu.add(smallButton);
        mySizeMenu.add(mediumButton);
        mySizeMenu.add(largeButton);
        return mySizeMenu;  
    }
    
    
    /**
     * Make the color JMenu for menu bar.
     * @return menu of color selections.
     */
    private JMenu makeColor() {
        final JRadioButtonMenuItem greenButton = new JRadioButtonMenuItem("Action Green");
        final JRadioButtonMenuItem navyButton = new JRadioButtonMenuItem("College Navy");
        final JRadioButtonMenuItem grayButton = new JRadioButtonMenuItem("Wolf Gray");
        
        final String propertyName = "color";
        greenButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(propertyName, "a color", "green");
            }
        });
        
        navyButton.setSelected(true);
        navyButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(propertyName, "another color", "navy");
            }
        });
        
        grayButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(propertyName, "a third color", "gray");
            }
        });
        
        myButtonGroup2.add(greenButton);
        myButtonGroup2.add(navyButton);
        myButtonGroup2.add(grayButton);
        
        myColorMenu.add(greenButton);
        myColorMenu.add(navyButton);
        myColorMenu.add(grayButton);
        
        return myColorMenu;  
    }
    
    /**
     * An options menu for the game.
     * @return menu of options.
     */
    private JMenu makeHelp() {
        final JMenuItem aboutButton = new JMenuItem("About...");
        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                //Display About
                final URL url = TetrisMenuBar.class.getResource("/resources/seahawks.png");
                final ImageIcon resizedIcon = new ImageIcon(new ImageIcon(url).
                                                            getImage().
                                getScaledInstance(57, 40, Image.SCALE_SMOOTH));
                
                JOptionPane.showMessageDialog(myFrame, "TCSS 305 Tetris \nAutumn 2017"
                                + "\nBy: Andrew Joshua Loria"
                                + "\nCompleted 12/10/2017\n\n"
                                + "\nColors & Logos:"
                                + "\nProperty of Seattle Seahawks"
                                + "\nwww.seahawks.com", "About", 
                                              JOptionPane.INFORMATION_MESSAGE, 
                                              resizedIcon);
            }
        });
        myHelpMenu.add(aboutButton);
        
        final JMenuItem instructions = new JMenuItem("Instructions...");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                //Display Instructions
                JOptionPane.showMessageDialog(myFrame, "Move Left: left arrow or 'a' or 'A' \n"
                                + "Move Right: right arrow or 'd' or 'D'\n"
                                + "Rotate: up arrow or 'w' or 'W'\n"
                                + "Move Down: down arrow or 's' or 'S'\n"
                                + "Drop: space", "Instructions", 
                                              JOptionPane.INFORMATION_MESSAGE, 
                                              null);
            }
        });
        myHelpMenu.add(instructions);
        
        final JMenuItem scoring = new JMenuItem("Scoring...");
        scoring.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                //Display Instructions
                JOptionPane.showMessageDialog(myFrame, "1 line clear at a time: +100 points\n"
                                + "2 line clears at a time: +500 points\n"
                                + "3 line clears at a time: +750 points\n"
                                + "4 line clears at a time: +1000 points\n",
                                              "Scoring", 
                                              JOptionPane.INFORMATION_MESSAGE, 
                                              null);
            }
        });
        myHelpMenu.add(scoring);
        
        return myHelpMenu;

    }

    /**
     * Returns new game button.
     * @return new game button.
     */
    public JMenuItem getMyNewButton() {
        return myNewButton;
    }
    
    /**
     * Returns the pause button.
     * @return pause button.
     */
    public JMenuItem getMyPauseButton() {
        return myPauseButton;
    }
    
    /**
     * Returns the end game button.
     * @return end game button.
     */
    public JMenuItem getMyEndButton() {
        return myEndButton;
    }

}
