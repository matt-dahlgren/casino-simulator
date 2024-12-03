package use_case.signup;

import entities.User;
import entities.UserFactory;

import java.io.IOException;

public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDAO;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface userDAO, SignupOutputBoundary userPresenter, UserFactory userFactory) {
        this.userDAO = userDAO;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }


    /**
     * executes signup use case
     * if the username does not already exist, if passwords match, create user
     * @param signupInputData is the input data containing account info
     */
    @Override
    public void execute(SignupInputData signupInputData) throws IOException {
        if (userDAO.userExists(signupInputData.getUsername())) {
            //fail view, username already exists
            userPresenter.prepareFailView("User already exists");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            //fail view, passwords don't match
            userPresenter.prepareFailView("Passwords do not match");
        }
        else {
            //create User
            final User user = userFactory.create(signupInputData.getUsername(),
                    signupInputData.getEmail(), signupInputData.getPassword());
            userDAO.addUser(user);
            //prepare success view
            final SignupOutputData outputData = new SignupOutputData(user.getUsername());
            userPresenter.prepareSuccessView(outputData);
        }


    }
}
