package use_case.probability.hit;

public class ProbabilityHitOutputData {

    public final int hitWinProbability;

    ProbabilityHitOutputData(int hitWinProbability) {
        this.hitWinProbability = hitWinProbability;
    }

    public int getHitWinProbability() {
        return hitWinProbability;
    }
}
