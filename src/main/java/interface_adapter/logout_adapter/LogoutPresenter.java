package interface_adapter.logout_adapter;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_adapter.LoginPresenter;
import interface_adapter.login_adapter.LoginViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

public class LogoutPresenter implements LogoutOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Prepares the success view for the Login Use Case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(LogoutOutputData outputData) {

    }

    /**
     * Prepares the failure view for the Login Use Case.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {

    }
}
