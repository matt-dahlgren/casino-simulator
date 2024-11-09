package entities;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand = new ArrayList<Card>();

    public Player() {
    }

    public void doTurn() {
    }

    protected void hit() {
    }

    protected void stand() {
    }

    protected void bust() {
    }

    public int getScore() {
        int score = 0;

        for (Card card : hand) {
            score += card.getValue();
        }

        return score;
    }
}