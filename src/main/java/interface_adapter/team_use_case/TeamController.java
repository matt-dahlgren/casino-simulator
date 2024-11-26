package interface_adapter.team_use_case;
import use_case.freeplaymode.functioninputboundary;
import use_case.freeplaymode.functioninputdata;

/**
 * The controller for the Team Use Case (free play mode).
 */

public class TeamController {
    private final FunctionInputBoundary functionInteractor;

    public TeamController(FunctionInputBoundary functionInteractor) {
        this.functionInteractor = functionInteractor;
    }

    /**
     * Executes the Free Play Use Case.
     * @param userHand
     * @param userHandValue
     * @param dealerHand
     * @param dealerHandValue
     */
    public void execute(String input1, String input2) {
        final FunctionInputData functionInputData = new FunctionInputData(input1, input2);

        functionInteractor.execute(functionInputData);
    }
}