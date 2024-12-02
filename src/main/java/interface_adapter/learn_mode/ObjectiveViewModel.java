package interface_adapter.learn_mode;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Objectives instruction view.
 */

public class ObjectiveViewModel extends ViewModel<ObjectiveState> {
    // public static final
    public ObjectiveViewModel() {
        super("Objective");
        setState(new ObjectiveState());
    }
}