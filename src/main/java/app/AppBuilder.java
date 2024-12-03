package app;

import data_access.APIDataAccessObject;
import data_access.GameDataAccessObject;
import entities.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.dealer_screen.DealerScreenViewModel;
import interface_adapter.freePlay.newhit.NewHitController;
import interface_adapter.freeplay.newhit.NewHitPresenter;
import interface_adapter.freeplay.setup.SetupController;
import interface_adapter.freeplay.setup.SetupViewModel;
import interface_adapter.learn_mode.*;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.report.ReportViewModel;
import interface_adapter.signup_adapter.*;
import interface_adapter.login_adapter.*;
import interface_adapter.dealer_screen.*;
import interface_adapter.freeplay.*;
import use_case.dealer_screen.DealerScreenInputBoundary;
import use_case.dealer_screen.DealerScreenInteractor;
import use_case.dealer_screen.DealerScreenOutputDataBoundary;
import use_case.learn_mode.LearnModeInputBoundary;
import use_case.learn_mode.LearnModeInteractor;
import use_case.learn_mode.LearnModeOutputBoundary;
import use_case.signup.*;
import use_case.login.*;
import use_case.freeplay.newhit.NewHitInputBoundary;
import use_case.freeplay.newhit.NewHitInteractor;
import use_case.freeplay.newhit.NewHitOutputBoundary;
import use_case.freeplay.setup.SetupInputBoundary;
import use_case.freeplay.setup.SetupInteractor;
import use_case.freeplay.setup.SetupOutputBoundary;
import use_case.signup.SignupInteractor;
import view.*;

import data_access.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.nio.channels.SeekableByteChannel;
import entities.*;

/**
 *
 */

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final ImageIcon img = new ImageIcon("resources/images/icon.png");

    // Entities
    private final UserPlayer userPlayer = new UserPlayer(); // TODO Where do I get the arg from?


    //Data Access Objects
    private final APIDataAccessObject APIDAO = new APIDataAccessObject();
    private final GameDataAccessObject gameDAO = new GameDataAccessObject(userPlayer); // TODO Where do I get the other 3 args from?
    private final AccountInfoDAO accountInfoDAO = new AccountInfoDAO();
    private final GameReportDataAccessObject gameReportDAO = new GameReportDataAccessObject();
    private UserFactory userFactory;

    //Views
    private MainMenuView mainMenuView;
    private SetupView setupView;
    private SignupView signupView;
    private LoginView loginView;
    private ObjectiveView objectiveView;
    private MovesView movesView;
    private DealingView dealingView;
    private DealerAfterStandView dealerAfterStandView;
    private ReportView reportView;

    //View Models
    private MainMenuViewModel mainMenuViewModel;
    private SetupViewModel setupViewModel;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private ObjectiveViewModel objectiveViewModel;
    private DealingViewModel dealingViewModel;
    private MovesViewModel movesViewModel;
    private DealerScreenViewModel dealerScreenViewModel;
    private ReportViewModel reportViewModel;

    public AppBuilder() throws FileNotFoundException {
    }

    //Views

    /**
     * Adds the SignUp view to the application
     * @return this builder
     */
    public AppBuilder addSignUpView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login view to the application
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Main Menu view to the application
     * @return this builder
     */
    public AppBuilder addMainMenuView() {
        mainMenuViewModel = new MainMenuViewModel();
        mainMenuView = new MainMenuView(mainMenuViewModel);
        cardPanel.add(mainMenuView, mainMenuView.getViewName());
        return this;
    }

    /**
     * Adds the Setup view to the application.
     * @return this builder
     */
    public AppBuilder addSetupView() {
        setupViewModel = new SetupViewModel();
        setupView = new SetupView(setupViewModel);
        cardPanel.add(setupView, setupView.getViewName());
        return this;
    }

    /**
     * Adds the DealerAfterStand view to the application.
     * @return this builder
     */
    public AppBuilder addDealerAfterStandView() {
        dealerScreenViewModel = new DealerScreenViewModel();
        dealerAfterStandView = new DealerAfterStandView(dealerScreenViewModel, new ReportViewModel());
        cardPanel.add(dealerAfterStandView, dealerAfterStandView.getViewName());
        return this;
    }

    /**
     * Adds the Assisted view to the application.
     * @return this builder
     */
    public AppBuilder addAssistedView() {
        return this;
        // TODO w matt's implementation
    }

    /**
     * Adds the Objective view to the application.
     * @return this builder
     */
    public AppBuilder addObjectiveView() {
        objectiveViewModel = new ObjectiveViewModel();
        dealingViewModel = new DealingViewModel();
        movesViewModel = new MovesViewModel();
        objectiveView = new ObjectiveView(objectiveViewModel, dealingViewModel, movesViewModel);

        cardPanel.add(objectiveView, objectiveView.getViewName());
        return this;
    }

    /**
     * Adds the Dealing view to the application.
     * @return this builder
     */
    public AppBuilder addDealingView() {
        objectiveViewModel = new ObjectiveViewModel();
        dealingViewModel = new DealingViewModel();
        movesViewModel = new MovesViewModel();
        dealingView = new DealingView(objectiveViewModel, dealingViewModel, movesViewModel);

        cardPanel.add(dealingView, dealingView.getViewName());
        return this;
    }

    /**
     * Adds the Moves view to the application.
     * @return this builder
     */
    public AppBuilder addMovesView() {
        objectiveViewModel = new ObjectiveViewModel();
        dealingViewModel = new DealingViewModel();
        movesViewModel = new MovesViewModel();
        movesView = new MovesView(objectiveViewModel, dealingViewModel, movesViewModel);

        cardPanel.add(movesView, movesView.getViewName());
        return this;
    }



    //Use cases

    /**
     * Adds the Login Use Case to the application.
     * @return this builder.
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, mainMenuViewModel, loginViewModel);

        final LoginInputBoundary loginInteractor = new LoginInteractor(accountInfoDAO, loginOutputBoundary);

        final LoginController controller = new LoginController(loginInteractor);
        loginView.setLoginController(controller);
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder.
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(signupViewModel, loginViewModel, viewManagerModel);

        final SignupInputBoundary signupInteractor = new SignupInteractor(accountInfoDAO, signupOutputBoundary, userFactory);
        // TODO Make sure user factory works
        final SignupController controller = new SignupController(signupInteractor);
        signupView.setSignupController(controller);
        // TODO The code in SignupView does NOT use SignupController... verify this is what you want
        return this;
    }

    /**
     * Adds the Setup Use Case to the application.
     * @return this builder.
     */
    public AppBuilder addSetupUseCase() {
        final SetupOutputBoundary setupOutputBoundary = new interface_adapter.freePlay.setup.SetupPresenter(viewManagerModel, mainMenuViewModel, setupViewModel, dealerScreenViewModel);

        final SetupInputBoundary setupInteractor = new SetupInteractor(gameDAO, APIDAO, setupOutputBoundary);

        final SetupController controller = new SetupController(setupInteractor);
        mainMenuView.setSetupController(controller);
        return this;
    }

    /**
     * Adds the Hit Use Case to the application.
     * @return this builder
     */
    public AppBuilder addHitUseCase() {
        final NewHitOutputBoundary hitOutputBoundary =
                new NewHitPresenter(setupViewModel);

        final NewHitInputBoundary newHitInteractor =
                new NewHitInteractor(APIDAO, gameDAO, hitOutputBoundary);

        final NewHitController controller =
                new NewHitController(newHitInteractor);

        setupView.setHitController(controller);
        return this;
    }

    /**
     * Adds the DealerScreen Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDealerScreenUseCase() {
        final DealerScreenOutputDataBoundary dealerScreenPresenter = new DealerScreenPresenter(viewManagerModel, dealerScreenViewModel, mainMenuViewModel);

        final DealerScreenInputBoundary dealerScreenInteractor = new DealerScreenInteractor(gameDAO, dealerScreenPresenter, gameReportDAO);

        final DealerScreenController controller = new DealerScreenController(dealerScreenInteractor);

        dealerAfterStandView.setDealerScreenController(controller);
        return this;
    }

    public AppBuilder addObjectiveLearnModeUseCase() {
        final LearnModeOutputBoundary learnModeOutputBoundary = new LearnModePresenter(viewManagerModel);
        final LearnModeInputBoundary learnModeInteractor = new LearnModeInteractor(learnModeOutputBoundary);
        final LearnModeController controller = new LearnModeController(learnModeInteractor);
        objectiveView.setLearnModeController(controller);
        return this;
    }

    public AppBuilder addDealingLearnModeUseCase() {
        final LearnModeOutputBoundary learnModeOutputBoundary = new LearnModePresenter(viewManagerModel);
        final LearnModeInputBoundary learnModeInteractor = new LearnModeInteractor(learnModeOutputBoundary);
        final LearnModeController controller = new LearnModeController(learnModeInteractor);
        dealingView.setLearnModeController(controller);
        return this;
    }

    public AppBuilder addMovesLearnModeUseCase() {
        final LearnModeOutputBoundary learnModeOutputBoundary = new LearnModePresenter(viewManagerModel);
        final LearnModeInputBoundary learnModeInteractor = new LearnModeInteractor(learnModeOutputBoundary);
        final LearnModeController controller = new LearnModeController(learnModeInteractor);
        movesView.setLearnModeController(controller);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the Signup View to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Blackjack Prediction Simulator");
        application.setIconImage(img.getImage());
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

}
