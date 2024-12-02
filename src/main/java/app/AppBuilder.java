package app;

import data_access.APIDataAccessObject;
import data_access.GameDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.freePlay.newhit.NewHitController;
import interface_adapter.freePlay.newhit.NewHitPresenter;
import interface_adapter.freePlay.setup.SetupController;
import interface_adapter.freePlay.setup.SetupPresenter;
import interface_adapter.freePlay.setup.SetupViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.freeplay.newhit.NewHitInputBoundary;
import use_case.freeplay.newhit.NewHitInteractor;
import use_case.freeplay.newhit.NewHitOutputBoundary;
import use_case.freeplay.setup.SetupInputBoundary;
import use_case.freeplay.setup.SetupInteractor;
import use_case.freeplay.setup.SetupOutputBoundary;
import view.MainMenuView;
import view.SetupView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.nio.channels.SeekableByteChannel;

/**
 *
 */

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final ImageIcon img = new ImageIcon("resources/images/icon.png");

    //Data Access Objects
    private final APIDataAccessObject APIDAO = new APIDataAccessObject();
    private final GameDataAccessObject gameDAO = new GameDataAccessObject();

    //Views
    private MainMenuView mainMenuView;
    private SetupView setupView;

    //View Models
    private MainMenuViewModel mainMenuViewModel;
    private SetupViewModel setupViewModel;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    //Views

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

    //Use cases

    /**
     * Adds the Setup Use Case to the application.
     * @return this builder.
     */
    public AppBuilder addSetupUseCase() {
        final SetupOutputBoundary setupOutputBoundary = new SetupPresenter(viewManagerModel, mainMenuViewModel, setupViewModel);

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
     * Creates the JFrame for the application and initially sets the Signup View to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Blackjack Prediction Simulator");
        application.setIconImage(img.getImage());
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        // Places our completed cardPanel in a GridBagLayout, so that it can be centered.
//        JPanel outerPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0;
//        gbc.anchor = GridBagConstraints.CENTER;
//
//        outerPanel.add(cardPanel, gbc);
//        application.add(outerPanel);

        application.add(cardPanel);

        // Sets "TeamView" as the view, aka TeamView is the default view.
        viewManagerModel.setState(mainMenuView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

}
