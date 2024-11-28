package use_case.freeplay.stand;

import java.util.List;

public class FreePlayStandOutputData {

    private final int dealerScore;
    private final int playerScore;
    private final boolean playerWin;
    private final List<String> dealerCardPictures;

    public FreePlayStandOutputData(int dealerScore, int playerScore, List<String> dealerCardPictures, boolean win) {
        this.dealerScore = dealerScore;
        this.playerScore = playerScore;
        this.dealerCardPictures = dealerCardPictures;
        this.playerWin = win;
    }

}
