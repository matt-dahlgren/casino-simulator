package use_case.assisted_mode.hit;

import java.util.ArrayList;

public class AssistedHitOutputData {
    private final int hitWin;
    private final int standWin;
    private final int playerScore;
    private final ArrayList<String> playerCards;
    private final ArrayList<String> dealerCards;

    public AssistedHitOutputData(int hitWin, int standWin, int playerScore,
                                 ArrayList<String> playerCards, ArrayList<String> dealerCards) {
        this.hitWin = hitWin;
        this.standWin = standWin;
        this.playerScore = playerScore;
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
    }

    public int getHitWin() {
        return hitWin;
    }

    public int getStandWin() {
        return standWin;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public ArrayList<String> getPlayerCards() {
        return playerCards;
    }

    public ArrayList<String> getDealerCards() {
        return dealerCards;
    }
}
