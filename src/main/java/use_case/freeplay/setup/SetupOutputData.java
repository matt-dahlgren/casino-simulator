package use_case.freeplay.setup;


import entities.Card;
import entities.Dealer;
import entities.UserPlayer;

import java.util.ArrayList;

/**
 * Output Data for the Setup Use Case.
 */
public class SetupOutputData {
    private final Dealer dealer;
    private final UserPlayer userPlayer;

    public SetupOutputData(Dealer dealer, UserPlayer userPlayer) {
        this.dealer = dealer;
        this.userPlayer = userPlayer;
    }

    public Dealer getDealer() {return this.dealer;}

    public UserPlayer getUserPlayer() {return this.userPlayer;}

}
