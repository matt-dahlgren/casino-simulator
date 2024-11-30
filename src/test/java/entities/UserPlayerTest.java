package entities;

import org.junit.Test;

import java.util.ArrayList;

public class UserPlayerTest {

    /**
     * Tests if the userPlayer can correctly have hand set.
     * This is not really a good test example.
     */
    @Test
    public void UserPlayerSetHandTest() {
        ArrayList<Card> hand = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();

        hand.add(new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));
        hand.add(new Card("A", "Spades", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));

        hand2.add(new Card("J", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));
        hand2.add(new Card("Q", "Spades", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));

        UserPlayer userPlayer = new UserPlayer(hand);
        userPlayer.setHand(hand2);

        assert(userPlayer.getHand().equals(hand2));
    }

    /**
     * Tests whether inherited player method getScore tabulates total value correctly
     * Does NOT set aces to equal to 1, that's the hit use case's responsibility.
     */
    @Test
    public void UserPlayerGetScoreTest() {
        ArrayList<Card> hand = new ArrayList<>();

        hand.add(new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));
        hand.add(new Card("A", "Spades", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));

        UserPlayer userPlayer = new UserPlayer(hand);
        assert(userPlayer.getScore() == 21);
    }

    /**
     * Tests whether inherited player method hit, draws a card correctly
     */
    @Test
    public void UserPlayerHitTest() {
        ArrayList<Card> hand = new ArrayList<>();

        hand.add(new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));
        hand.add(new Card("A", "Spades", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png"));
        Card card = new Card("10", "Hearts", "https://wiki.tf2classic.com/w/images/f/f4/Backpack_Sandvich.png");

        UserPlayer userPlayer = new UserPlayer(hand);
        userPlayer.hit(card);

        assert(userPlayer.getHand().size() == 3);
    }
}
