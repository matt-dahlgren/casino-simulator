package use_case.freeplay.stand;

import java.util.ArrayList;

public class FreePlayStandOutputData {

    private final int dealerScore;
    private final int playerScore;
    private final boolean playerWin;
    private final ArrayList<String> dealerCardPictures;

    public FreePlayStandOutputData(int dealerScore, int playerScore, ArrayList<String> dealerCardPictures, boolean win) {
        this.dealerScore = dealerScore;
        this.playerScore = playerScore;
        this.dealerCardPictures = dealerCardPictures;
        this.playerWin = win;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public ArrayList<String> getDealerCardPictures() {
        return dealerCardPictures;
    }

    public boolean isPlayerWin() {
        return playerWin;
    }
}
