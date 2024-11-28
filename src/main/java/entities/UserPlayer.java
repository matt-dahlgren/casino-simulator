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

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {}
}

