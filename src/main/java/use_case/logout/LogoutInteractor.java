package use_case.logout;

import data_access.AccountInfoDAO;

public class LogoutInteractor implements LogoutInputBoundary{
    private AccountInfoDAO userDataAccessObject;
    private LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(AccountInfoDAO userDataAccessInterface,
                           LogoutOutputBoundary logoutOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.logoutPresenter = logoutOutputBoundary;
    }

    /**
     * Executes the Logout use case.
     *
     * @param logoutInputData the input data
     */
    @Override
    public void execute(LogoutInputData logoutInputData) {
        final String username = logoutInputData.getUser();
        userDataAccessObject.setCurrentUser(null);
        final LogoutOutputData logoutOutputData = new LogoutOutputData("");
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }
}
