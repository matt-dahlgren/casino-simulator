package entities;

import java.util.ArrayList;

/**
 * The dealer player.
 */
public class Dealer extends Player {
    public Dealer() {
    }

    /**
     * Carries out the dealer's turn (the dealer hits until their score reaches 17, then stands).
     * @param deck the deck of remaining cards that can be drawn.
     */
    public void doTurn(ArrayList<Card> deck) {
        while (getScore() < 17) {
            hit(deck.remove(0));
        }

        if (getScore() >= 17) {
            bust();
        }

        stand();
    }
}
