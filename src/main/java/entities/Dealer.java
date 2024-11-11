package entities;

import java.util.ArrayList;
import java.util.Random;

/**
 * The dealer player.
 */
public class Dealer extends Player {

    public Dealer(ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     * Carries out the dealer's turn (the dealer hits until their score reaches 17, then stands).
     *
     * @param deck the deck of remaining cards that can be drawn.
     */
    public void doTurn(ArrayList<Card> deck) {
        while (getScore() < 17) {
            Random random = new Random();
            int size = random.nextInt(this.deck.size());
            Card card = this.deck.get(size);
            this.deck.remove(size);
            hit(card);
            System.out.println("Dealer Played" + card);
        }
    }
}
