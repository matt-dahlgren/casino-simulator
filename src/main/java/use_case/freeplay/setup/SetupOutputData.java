package use_case.freeplay.setup;

import java.util.ArrayList;

/**
 * Output Data for the Setup Use Case.
 *
 */
public class SetupOutputData {
    private final ArrayList<String> dealerHand;
    private final ArrayList<String> userPlayerHand;

    /**
     * The output data for Setup
     * @param dealerHand the list of all the image links for the images of the dealer's hand.
     * @param userPlayerHand the list of all the image links for the images of the userPlayer's hand.
     */
    public SetupOutputData(ArrayList<String> dealerHand, ArrayList<String> userPlayerHand) {
        this.dealerHand = dealerHand;
        this.userPlayerHand = userPlayerHand;
    }

    public ArrayList<String> getDealerHand() {return this.dealerHand;}

    public ArrayList<String> getUserPlayerHand() {return this.userPlayerHand;}

}
