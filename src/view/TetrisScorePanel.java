/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

/**
 * This is the panel where the score will be updated and displayed
 * in the game. It will also store the highest score ever scored on this game.
 * @author Andrew Joshua Loria
 * @version 12/6/17
 *
 */
public class TetrisScorePanel extends JPanel implements Observer {
    
    /**
     * Generated ID.
     */
    private static final long serialVersionUID = 3700491093259514428L;
    
    /**Size of panel.*/
    private static final Dimension PANEL_SIZE = new Dimension(50, 50);
    
    /**Green color.*/
    private static final Color GREEN = new Color(77, 255, 0);
    
    /**Navy color.*/
    private static final Color NAVY = new Color(0, 21, 51);
    
    /**Default font style.*/
    private static final Font DEFAULT_FONT = new Font("Trebuchet MS", Font.PLAIN, 13);
    
    /**Number of lines to clear until next level.*/
    private static final int LINES_UNTIL_NEXT = 5;
    
    /**The first level timer delay.*/
    private static final int FIRST_LEVEL_DELAY = 1000;

    /**The second level timer delay.*/
    private static final int SECOND_LEVEL_DELAY = 450;
    
    /**The third level timer delay.*/
    private static final int THIRD_LEVEL_DELAY = 350;
    
    /**The fourth level timer delay.*/
    private static final int FOURTH_LEVEL_DELAY = 250;
    
    /**The fifth level timer delay.*/
    private static final int FIFTH_LEVEL_DELAY = 200;

    /**Score title.*/
    private static final String SCORE_TITLE = "Score: ";

    /**Level title.*/
    private static final String LEVEL_TITLE = "  Level: ";
        
    /**Score.*/
    private int myScore;

    /**The level.*/
    private int myLevel;

    /**Number of full lines cleared.*/
    private int myNumberOfLinesCleared;
    
    /**The label for number of lines cleared.*/
    private JLabel myLinesLabel;
    
    /**The label for total score.*/
    private JLabel myScoreLabel;
    
    /**The label for the current label.*/
    private JLabel myLevelLabel;

    /**The label for the high score.*/
    private JLabel myHighScoreLabel;
        
    /**The label for the number of lines before next level.*/
    private JLabel myNextLabel;
    
    /**My timer that will speed up through leveling up.*/
    private final Timer myTimer;

    /**My high score.*/
    private int mySessionHighScore;
    
    /**The number of lines before next level.*/
    private int myNext;
    

    /**
     * Constructs new score panel.
     * The timer is passed because as we level up, we want the delay
     * to go down.
     * @param theTimer the Timer used in GUI.
     */
    public TetrisScorePanel(final Timer theTimer) {
        super(); 
        myTimer = theTimer;
        this.setLayout(new FlowLayout());
        setPreferredSize(PANEL_SIZE);
        setBackground(GREEN);
        displayScore();
    }

    /**
     * Initial score display when GUI starts.
     */
    private void displayScore() {
        myScore = 0;
        myLevel = 1;
        myNext = LINES_UNTIL_NEXT;
        mySessionHighScore = 0;
        
        myScoreLabel = new JLabel(SCORE_TITLE + myScore);
        myScoreLabel.setForeground(Color.BLACK);
        myScoreLabel.setFont(DEFAULT_FONT);

        myLevelLabel = new JLabel(LEVEL_TITLE + myLevel);
        myLevelLabel.setForeground(Color.BLACK);
        myLevelLabel.setFont(DEFAULT_FONT);

        
        myNextLabel = new JLabel("# of Lines Until Next Level: " + myNext);
        myNextLabel.setForeground(Color.BLACK);
        myNextLabel.setFont(DEFAULT_FONT);

        
        myLinesLabel = new JLabel("# of Lines Cleared:");
        myLinesLabel.setForeground(Color.BLACK);
        myLinesLabel.setFont(DEFAULT_FONT);

        myHighScoreLabel = new JLabel("Session High Score:   " + mySessionHighScore);
        myHighScoreLabel.setForeground(Color.BLACK);
        myHighScoreLabel.setFont(DEFAULT_FONT);      
        
        add(myScoreLabel);
        add(myLevelLabel);
        add(myLinesLabel);
        add(myNextLabel);
        add(myHighScoreLabel);
        

        final TitledBorder border = new TitledBorder(BorderFactory.
                                                     createMatteBorder(5, 5, 5, 5, NAVY), 
                                                   "Score Panel", TitledBorder.CENTER,
                                                   TitledBorder.TOP);
        border.setTitleFont(DEFAULT_FONT);
        border.setTitleColor(NAVY);
        setBorder(BorderFactory.createTitledBorder(border));
    }



    @Override
    public void update(final Observable theObservable, final Object theObject) {
        if (theObject instanceof Integer []) {
            final int numLines = ((Integer []) theObject).length;
            updateLabels(numLines);
            repaint();
        }
        
    }

    /**
     * Updates score and number of lines cleared labels.
     * Also changes timer delay for each level up.
     * @param theNumLines the number of lines cleared.
     */
    private void updateLabels(final int theNumLines) {
        myNumberOfLinesCleared = myNumberOfLinesCleared + theNumLines;
        myLinesLabel.setText("# of Lines Cleared: " + myNumberOfLinesCleared);
        
        final int score = lineBonus(theNumLines); // theNumLines * 100;
        myScore = myScore + score;
        myScoreLabel.setText(SCORE_TITLE + myScore);
        
        //assign variables to address of Checkstyle magic number warnings
        final int level3 = 3;
        final int level4 = 4;
        final int level5 = 5;
        
        final int linesLevel2 = 4;
        final int linesLevel3 = 9;
        final int linesLevel4 = 14;
        final int linesLevel5 = 19;
        
       
        if (myScore > mySessionHighScore) {
            highScore();
        }
        
        if (myNumberOfLinesCleared > linesLevel2) {
            myLevel = 2;
            myTimer.setDelay(SECOND_LEVEL_DELAY);
        } 
        
        if (myNumberOfLinesCleared > linesLevel3) {
            myLevel = level3;
            myTimer.setDelay(THIRD_LEVEL_DELAY);
        } 
        
        if (myNumberOfLinesCleared > linesLevel4) {
            myLevel = level4;
            myTimer.setDelay(FOURTH_LEVEL_DELAY);
        }
        
        if (myNumberOfLinesCleared > linesLevel5) {
            myLevel = level5;
            myTimer.setDelay(FIFTH_LEVEL_DELAY);
        }
        
        
        myLevelLabel.setText(LEVEL_TITLE + myLevel);
        if (myLevel == level5) {
            myNextLabel.setText("Wow! Max level reached!");
        } else {
            myNext = LINES_UNTIL_NEXT * myLevel - myNumberOfLinesCleared;
            myNextLabel.setText("# of Lines Until Next Level:" + myNext);
        }

        
    }
    
    /**
     * The score that is added per line clear is determined here.
     * @param theNumLines number of lines cleared in one turn.
     * @return score to add to total.
     */
    private int lineBonus(final int theNumLines) {
        
        final int bonus;
        final int singleLineMultiplier = 100;
        final int multipleLinesMultiplier = 250;
        
        if (theNumLines == 1) {
            bonus =  theNumLines * singleLineMultiplier;
        }  else {
            bonus =  theNumLines * multipleLinesMultiplier;
        }
        
        return bonus;

    }

    /**
     * Resets the number of lines, resets the score, 
     * and resets the level for a new game.
     */
    public void reset() {
        myTimer.setDelay(FIRST_LEVEL_DELAY);
        resetNumberOfLines();
        resetScore();
        resetLevel();
        resetNext();
        
    }

    /**
     * Resets the level to 1.
     */
    private void resetLevel() {
        myLevel = 1;
        myLevelLabel.setText("  Level: 1");
        
    }

    /**
     * Resets number of lines to 0.
     */
    private void resetNumberOfLines() {
        myNumberOfLinesCleared = 0;
        myLinesLabel.setText("# of Lines Cleared: 0");
    }
    
    /**
     * Resets number of lines until next level to 5.
     */
    private void resetNext() {
        myNext = LINES_UNTIL_NEXT;
        myNextLabel.setText("# of Lines Until Next Level: 5");
    }
    
    /**
     * Method that updates the high score.
     */
    public void highScore() {
        if (myScore > mySessionHighScore) {
            mySessionHighScore = myScore;
        }
        myHighScoreLabel.setText("Session High Score: " + mySessionHighScore);
    }   

    /**
     * Resets score to 0 when there is a new game.
     */
    private void resetScore() {
        myScore = 0;
        myScoreLabel.setText("Score: 0");
        
    }

  
    
}
