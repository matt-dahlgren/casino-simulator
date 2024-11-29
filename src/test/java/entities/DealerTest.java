package entities;

import org.junit.Test;

import java.util.ArrayList;

public class DealerTest {

    /**
     * Tests if the dealer can correctly have hand set.
     * This is not really a good test example.
     */
    @Test
    public void DealerSetHandTest() {
        ArrayList<Card> hand = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();

        hand.add(new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));
        hand.add(new Card("A", "Spades", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));

        hand2.add(new Card("J", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));
        hand2.add(new Card("Q", "Spades", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));

        Dealer dealer = new Dealer(hand);
        dealer.setHand(hand2);

        assert(dealer.getHand().equals(hand2));
    }

    /**
     * Tests whether inherited player method getScore tabulates total value correctly
     * Does NOT set aces to equal to 1, that's the hit use case's responsibility.
     */
    @Test
    public void DealerGetScoreTest() {
        ArrayList<Card> hand = new ArrayList<>();

        hand.add(new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));
        hand.add(new Card("A", "Spades", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));

        Dealer dealer = new Dealer(hand);
        assert(dealer.getScore() == 21);
    }

    /**
     * Tests whether inherited player method hit, draws a card correctly
     */
    @Test
    public void DealerHitTest() {
        ArrayList<Card> hand = new ArrayList<>();

        hand.add(new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));
        hand.add(new Card("A", "Spades", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));
        Card card = new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png");

        Dealer dealer = new Dealer(hand);
        dealer.hit(card);

        assert(dealer.getHand().size() == 3);
    }
}
