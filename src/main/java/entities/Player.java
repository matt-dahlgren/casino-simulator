package entities;

import java.util.ArrayList;

/**
 * A generic player (super)class.
 */
public class Player {
    private final ArrayList<Card> hand = new ArrayList<Card>();
    ArrayList<Card> deck = new ArrayList<>();

    public Player() {
    }

    /**
     * Carries out the player's turn. // Moved deck param to the players' attributes.
     */
    public void doTurn() {
    }

    /**
     * Allows the player to draw a card.
     */
    public void hit(Card newCard) {
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
        System.out.println("Bust");
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