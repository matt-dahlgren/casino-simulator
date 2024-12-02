package entities;

import java.util.ArrayList;

/**
 * The dealer player.
 */
public class Dealer extends Player {
    private ArrayList<Card> hand;

    public Dealer(ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * Gets the hand from the dealer.
     * @return hand array
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Updates dealer hand.
     * @param newHand array
     */
    public void setHand(ArrayList<Card> newHand) {
        hand = newHand;
    }
}
