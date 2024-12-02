package use_case.assisted_mode.game;

public interface AssistedGameOutputDataBoundary {

    void prepareAssistedView(AssistedGameOutputData data);

    void prepareAssistedStand(AssistedGameOutputData data);

    void prepareMainMenu();
}
