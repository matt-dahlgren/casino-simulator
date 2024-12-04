package use_case.freeplay.setup;

import java.util.ArrayList;

/**
 * Output Data for the Setup Use Case.
 *
 */
public class SetupOutputData {
    private final ArrayList<String> dealerHand;
    private final ArrayList<String> userPlayerHand;
    private final boolean winGame;
    private final int playerScore;
    private final int dealerScore;

    /**
     * The output data for Setup
     *
     * @param dealerHand     the list of all the image links for the images of the dealer's hand.
     * @param userPlayerHand the list of all the image links for the images of the userPlayer's hand.
     * @param dealerScore
     */
    public SetupOutputData(ArrayList<String> dealerHand, ArrayList<String> userPlayerHand, boolean winGame,
                           int playerScore, int dealerScore) {
        this.userPlayerHand = userPlayerHand;
        this.dealerHand = dealerHand;
        this.winGame = winGame;
        this.playerScore = playerScore;
        this.dealerScore = dealerScore;
    }

    public ArrayList<String> getDealerHand() {return this.dealerHand;}

    public ArrayList<String> getUserPlayerHand() {return this.userPlayerHand;}

    public boolean isWinGame() {return this.winGame;}

    public int getPlayerScore() {return this.playerScore;}

    public int getDealerScore() {return this.dealerScore;}

}
