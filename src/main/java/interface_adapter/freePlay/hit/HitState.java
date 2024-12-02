package interface_adapter.freeplay.hit;

import java.util.ArrayList;

public class HitState {
    private ArrayList<String> playerCards= new ArrayList<>();

    public HitState(ArrayList<String> playerCards) {
        this.playerCards = playerCards;
    }

    public ArrayList<String> getPlayerHand() {
        return this.playerCards;
    }

    public void setPlayerHand(ArrayList<String> playerHand) {
        this.playerCards = playerHand;
    }
}
