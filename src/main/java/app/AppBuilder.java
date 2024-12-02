package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.freePlay.setup.SetupViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.freePlay.hit.HitViewModel;
import view.HitView;
import view.MainMenuView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

/**
 *
 */

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final ImageIcon img = new ImageIcon("resources/images/icon.png");

    private MainMenuView mainMenuView;
    private HitView hitView;

    private MainMenuViewModel mainMenuViewModel;
    private HitViewModel hitViewModel;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
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
     * Adds the Hit view to the application
     * @return this builder
     */
    public AppBuilder addHitView() {
        hitViewModel = new HitViewModel();
        hitView = new HitView(hitViewModel);
        cardPanel.add(hitView, hitView.getViewName());
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
