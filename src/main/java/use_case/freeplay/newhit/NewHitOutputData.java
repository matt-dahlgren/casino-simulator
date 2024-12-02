package use_case.freeplay.newhit;

import java.util.ArrayList;

/**
 * Output Data for the Hit Use Case.
 */

public class NewHitOutputData {
    private final ArrayList<String> userPlayerHand;

    /**
     * The output data for Setup
     * @param userPlayerHand the list of all the image links for the images of the userPlayer's hand.
     */
    public NewHitOutputData(ArrayList<String> userPlayerHand) {
        this.userPlayerHand = userPlayerHand;
    }


    public ArrayList<String> getUserPlayerHand() {return this.userPlayerHand;}

}