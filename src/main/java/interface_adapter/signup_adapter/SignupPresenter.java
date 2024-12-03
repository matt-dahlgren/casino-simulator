package interface_adapter.signup_adapter;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_adapter.LoginState;
import interface_adapter.login_adapter.LoginViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(SignupViewModel signupViewModel, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * success view
     * @param outputData username
     */
    @Override
    public void prepareSuccessView(SignupOutputData outputData) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(outputData.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    /**
     * Fail view
     * @param errorMessage is the string saying what error happened
     */
    @Override
    public void prepareFailView(String errorMessage) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(errorMessage);
        signupViewModel.firePropertyChanged();

    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
