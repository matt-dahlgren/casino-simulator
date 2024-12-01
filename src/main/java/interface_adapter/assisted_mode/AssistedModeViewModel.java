package interface_adapter.assisted_mode;

import interface_adapter.ViewModel;

public class AssistedModeViewModel extends ViewModel<AssistedModeState> {

    public AssistedModeViewModel() {
        super("assisted_mode");
        setState(new AssistedModeState());
    }
}
