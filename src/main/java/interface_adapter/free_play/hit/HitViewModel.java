package interface_adapter.free_play.hit;

import interface_adapter.ViewModel;

import java.util.ArrayList;

public class HitViewModel extends ViewModel<HitState> {
    public HitViewModel() {
        super("hit");
        setState(new HitState());

    }
}
