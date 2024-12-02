package interface_adapter.dealer_screen;

import interface_adapter.ViewModel;

public class DealerScreenViewModel extends ViewModel<DealerScreenState> {

    public DealerScreenViewModel() {
        super("stand");
        setState(new DealerScreenState());
    }
}
