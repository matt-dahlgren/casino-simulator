package interface_adapter.initial;

import interface_adapter.ViewModel;

public class InitialViewModel extends ViewModel<InitialState> {
    public static final String TITLE_LABEL = "Insert Title Label";
    public static final String DESCRIPTION_LABEL = "This was originally a to be called 'Default View.'";

    public static final String USELESS_BUTTON_LABEL = "I do Nothing";

    public InitialViewModel() {
        super("initial");
        setState(new InitialState());
    }
}
