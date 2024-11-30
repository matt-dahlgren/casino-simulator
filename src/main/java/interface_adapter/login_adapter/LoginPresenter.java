package interface_adapter.login_adapter;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import view.ViewManager;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final MainMenuViewModel mainMenuViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          MainMenuViewModel mainMenuViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        final MainMenuState mainMenuState = mainMenuViewModel.getState();
        mainMenuState.setUsername(response.getUsername());
        this.mainMenuViewModel.setState(mainMenuState);
        this.mainMenuViewModel.firePropertyChanged();

        this.viewManagerModel.setState(mainMenuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
