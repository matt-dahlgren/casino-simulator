package use_case.freeplay.setup;


import entities.Card;
import entities.Dealer;
import entities.UserPlayer;

import java.util.ArrayList;

/**
 * Output Data for the Setup Use Case.
 */
public class SetupOutputData {
    private final ArrayList<String> dealerHand;
    private final ArrayList<String> userPlayerHand;

    public SetupOutputData(ArrayList<String> dealerHand, ArrayList<String> userPlayerHand) {
        this.dealerHand = dealerHand;
        this.userPlayerHand = userPlayerHand;
    }

    public ArrayList<String> getDealerHand() {return this.dealerHand;}

    public ArrayList<String> getUserPlayer() {return this.userPlayerHand;}

}
