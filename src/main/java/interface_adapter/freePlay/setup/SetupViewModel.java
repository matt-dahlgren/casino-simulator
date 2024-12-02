package interface_adapter.freePlay.setup;

import interface_adapter.ViewModel;

public class SetupViewModel extends ViewModel<SetupState> {

    public SetupViewModel() {
        super("setup");
        setState(new SetupState());

    }
}
