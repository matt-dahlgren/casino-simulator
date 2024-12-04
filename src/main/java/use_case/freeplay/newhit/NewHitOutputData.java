package use_case.freeplay.newhit;

import java.util.ArrayList;

/**
 * Output Data for the Hit Use Case.
 */

public class NewHitOutputData {
    private final ArrayList<String> userPlayerHand;
    private final int score;
    private final boolean win;

    /**
     * The output data for Setup
     * @param userPlayerHand the list of all the image links for the images of the userPlayer's hand.
     */
    public NewHitOutputData(ArrayList<String> userPlayerHand, int score, boolean win) {
        this.userPlayerHand = userPlayerHand;
        this.score = score;
        this.win = win;
    }


    public ArrayList<String> getUserPlayerHand() {return this.userPlayerHand;}

    public int getScore() {return this.score;}

    public boolean getWin() {return this.win;}
}