package interface_adapter.free_play.setup;

import java.util.ArrayList;

public class SetupState {
    private ArrayList<String> dealerHand;
    private ArrayList<String> playerHand;
    private int score;
    private boolean win;
    private int dealerScore;
    private String hiddenDealer;

    public SetupState() {}

    public SetupState(SetupState copy) {
        dealerHand = copy.dealerHand;
        playerHand = copy.playerHand;
        score = copy.score;
        win = copy.win;
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

    public boolean getWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setDealerScore(int dealerScore) {
        this.dealerScore = dealerScore;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public String getHiddenDealer() {
        return hiddenDealer;
    }

    public void setHiddenDealer(String hiddenDealer) {
        this.hiddenDealer = hiddenDealer;
    }
}
