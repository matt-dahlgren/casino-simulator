package interface_adapter.login_adapter;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * The controller for the Login Use Case.
 */
public class LoginController {
    private final LoginInputBoundary loginInputBoundary;

    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }

    /**
     * Executes the Login Use Case.
     *
     * @param username the username of the user that is logging in.
     * @param password the password of the user that is logging in.
     */
    public void execute(String username, String password) {
        final LoginInputData loginInputData = new LoginInputData(username, password);

        loginInputBoundary.execute(loginInputData);
    }
}

