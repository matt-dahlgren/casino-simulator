package use_case.freeplay.hit;

public interface HitInputBoundary {
    void execute();

    void switchToHitView();

    void executeSetuptoHit();

    void switchToDealerAfterStandView();

    void switchToMainMenuView();

}
