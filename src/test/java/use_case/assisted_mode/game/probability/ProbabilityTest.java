package use_case.assisted_mode.game.probability;

import data_access.GameDataAccessObject;
import entities.Card;
import entities.Dealer;
import entities.Player;
import entities.UserPlayer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProbabilityTest {

    public static String deckID = "Filler";
    public static ArrayList<Player> computers = new ArrayList<>();
    @Test
    public void testWinProbability() {
        ArrayList<Card> hand = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();
        hand.add(new Card("10", "Hearts", "https://deckofcardsapi.com/static/img/back.png"));
        hand.add(new Card("ACE", "Spades", "https://deckofcardsapi.com/static/img/back.png"));

        hand2.add(new Card("JACK", "Hearts", "https://deckofcardsapi.com/static/img/back.png"));
        hand2.add(new Card("QUEEN", "Spades", "https://deckofcardsapi.com/static/img/back.png"));

        UserPlayer playerOne = new UserPlayer(hand);
        Dealer dealerOne = new Dealer(hand);

        GameDataAccessObject gameTest = new GameDataAccessObject(playerOne, dealerOne,deckID, computers);
        ProbabilityStandCalculator testCalculator = new ProbabilityStandCalculator(gameTest);

        int probability = testCalculator.standProbability();

        System.out.println("Win probability for Player One: " + probability);
        assertTrue(probability == 100);
    }
}
