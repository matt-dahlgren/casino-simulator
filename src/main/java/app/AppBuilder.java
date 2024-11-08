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
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final ImageIcon img = new ImageIcon("resources/images/icon.png");

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
        final JFrame application = new JFrame("Blackjack Prediction Simulator");
        application.setIconImage(img.getImage());
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Places our completed cardPanel in a GridBagLayout, so that it can be centered.
        JPanel outerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        outerPanel.add(cardPanel, gbc);
        application.add(outerPanel);

        // Sets "initialView" as the view, aka initialView is the default view.
        viewManagerModel.setState(initialView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

}
