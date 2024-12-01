package interface_adapter.freeplay.stand;

import interface_adapter.ViewModel;

public class FreePlayStandViewModel extends ViewModel<FreePlayStandState> {

    public FreePlayStandViewModel() {
        super("stand");
        setState(new FreePlayStandState());
    }
}
