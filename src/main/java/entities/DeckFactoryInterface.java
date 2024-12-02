package entities;

/**
 * Facilitates implementation of deck factory.
 */
public interface DeckFactoryInterface {
    /**
     * Creates a new deck object.
     * @param deckID ID
     * @return deck
     */
    Deck createDeck(String deckID);
}
