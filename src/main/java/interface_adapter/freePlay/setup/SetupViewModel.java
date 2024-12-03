package interface_adapter.freeplay.setup;

import interface_adapter.ViewModel;
import interface_adapter.freeplay.setup.SetupState;

public class SetupViewModel extends ViewModel<SetupState> {

    public SetupViewModel() {
        super("setup");
        setState(new SetupState());

    }
}
