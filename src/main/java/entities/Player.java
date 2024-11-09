package entities;

import java.util.ArrayList;

/**
 * A generic player (super)class.
 */
public class Player {
    private ArrayList<Card> hand = new ArrayList<Card>();

    public Player() {
    }

    /**
     * Carries out the player's turn.
     * @param deck the deck of remaining cards that can be drawn.
     */
    public void doTurn(ArrayList<Card> deck) {
    }

    /**
     * Allows the player to draw a card.
     */
    protected void hit(Card newCard) {
        hand.add(newCard);
    }

    /**
     * Ends the player's turn.
     */
    protected void stand() {
    }

    /**
     * Performs the appropriate action when the player's score exceeds 21.
     */
    protected void bust() {
    }

    /**
     * Gets the user's score (the sum of the values of all their cards).
     * @return the user's score.
     */
    public int getScore() {
        int score = 0;

        for (Card card : hand) {
            score += card.getValue();
        }

        return score;
    }
}