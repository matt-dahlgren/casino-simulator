package entities;

/**
 * Deck class. The actual cards are accessed via the API.
 */
public class Deck {
    private String deckID;

    public Deck(String deckID) {
        this.deckID = deckID;
    }

    /**
     * Gets the deck ID.
     * @return deck ID
     */
    public String getDeckID() {
        return deckID;
    }

    /**
     * Sets the deck ID.
     * @param deckID deck ID
     */
    public void setDeckID(String deckID) {
        this.deckID = deckID;
    }
}
