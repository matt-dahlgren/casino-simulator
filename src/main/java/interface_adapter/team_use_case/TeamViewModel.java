package interface_adapter.team_use_case;

import interface_adapter.ViewModel;

public class TeamViewModel extends ViewModel<TeamState> {
    public static final String APP_LABEL = "BLACKJACK";
    public static final String HIT_BUTTON_LABEL = "Hit";
    public static final String STAND_BUTTON_LABEL = "Stand";

    public TeamViewModel() {
        super("team_use_case");
        setState(new TeamState());
    }
}
