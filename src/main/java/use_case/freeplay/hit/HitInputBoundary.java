package use_case.freeplay.hit;

import use_case.freeplay.setup.SetupOutputData;

public interface HitInputBoundary {
    void execute();

    void switchToHitView();

    void executeSetuptoHit();

    void switchToMainMenuView();

}
