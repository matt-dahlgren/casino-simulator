package interface_adapter.initial;

import interface_adapter.ViewModel;

public class InitialViewModel extends ViewModel<InitialState> {
    public static final String TITLE_LABEL = "BLACKJACK";
    public static final String DESCRIPTION_LABEL = "Fanciful description";

    public static final String USELESS_BUTTON_LABEL = "I am currently Useless";

    public InitialViewModel() {
        super("initial");
        setState(new InitialState());
    }
}
