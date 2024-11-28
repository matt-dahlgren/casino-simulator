package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * The dealer player.
 */
public class Dealer extends Player {
    private final ArrayList<Card> hand;

    public Dealer(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {}
}
