package interface_adapter.freePlay.hit;

import java.util.ArrayList;

public class HitState {
    private ArrayList<String> playerCards;
    private ArrayList<String> dealerCards;

    public ArrayList<String> getPlayerHand() {
        return this.playerCards;
    }

    public ArrayList<String> getDealerHand() {
        return this.dealerCards;
    }

    public void setPlayerHand(ArrayList<String> playerHand) {
        this.playerCards = playerHand;
    }

    public void setDealerHand(ArrayList<String> dealerHand) {
        this.dealerCards = dealerHand;
    }

}
