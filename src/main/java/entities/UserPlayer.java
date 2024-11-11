package entities;

import java.util.ArrayList;

/**
 * The player controlled by the user.
 */
public class UserPlayer extends Player {

    public UserPlayer(ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     * Carries out the player's turn.
     */
    public void doTurn() {
        //Allow to press button

        if ((getScore() > 21)) {
            bust();
        }

        }
    }

