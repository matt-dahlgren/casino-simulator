package interface_adapter.freePlay.setup;

import java.util.ArrayList;

public class SetupState {
    private ArrayList<String> dealerHand;
    private ArrayList<String> playerHand;
    private int score;

    public SetupState() {}

    public SetupState(SetupState copy) {
        dealerHand = copy.dealerHand;
        playerHand = copy.playerHand;
        score = copy.score;
    }

    public ArrayList<String> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<String> getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand(ArrayList<String> DealerHand) {
        this.dealerHand = DealerHand;
    }

    public void setPlayerHand(ArrayList<String> PlayerHand) {
        this.playerHand = PlayerHand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
