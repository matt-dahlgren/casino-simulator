package interface_adapter.freeplay.hit;

import interface_adapter.ViewModel;
import interface_adapter.freeplay.hit.HitState;

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
