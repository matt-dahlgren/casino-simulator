package interface_adapter.freePlay.hit;

import interface_adapter.ViewModel;
import interface_adapter.freePlay.hit.HitState;

import java.util.ArrayList;

public class HitViewModel extends ViewModel<HitState> {
    public HitViewModel() {
        super("hit");
        setState(new HitState());

    }
}
