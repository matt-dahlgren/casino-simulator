import api.CardDeck;
import entities.Card;
import entities.Deck;
import entities.DeckFactory;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    @Test
    void testGetDeckID() {
        CardDeck deck = new CardDeck();
        final String deckID = deck.getDeckID();

        System.out.println("Deck ID: " + deckID);
    }

    @Test
    void testDrawCard() {
        CardDeck deck = new CardDeck();
        DeckFactory df = new DeckFactory();
        Deck currentDeck = df.createDeck(deck.getDeckID());

        Card newCard = deck.drawCard(currentDeck.getDeckID());

        assert(newCard != null);

    }
}
