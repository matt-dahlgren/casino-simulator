package app;

import data_access.APIDataAccessObject;
import data_access.GameDataAccessObject;
import entities.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.assisted_mode.AssistedModeController;
import interface_adapter.assisted_mode.AssistedModeHitPresenter;
import interface_adapter.assisted_mode.AssistedModeViewModel;
import interface_adapter.dealer_screen.DealerScreenViewModel;
import interface_adapter.freePlay.newhit.NewHitController;
import interface_adapter.free_play.newhit.NewHitPresenter;
import interface_adapter.free_play.setup.SetupController;
import interface_adapter.free_play.setup.SetupViewModel;
import interface_adapter.learn_mode.*;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.report.*;
import interface_adapter.signup_adapter.*;
import interface_adapter.login_adapter.*;
import interface_adapter.dealer_screen.*;
import interface_adapter.assisted_mode.setup.*;
import use_case.assisted_mode.game.AssistedGameInputDataBoundary;
import use_case.assisted_mode.game.AssistedGameInteractor;
import use_case.assisted_mode.game.AssistedGameOutputDataBoundary;
import use_case.assisted_mode.setup.AssistedSetupInputBoundary;
import use_case.assisted_mode.setup.AssistedSetupInteractor;
import use_case.assisted_mode.setup.AssistedSetupOutputBoundary;
import use_case.dealer_screen.DealerScreenInputBoundary;
import use_case.dealer_screen.DealerScreenInteractor;
import use_case.dealer_screen.DealerScreenOutputDataBoundary;
import use_case.email_report.EmailReportInputBoundary;
import use_case.email_report.EmailReportInteractor;
import use_case.email_report.EmailReportOutputBoundary;
import use_case.endgame_report.GameReportInputBoundary;
import use_case.endgame_report.GameReportInteractor;
import use_case.endgame_report.GameReportOutputBoundary;
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
import java.util.ArrayList;

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
    private final UserPlayer userPlayer = new UserPlayer(new ArrayList<>());
    private final Dealer dealer = new Dealer(new ArrayList<>());

    //Data Access Objects
    private final APIDataAccessObject APIDAO = new APIDataAccessObject();
    private final GameDataAccessObject gameDAO = new GameDataAccessObject(userPlayer, dealer, "",
            new ArrayList<>());
    private final AccountInfoDAO accountInfoDAO = new AccountInfoDAO();
    private GameReportDataAccessObject gameReportDAO = new GameReportDataAccessObject();
    private UserFactory userFactory = new CommonUserFactory();

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
    private AssistedView assistedView;

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
    private AssistedModeViewModel assistedModeViewModel;

    public AppBuilder() throws FileNotFoundException {
    }

    //Views

    /**
     * Adds the SignUp view to the application
     * @return this builder
     */
    public AppBuilder addSignUpView() {
        signupViewModel = new SignupViewModel();
        loginViewModel = new LoginViewModel();
        signupView = new SignupView(signupViewModel, loginViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login view to the application
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        mainMenuViewModel = new MainMenuViewModel();
        loginView = new LoginView(loginViewModel, mainMenuViewModel);
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
     * Adds the Assisted view to the application.
     * @return this builder
     */
    public AppBuilder addAssistedView() {
        assistedModeViewModel = new AssistedModeViewModel();
        assistedView = new AssistedView(assistedModeViewModel, mainMenuViewModel, dealerScreenViewModel);
        cardPanel.add(assistedView, assistedView.getViewName());
        return this;
    }

    /**
     * Adds the Report view to the application.
     * @return this builder
     */
    public AppBuilder addReportView() throws FileNotFoundException {
        reportViewModel = new ReportViewModel(accountInfoDAO.getCurrentUser(), gameReportDAO.getNumGames());
        reportView = new ReportView(reportViewModel);
        cardPanel.add(reportView, reportView.getViewName());
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

        final SignupController controller = new SignupController(signupInteractor);
        signupView.setSignupController(controller);
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
        final NewHitOutputBoundary hitOutputBoundary = new NewHitPresenter(setupViewModel);

        final NewHitInputBoundary newHitInteractor = new NewHitInteractor(APIDAO, gameDAO, hitOutputBoundary);

        final NewHitController controller = new NewHitController(newHitInteractor);

        setupView.setHitController(controller);
        return this;
    }

    /**
     * Adds the DealerScreen Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDealerScreenUseCase() {
        final DealerScreenOutputDataBoundary dealerScreenPresenter = new DealerScreenPresenter(viewManagerModel, dealerScreenViewModel, mainMenuViewModel);

        ArrayList<String> cards = new ArrayList<>();

        final DealerScreenInputBoundary dealerScreenInteractor = new DealerScreenInteractor(gameDAO, dealerScreenPresenter, gameReportDAO, cards);

        final DealerScreenController controller = new DealerScreenController(dealerScreenInteractor);

        dealerAfterStandView.setDealerScreenController(controller);
        return this;
    }

    /**
     * Adds the Assisted Mode SETUP Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAssistedSetupUseCase() {
        final AssistedSetupOutputBoundary assistedSetupPresenter = new AssistedModeSetupPresenter(viewManagerModel, assistedModeViewModel);

        final AssistedSetupInputBoundary assistedSetupInteractor = new AssistedSetupInteractor(assistedSetupPresenter, gameDAO);

        final AssistedModeSetupController controller = new AssistedModeSetupController(assistedSetupInteractor);

        mainMenuView.setAssistedModeSetupController(controller);

        return this;
    }

    /**
     * Adds the Assisted Mode GAME Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAssistedGameUseCase() {
        final AssistedGameOutputDataBoundary assistedGamePresenter = new AssistedModeHitPresenter(viewManagerModel, assistedModeViewModel, dealerScreenViewModel, mainMenuViewModel);

        final AssistedGameInputDataBoundary assistedGameInteractor = new AssistedGameInteractor(gameDAO, assistedGamePresenter, accountInfoDAO, gameReportDAO);

        final AssistedModeController controller = new AssistedModeController(assistedGameInteractor);

        assistedView.setAssistedModeController(controller);

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

    public AppBuilder addReportUseCase() throws FileNotFoundException {
        gameReportDAO = new GameReportDataAccessObject(accountInfoDAO.getCurrentUser());

        final GameReportOutputBoundary reportOutputBoundary = new GameReportPresenter(reportViewModel,
                viewManagerModel);
        final GameReportInputBoundary gameReportUseCaseInteractor = new GameReportInteractor(gameReportDAO,
                reportOutputBoundary);
        final GameReportController gameReportController = new GameReportController(gameReportUseCaseInteractor);
        reportView.setGameReportController(gameReportController);

        EmailReportOutputBoundary emailReportOutputBoundary = new EmailReportPresenter(reportViewModel,
                viewManagerModel);
        EmailReportInputBoundary emailReportUseCaseInteractor = new EmailReportInteractor(gameReportDAO,
                emailReportOutputBoundary, accountInfoDAO);
        EmailReportController emailReportController = new EmailReportController(emailReportUseCaseInteractor);
        reportView.setEmailReportController(emailReportController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the Signup View to be displayed.
     * @return the application
     */
    public JFrame build() {
        accountInfoDAO.setCurrentUser("Martha");

        final JFrame application = new JFrame("Blackjack Prediction Simulator");
        application.setIconImage(img.getImage());
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(reportView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

}
