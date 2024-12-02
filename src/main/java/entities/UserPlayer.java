package entities;

import java.util.ArrayList;

/**
 * The player controlled by the user.
 */
public class UserPlayer extends Player {
    private final ArrayList<Card> hand;

    public UserPlayer(ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * Returns hand.
     * @return hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Updates hand.
     * @param hand new hand
     */
    public void setHand(ArrayList<Card> hand) {

    }

    /**
     * Adds a card.
     * @param card object
     */
    public void add(Card card) {
        hand.add(card);
    }
}

