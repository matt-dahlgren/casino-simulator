package use_case.assisted_mode.hit;

import data_access.GameDataAccessObject;
import entities.Card;
import use_case.assisted_mode.hit.probability.ProbabilityHitCalculator;
import use_case.assisted_mode.hit.probability.ProbabilityStandCalculator;

import java.util.ArrayList;

/**
 * Interactor for the Hit sub-UseCase of the Assisted_Mode
 */
public class AssistedHitInteractor implements AssistedHitInputDataBoundary{

    private final GameDataAccessObject gameDataAccessObject;
    private final AssistedHitOutputDataBoundary assistedHitOutputDataBoundary;

    public AssistedHitInteractor(GameDataAccessObject gameDataAccessObject,
                                 AssistedHitOutputDataBoundary assistedHitOutputDataBoundary) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.assistedHitOutputDataBoundary = assistedHitOutputDataBoundary;
    }
    @Override
    public void execute() {
        ProbabilityHitCalculator hitCalculator = new ProbabilityHitCalculator(gameDataAccessObject);
        ProbabilityStandCalculator standCalculator = new ProbabilityStandCalculator(gameDataAccessObject);
        int handscore = standCalculator.handScore(standCalculator.getUserCards());
        AssistedHitOutputData outputData = new AssistedHitOutputData(hitCalculator.execute(),
                standCalculator.execute(), handscore);
        assistedHitOutputDataBoundary.prepareAssistedHitView(outputData);

        ArrayList<String> playerVisibleCards = new ArrayList<>();
        ArrayList<String> dealerVisibleCards = new ArrayList<>();

        for (Card card: gameDataAccessObject.getPlayer().getHand()) {
            playerVisibleCards.add(card.getImage());
        }

        for (Card card: gameDataAccessObject.getDealer().getHand()) {
            if (card.isVisible()) {
                dealerVisibleCards.add(card.getImage());
            }
            else {
                dealerVisibleCards.add("https://deckofcardsapi.com/static/img/back.png");
            }
        }
    }
}
