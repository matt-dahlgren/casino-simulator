package entities;

import org.junit.Test;

import java.util.Objects;

public class CardTest {

    /**
     * Tests if the card will return the correct rank by default.
     */
    @Test
    public void RankTest() {
        Card card = new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png");
        assert(card.getRank().equals("10"));
    }

    /**
     * Tests if the card will return the correct suit by default.
     * Remove when if/when we remove the suit.
     */
    @Test
    public void SuitTest() {
        Card card = new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png");
        assert (Objects.equals(card.getSuit(), "Hearts"));
    }

    /**
     * Tests if the card will return the correct value by default when input is an ACE.
     */
    @Test
    public void ValueATest() {
        Card card = new Card("A", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png");
        assert (card.getValue() == 11);
    }

    /**
     * Tests if the card will return the correct value by default when input is a JACK, QUEEN, or KING.
     */
    @Test
    public void ValueJTest() {
        Card card = new Card("J", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png");
        assert (card.getValue() == 10);
    }

    /**
     * Tests if the card will return the correct value by default when input is a number rank.
     */
    @Test
    public void ValueIntTest() {
        Card card = new Card("5", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png");
        assert (card.getValue() == 5);
    }

    /**
     * Tests if the card's value will be correctly changed if an Ace's value is set to 1 from 11.
     */
    @Test
    public void SetValueSuccessTest() {
        Card card = new Card("A", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png");
        card.setValue(1);
        assert (card.getValue() == 1);
    }

    /**
     * Tests if the card's value will not be changed if it is not an Ace, keeping it at its previous value.
     */
    @Test
    public void SetValueFailureTest() {
        Card card = new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png");
        card.setValue(1);
        assert (card.getValue() == 10);
    }

    /**
     * Tests if the card's visibility is corrected changed by setVisible.
     */
    @Test
    public void VisibilityTest() {
        Card card = new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png");
        card.setVisible(false);
        assert (!card.isVisible());
    }

    /**
     * Tests if the image is correctly stored and returned.
     */
    @Test
    public void ImageTest() {
        Card card = new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png");
        assert (Objects.equals(card.getImage(), "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));
    }
}
