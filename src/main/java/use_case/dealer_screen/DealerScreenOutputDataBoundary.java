package use_case.dealer_screen;

public interface DealerScreenOutputDataBoundary {

    /**
     * Prepares for the Exit View.
     */
    void prepareStandView(DealerScreenOutputData outputData);

    /**
     * Send view to main menu.
     */
    void toMainView();
}
