import api.CardDeck;
import entities.Card;
import entities.Deck;
import entities.DeckFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class CardDeckTest {
    @Test
    void testGetDeckID() {
        CardDeck deck = new CardDeck();
        final String deckID = deck.getNewDeckID();

        System.out.println("Deck ID: " + deckID);
    }

    @Test
    void testDrawCard() {
        CardDeck deck = new CardDeck();
        DeckFactory df = new DeckFactory();
        Deck currentDeck = df.createDeck(deck.getNewDeckID());

        Card newCard = deck.drawCard(currentDeck.getDeckID());

        assert(newCard != null);

    }

    @Test
    void testShuffleDeck() {
        CardDeck deck = new CardDeck();
        DeckFactory df = new DeckFactory();
        Deck currentDeck = df.createDeck(deck.getNewDeckID());

        Assertions.assertTrue(deck.shuffleDeck(currentDeck.getDeckID()));
    }
}
