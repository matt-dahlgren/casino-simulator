package interface_adapter.learn_mode;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Moves instruction view.
 */

public class MovesViewModel extends ViewModel<MovesState> {
    // public static final
    public MovesViewModel() {
        super("Moves");
        setState(new MovesState());
    }
}
