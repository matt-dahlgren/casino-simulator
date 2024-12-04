package use_case.freeplay.stand;

/**
 * Output data for stand use case.
 */

import java.util.ArrayList;

public class StandOutputData {
    private final ArrayList<String> dealerHand;
    private final int dscore;
    private final boolean winGame;

    public StandOutputData(ArrayList<String> dealerHand, int dscore, boolean winGame) {
        this.dealerHand = dealerHand;
        this.dscore = dscore;
        this.winGame = winGame;
    }

    public ArrayList<String> getDealerHand() {
        return dealerHand;
    }

    public int getDscore() {
        return dscore;
    }

    public boolean isWinGame() {
        return winGame;
    }
}
