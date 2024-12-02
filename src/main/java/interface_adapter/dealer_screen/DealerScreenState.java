package interface_adapter.dealer_screen;

import java.util.ArrayList;

/**
 * The state for the Team Use Case (free play mode) View Model.
 *
 */
public class DealerScreenState {

    private int dealerScore;
    private int playerScore;
    private ArrayList<String> cardImages;
    private boolean playerWin;
    private String numGame;

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

    public void setNumGame(String numGame) {
        this.numGame = numGame;
    }
    public String getNumGame() {
        return numGame;
    }
}
