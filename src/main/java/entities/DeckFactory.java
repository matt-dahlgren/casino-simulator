package entities;

public class DeckFactory implements DeckFactoryInterface {

    /**
     * @param deckID id of the current deck
     * @return Deck object
     */
    @Override
    public Deck createDeck(String deckID) {
        return new Deck(deckID);
    }
}
