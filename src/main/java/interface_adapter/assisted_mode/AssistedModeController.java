package interface_adapter.assisted_mode;

import use_case.assisted_mode.game.AssistedGameInputDataBoundary;

/**
 * Controller for the AssistedMode UseCase
 */
public class AssistedModeController {

    private final AssistedGameInputDataBoundary assistedGameInputDataBoundary;

    public AssistedModeController(AssistedGameInputDataBoundary assistedGameInputDataBoundary) {
        this.assistedGameInputDataBoundary = assistedGameInputDataBoundary;
    }

    /**
     * Execute the assistedmode use case
     */
    public void execute() {
        assistedGameInputDataBoundary.execute();
    }

    /**
     * Execute the use case of a player hitting in an assisted game mode.
     */
    public void assistedHit() {
        assistedGameInputDataBoundary.assistedHit();
    }

    /**
     * Exectute the use case of a Player standing in an assisted game mode.
     */
    public void assistedStand() {
        assistedGameInputDataBoundary.assistedStand();
    }

    /**
     * Return to main menu.
     */
    public void mainMenu() {
        assistedGameInputDataBoundary.mainMenu();
    }
}
