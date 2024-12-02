package use_case.assisted_mode.setup;

import data_access.GameDataAccessObject;

import java.util.ArrayList;

/**
 * Output data for the set up of an assisted BlackJack Game.
 */
public class AssistedSetUpOutputData {

    private final GameDataAccessObject gameDataAccessObject;
    private final int hitWin;
    private final int standWin;
    private final int playerScore;
    private final ArrayList<String> playerCards;
    private final ArrayList<String> dealerCards;
    //TODO: Jacob's DAO
    public AssistedSetUpOutputData(GameDataAccessObject gameDataAccessObject, int hitWin, int standWin, int playerScore,
                                   ArrayList<String> playerCards, ArrayList<String> dealerCards) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.hitWin = hitWin;
        this.standWin = standWin;
        this.playerScore = playerScore;
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
    }

    public GameDataAccessObject getGameDataAccessObject() {
        return gameDataAccessObject;
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
