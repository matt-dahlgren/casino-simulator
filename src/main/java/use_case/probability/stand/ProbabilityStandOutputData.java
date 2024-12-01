package use_case.probability.stand;

public class ProbabilityStandOutputData {

    private final int standWinProbability;

    public ProbabilityStandOutputData(int standWinProbability) {
        this.standWinProbability = standWinProbability;
    }

    public int getStandWinProbability() {
        return standWinProbability;
    }
}
