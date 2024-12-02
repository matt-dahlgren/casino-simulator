package interface_adapter.freePlay.setup;

import java.util.ArrayList;

public class SetupState {
    private ArrayList<String> DealerHand;
    private ArrayList<String> PlayerHand;

    public ArrayList<String> getPlayerHand() {
        return PlayerHand;
    }

    public ArrayList<String> getDealerHand() {
        return DealerHand;
    }

    public void setDealerHand(ArrayList<String> DealerHand) {
        this.DealerHand = DealerHand;
    }

    public void setPlayerHand(ArrayList<String> PlayerHand) {
        this.PlayerHand = PlayerHand;
    }
}
