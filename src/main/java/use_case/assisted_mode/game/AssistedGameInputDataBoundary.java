package use_case.assisted_mode.game;

public interface AssistedGameInputDataBoundary {

    /**
     * Execute the AssistedGame UseCase
     */
    void execute();

    /**
     * Pull a card, if is a bust go straight to exit page.
     */
    void assistedHit();

    /**
     * Stand, go to dealer screen to see if the player has won.
     */
    void assistedStand();

    /**
     * Go back to the main menu.
     */
    void mainMenu();
}
