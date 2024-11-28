package use_case.freePlayMode;

public interface FreePlayOutputBoundary {

    void prepareStartView(FreePlayOutputData outputData);

    void prepareSuccessHitView(FreePlayOutputData outputData);

    void prepareSuccessStandView(FreePlayOutputData outputData);

    void prepareFailView(String errorMessage);
}
