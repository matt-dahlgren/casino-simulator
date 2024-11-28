package app;

import data_access.GameReportDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialViewModel;
import interface_adapter.report.*;
import use_case.email_report.EmailReportInteractor;
import use_case.email_report.EmailReportOutputBoundary;
import use_case.endgame_report.GameReportInteractor;
import use_case.endgame_report.GameReportOutputBoundary;
import view.InitialView;
import view.ReportView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 *
 */

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final ImageIcon img = new ImageIcon("resources/images/icon.png");

    private final JLabel hitCard = new JLabel();

    private ReportView initialView;
    private ReportViewModel initialViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Default View to the application
     * @return this builder
     */
    public AppBuilder addInitialView() throws FileNotFoundException {
        //initialViewModel = new InitialViewModel();
        //initialView = new InitialView(initialViewModel, hitCard);
        //cardPanel.add(initialView, initialView.getViewName());
        //return this;

        initialViewModel = new ReportViewModel("src/main/java/user_data/Martha.csv");
        initialView = new ReportView(initialViewModel);
        cardPanel.add(initialView, "Hello");

        GameReportDataAccessObject DAO = new GameReportDataAccessObject("src/main/java/user_data/Martha.csv");

        EmailReportOutputBoundary emailReportPresenter = new EmailReportPresenter(initialViewModel, viewManagerModel);
        EmailReportInteractor emailReportInteractor = new EmailReportInteractor(DAO, emailReportPresenter);
        EmailReportController emailReportController = new EmailReportController(emailReportInteractor);

        GameReportOutputBoundary gameReportPresenter = new GameReportPresenter(initialViewModel, viewManagerModel);
        GameReportInteractor gameReportInteractor = new GameReportInteractor(DAO, gameReportPresenter);
        GameReportController gameReportController = new GameReportController(gameReportInteractor);

        initialView.setEmailReportController(emailReportController);
        initialView.setGameReportController(gameReportController);

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

        // Sets "initialView" as the view, aka initialView is the default view.
        viewManagerModel.setState(initialView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

}
