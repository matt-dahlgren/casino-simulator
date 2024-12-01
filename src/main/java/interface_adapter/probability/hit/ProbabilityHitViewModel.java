package interface_adapter.probability.hit;

import interface_adapter.ViewModel;

public class ProbabilityHitViewModel extends ViewModel<ProbabilityHitState> {

    public ProbabilityHitViewModel() {
        super("ProbabilityHit");
        this.setState(new ProbabilityHitState());
    }
}
