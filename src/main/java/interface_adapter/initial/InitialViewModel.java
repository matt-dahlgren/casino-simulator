package interface_adapter.initial;

import interface_adapter.ViewModel;

public class InitialViewModel extends ViewModel<InitialState> {
    public static final String APP_LABEL = "BLACKJACK";
    public static final String HIT_BUTTON_LABEL = "Hit";
    public static final String STAND_BUTTON_LABEL = "Stand";

    public InitialViewModel() {
        super("initial");
        setState(new InitialState());
    }
}
