package api;

import org.json.JSONException;

import entities.Card;
import enitites.Dealer;
import edntities.Player;

/**
 * DeckofCards is an interface that defines the methods that the CardDeck class must implement.
 */
public interface DeckofCards {
    /**
     * A method that draws a card from the deck in play.
     * @param player is a valid player.
     * @return the Card pulled from the deck in play.
     */
    Card drawCard();

    /**
     * A method that creates a pile to act as the player's hand.
     * @param card is a valid Card.
     * @param player is a valid Player.
     * @return True if the card(s) have been added to the player's pile successfully.
     */
    boolean addtoHand(Player player, Card card);

    /**
     * A method that creates a pile to act as the dealer's hand.
     * @param dealer is a valid Dealer
     * @param card is a valid Card.
     * @return
     */
    boolean addtoDealerHand(Dealer dealer, Card card);

    /**
     * A method that shuffles the deck in play.
     * @return True if and only if the deck shuffle is successful.
     */
    boolean shuffleDeck();

    /**
     * A method that returns all hands back to the deck to be reshuffled.
     * @return True if and only if the deck has been reset and is shuffled.
     */
    boolean returnHandstoDeck();
}