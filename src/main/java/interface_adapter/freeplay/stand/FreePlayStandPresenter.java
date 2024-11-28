package interface_adapter.freeplay.stand;

import interface_adapter.ViewManagerModel;
import use_case.freeplay.stand.FreePlayStandOutputData;
import use_case.freeplay.stand.FreePlayStandOutputDataBoundary;

public class FreePlayStandPresenter implements FreePlayStandOutputDataBoundary {

    private final FreePlayStandViewModel freePlayStandViewModel;
    private final ViewManagerModel viewManagerModel;

    public FreePlayStandPresenter(ViewManagerModel viewManagerModel, FreePlayStandViewModel freePlayStandViewModel) {
        this.freePlayStandViewModel = freePlayStandViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareStandView(FreePlayStandOutputData outputData) {
        final FreePlayStandState freePlayStandState = FreePlayStandViewModel.getState();

    }
}
