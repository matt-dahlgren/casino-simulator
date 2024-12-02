package entities;

/**
 * Deck factory.
 */
public class DeckFactory implements DeckFactoryInterface {

    /**
     * Creates the deck.
     * @param deckID id of the current deck
     * @return Deck object
     */
    @Override
    public Deck createDeck(String deckID) {
        return new Deck(deckID);
    }
}
