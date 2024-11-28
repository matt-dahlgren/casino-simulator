package interface_adapter.probability.hit;

/**
 * The state for the ProbabiltyHit portion of ProbabiltyView.
 */
public class ProbabilityHitState {
    private int hitProbability;

    public int getStandProbability() {
        return hitProbability;
    }

    public void setStandProbability(int standProbability) {
        this.hitProbability = standProbability;
    }
}
