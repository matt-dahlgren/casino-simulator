package use_case.freeplay.setup;

import entities.Card;

/**
 * DAO for Setup Use Case.
 */
public interface SetupDeckDataAccessInterface {

    /**
     * Draws a card from the API deck.
     * @return card drawn from the API deck.
     */
    Card getCard();

    /**
     * Provides the DeckID from the API deck.
     * @return the deckID as a string.
     */
    String getDeckID();

}
