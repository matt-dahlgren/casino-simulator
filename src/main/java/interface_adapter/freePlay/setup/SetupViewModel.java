package interface_adapter.freePlay.setup;

import interface_adapter.ViewModel;

public class SetupViewModel extends ViewModel<SetupState> {
    public String DEALER_ONE;
    public String DEALER_TWO;
    public String PLAYER_ONE;
    public String PLAYER_TWO;

    public SetupViewModel() {
        super("setup");
        DEALER_ONE = this.getState().getDealerCardOne();
        DEALER_TWO = this.getState().getDealerCardTwo();
        PLAYER_ONE = this.getState().getPlayerCardOne();
        PLAYER_TWO = this.getState().getPlayerCardTwo();
    }
}
