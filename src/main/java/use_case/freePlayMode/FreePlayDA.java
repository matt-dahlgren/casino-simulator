package use_case.freePlayMode;


import entities.Card;

/**
 * Data access interface for freeplay mode
 */
public interface FreePlayDA {

    Card getCard(String deckID);

    String getDeckID();

    Boolean shuffleExistingDeck(String deckID);

}
