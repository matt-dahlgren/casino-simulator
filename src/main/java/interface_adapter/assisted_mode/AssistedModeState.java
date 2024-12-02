package interface_adapter.assisted_mode;

import java.util.ArrayList;

/**
 * The state for the assisted mode's View Model.
 */
public class AssistedModeState {
    private int dealerScore;
    private int playerScore;
    private int hitWin;
    private int standWin;
    private ArrayList<String> playerCardImages;
    private ArrayList<String> dealerCardImages;

    @Override
    public String toString() {
        return "AssistedModeState";
    }

    public void setPlayerCardImages(ArrayList<String> cardImages) {
        this.playerCardImages = cardImages;
    }

    public void setDealerCardImages(ArrayList<String> cardImages) {
        this.dealerCardImages = cardImages;
    }

    public void setDealerScore(int dealerScore) {
        this.dealerScore = dealerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public ArrayList<String> getDealerCardImages() {
        return dealerCardImages;
    }

    public ArrayList<String> getPlayerCardImages() {
        return playerCardImages;
    }

    public int getDealerScore() {
        return dealerScore;
    }


    public int getPlayerScore() {
        return playerScore;
    }

    public int getHitWin() {
        return hitWin;
    }
    public int getStandWin() {
        return standWin;
    }

    public void setHitWin(int hitWin) {
        this.hitWin = hitWin;
    }

    public void setStandWin(int standWin) {
        this.standWin = standWin;
    }
}
