package use_case.freeplay.setup;

import entities.Dealer;
import entities.UserPlayer;

/**
 * The DAO for the Setup Use Case in the context of the GameDataAccessObject
 */
public interface SetupGameDataAccessInterface {

    /**
     * Sets the dealer in the DAO
     * @param dealer the dealer of the game
     */
    void setDealer(Dealer dealer);

    /**
     * Sets the userPlayer in the DAO
     * @param userPlayer the userPlayer of the game
     */
    void setPlayer(UserPlayer userPlayer);

    /**
     * Sets the deckID in the DAO
     * @param deckID the deckID of the game
     */
    void setDeckID(String deckID);
}
