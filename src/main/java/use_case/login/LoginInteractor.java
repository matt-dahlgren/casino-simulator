package use_case.login;

import data_access.AccountInfoDAO;
import entities.User;

/**
 * Log in interactor
 */
public class LoginInteractor implements LoginInputBoundary{
    private final AccountInfoDAO userDAO;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(AccountInfoDAO userDAO, LoginOutputBoundary loginPresenter) {
        this.userDAO = userDAO;
        this.loginPresenter = loginPresenter;
    }


    /**
     * Executes the login use case.
     *
     * @param loginInputData the input data
     */
    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (!userDAO.userExists(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDAO.getUser(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User user = userDAO.getUser(loginInputData.getUsername());

                userDAO.setCurrentUser(user.getUsername());
                final LoginOutputData loginOutputData = new LoginOutputData(user.getUsername());
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}
