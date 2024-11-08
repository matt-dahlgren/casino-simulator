package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialViewModel;
import view.InitialView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

/**
 *
 */

public class AppBuilder {

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private InitialView initialView;
    private InitialViewModel initialViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Default View to the application
     * @return this builder
     */
    public AppBuilder addInitialView() {
        initialViewModel = new InitialViewModel();
        initialView = new InitialView(initialViewModel);
        cardPanel.add(initialView, initialView.getViewName());
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the initialView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Blackjack Prediction");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(initialView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

}
