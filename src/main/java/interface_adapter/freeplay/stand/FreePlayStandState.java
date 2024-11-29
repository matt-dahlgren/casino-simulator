package interface_adapter.freeplay.stand;

import java.util.ArrayList;

/**
 * The state for the Team Use Case (free play mode) View Model.
 *
 */
public class FreePlayStandState {

    private int dealerScore;
    private int playerScore;
    private ArrayList<String> cardImages;
    private boolean playerWin;

    @Override
    public String toString() {
        return "FreePlayStandState";
    }

    public void setCardImages(ArrayList<String> cardImages) {
        this.cardImages = cardImages;
    }

    public void setDealerScore(int dealerScore) {
        this.dealerScore = dealerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public ArrayList<String> getCardImages() {
        return cardImages;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerWin(boolean playerWin) {
        this.playerWin = playerWin;
    }

    public boolean getPlayerWin() {
        return playerWin;
    }
}
