package use_case.freeplay.hit;

public interface HitInputBoundary {
    void execute();

    void switchToHitView();

    void switchToDealerAfterStandView();

    void switchToMainMenuView();

    int getHandVal();

    void execute_setup();
}
