package interface_adapter.learn_mode;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Moves instruction view.
 */

public class DealingViewModel extends ViewModel<DealingState> {
    // public static final
    public DealingViewModel() {
        super("Dealing");
        setState(new DealingState());
    }
}