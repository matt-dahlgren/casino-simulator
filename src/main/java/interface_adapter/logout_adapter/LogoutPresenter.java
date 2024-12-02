package interface_adapter.logout_adapter;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_adapter.LoginPresenter;
import interface_adapter.login_adapter.LoginState;
import interface_adapter.login_adapter.LoginViewModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;
import view.MainMenuView;

public class LogoutPresenter implements LogoutOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private MainMenuViewModel mainMenuViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                           MainMenuViewModel mainMenuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.mainMenuViewModel = mainMenuViewModel;
    }

    /**
     * Prepares the success view for the Login Use Case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(LogoutOutputData outputData) {
        final MainMenuState mainMenuState = mainMenuViewModel.getState();
        mainMenuState.setUsername(outputData.getUsername());
        mainMenuViewModel.setState(mainMenuState);
        mainMenuViewModel.firePropertyChanged();

        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(outputData.getUsername());
        loginState.setPassword("");
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

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
