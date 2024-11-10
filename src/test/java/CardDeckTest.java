import api.CardDeck;
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
        int numCards = 2;
        CardDeck deck = new CardDeck();
        deck.drawCard(numCards);

        // TODO: Make sure we've gotten two cards back
    }
}
