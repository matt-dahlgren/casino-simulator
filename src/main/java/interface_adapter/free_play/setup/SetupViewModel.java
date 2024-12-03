package interface_adapter.free_play.setup;

import interface_adapter.ViewModel;

public class SetupViewModel extends ViewModel<SetupState> {

    public SetupViewModel() {
        super("setup");
        setState(new SetupState());

    }
}
