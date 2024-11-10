package entities;

/**
 * Deck class. The actual cards are accessed via the API.
 */
public class Deck {
    private String deckID;

    public Deck(String deckID) {
        this.deckID = deckID;
    }

    public String getDeckID() {
        return deckID;
    }

    public void setDeckID(String deckID) {
        this.deckID = deckID;
    }
}
