package interface_adapter.probability.stand;

import interface_adapter.ViewModel;

public class ProbabilityStandViewModel extends ViewModel<ProbabilityStandState> {

    public ProbabilityStandViewModel() {
        super("ProbabilityStand");
        this.setState(new ProbabilityStandState());
    }
}
