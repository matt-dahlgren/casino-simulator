package interface_adapter.freePlay.hit;

import interface_adapter.ViewModel;
import interface_adapter.freePlay.hit.HitState;

import java.util.ArrayList;

public class HitViewModel extends ViewModel<HitState> {
    public ArrayList<String> PLAYER_HAND;
    public ArrayList<String> DEALER_HAND;
    public HitViewModel() {
        super("hit");
        PLAYER_HAND = this.getState().getPlayerHand();
        DEALER_HAND = this.getState().getDealerHand();
    }
}
