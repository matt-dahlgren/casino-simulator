package use_case.gameplay_use_cases.start_game;

import java.util.List;

/**
 * The output boundary for the start game use case.
 */
public interface Start_GameOutputBoundary {
    /**
     * Called when game succesfully started.
     * This method provides the game state information to the presenter or UI for display.
     *
     * @param players The list of players participating in the game
     * @param dealer The dealer for the game
     * @param deckID The ID of the deck used in the game
     */
    void showGameStartedMessage(List<String> players, String dealer, String deckID);

    /**
     * Called when there is an error starting game
     * @param errorMessage Error message description
     */
    void showErrorMessage(String errorMessage);

    /**
     * Called to show the initial game setup
     *
     * @param deckID the ID of the deck used
     * @param initialHands is the initial hands of each player
     */
    void showInitialSetup(String deckID, List<>);
}
