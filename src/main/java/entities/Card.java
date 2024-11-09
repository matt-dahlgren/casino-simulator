package entities;

/**
 * Represents a card with a rank, suit, value, and visibility status.
 */
public class Card {
    private String rank;
    private String suit;
    private int value;
    private boolean isVisible;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        isVisible = false;

        if (rank == "J" || rank == "Q" || rank == "K") {
            value = 10;
        }
        else if (rank == "A") {
            value = 11;
        }
        else {
            value = Integer.parseInt(rank);
        }
    }

    /**
     * Get the card's rank (9, 10, J, Q, etc.).
     * @return The card's rank.
     */
    public String getRank() {
        return rank;
    }

    /**
     * Get the card's suit.
     * @return The card's suit.
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Get the value of the card (9, 10, 11, etc.).
     * @return The card's value.
     */
    public int getValue() { return value; }

    /**
     * Determine if the card's information should be visible to the player.
     * @return Whether the card's information should be visible.
     */
    public boolean isVisible() { return isVisible; }

    /**
     * Allows the value of an ace to be switched to 1 or 11.
     * @param newValue The new value of the ace.
     */
    public void setValue(int newValue) {
        if (rank == "A" && (newValue == 11 || newValue == 1)) {
            value = newValue;
        }
    }

    /**
     * Sets the visibility status of the card.
     * @param newValue represents whether the card should be visibile to the user.
     */
    public void setVisible(boolean newValue) { isVisible = newValue; }
}
