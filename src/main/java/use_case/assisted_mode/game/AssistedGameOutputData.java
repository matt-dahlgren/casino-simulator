package use_case.assisted_mode.game;

import java.util.ArrayList;

public class AssistedGameOutputData {
    private final int hitWin;
    private final int standWin;
    private final int playerScore;
    private final ArrayList<String> playerCards;
    private final ArrayList<String> dealerCards;
    private final int dealerScore;
    private final boolean gameWin;

    public AssistedGameOutputData(int hitWin, int standWin, int playerScore, int dealerScore,
                                  ArrayList<String> playerCards, ArrayList<String> dealerCards, boolean gameWin) {
        this.hitWin = hitWin;
        this.standWin = standWin;
        this.playerScore = playerScore;
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
        this.dealerScore = dealerScore;
        this.gameWin = gameWin;

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

    public int getDealerScore() {
        return dealerScore;
    }
    public boolean isGameWin() {
        return gameWin;
    }
}
