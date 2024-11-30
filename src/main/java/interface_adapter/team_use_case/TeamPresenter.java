package interface_adapter.team_use_case;

// import use_case.team_usecase.OutputBoundary;
// import use_case.team_usecase.OutputData;

/**
 * The Presenter for the Team Use Case (free play mode).
 */

public class TeamPresenter implements OutputBoundary {

    private final TeamViewModel teamViewModel;

    public TeamPresenter(TeamViewModel teamViewModel) {
        this.teamViewModel = teamViewModel;
    }

    @Override
    public void prepareSuccessView(OutputData outputData) {
        // Used to generally update the player's hand and the dealer's hand based on movements

        // Update Player hand on hit
        teamViewModel.setPlayerHand(outputData.getPlayerHand());
        // Update Player hand value on hit
        teamViewModel.setPlayerHandValue(outputData.getPlayerHandValue());
        teamViewModel.firePropertyChanged("playerhand");

        if (outputData.getDealerHand() != null) {
        // Reveal dealer card on stand (update Dealer hand on hit)
        teamViewModel.setDealerHand(outputData.getDealerHand());
        // Update Dealer hand value on stand
        teamViewModel.setDealerHandValue(outputData.getDealerHandValue());
        teamViewModel.firePropertyChanged("dealerhand");
        }

    @Override
    public void prepareFailView(String bust) {
        // Used for error state when player busts
            teamViewModel.setBustMessage(bust);
            teamViewModel.firePropertyChanged("bust");
        }
}




    }

