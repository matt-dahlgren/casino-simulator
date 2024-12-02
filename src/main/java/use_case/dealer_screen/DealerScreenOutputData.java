package use_case.dealer_screen;

import java.util.ArrayList;

public class DealerScreenOutputData {

    private final int dealerScore;
    private final int playerScore;
    private final boolean playerWin;
    private final ArrayList<String> dealerCardPictures;
    private final String gameNum;

    public DealerScreenOutputData(int dealerScore, int playerScore, ArrayList<String> dealerCardPictures, boolean win,
                                  String gameNum) {
        this.dealerScore = dealerScore;
        this.playerScore = playerScore;
        this.dealerCardPictures = dealerCardPictures;
        this.playerWin = win;
        this.gameNum = gameNum;
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

    public String getGameNum() {
        return gameNum;
    }
}
