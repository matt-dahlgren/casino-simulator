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






    @Override
    public void prepareSuccessView(BlackjackOutputData outputData) {
        // Update the user's hand in the view model.
        blackjackViewModel.setHand(outputData.getUserHand());
        blackjackViewModel.setHandValue(outputData.getHandValue());

        // Notify the view that the hand has been updated.
        blackjackViewModel.firePropertyChanged("hand");
    }

    @Override
    public void prepareFailView(String error) {
        // Notify the view of the failure (e.g., user busted).
        blackjackViewModel.setErrorMessage(error);
        blackjackViewModel.firePropertyChanged("error");
    }
}
