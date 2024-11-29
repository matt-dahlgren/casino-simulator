package view;

import interface_adapter.report.GameReportController;
import interface_adapter.report.EmailReportController;
import interface_adapter.report.ReportState;
import interface_adapter.report.ReportViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.awt.Color;

import static data_access.GameReportDAOConstants.STATISTIC_LABELS;

/**
 * The View for when the user is viewing game data
 */
public class ReportView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String gameNumPrompt =
            "<html><span style='font-family: Times New Roman; font-size: 20px; color: white;'>" +
                    "Enter the number of the game report you'd like to see: </span></html>";

    private final String viewName = "report";
    private final ReportViewModel reportViewModel;

    private final JTextField gameNumInputField = new JTextField(15);
    private final JLabel gameNumErrorField = new JLabel();

    private final JButton searchReportsButton;
    private final JButton sendEmailButton;
    private final JButton goToMainButton;

    private final LabelTextPanel gameNumInfo = new LabelTextPanel(new JLabel(gameNumPrompt), gameNumInputField);

    private JLabel title;
    private JTable dataTable;

    private GameReportController gameReportController;
    private EmailReportController emailReportController;

    public ReportView(ReportViewModel reportViewModel) {
        // TODO: Add email error field and functionality for the email and menu buttons
        this.reportViewModel = reportViewModel;
        this.reportViewModel.addPropertyChangeListener(this);

        ReportState state = reportViewModel.getState();
        String[][] gameData = state.getGameData();
        String[][] gameDateWithTurns = formatGameData(gameData);

        dataTable = new JTable(10, STATISTIC_LABELS.length);
        formatDataTable(gameDateWithTurns);

        int gameNum = reportViewModel.getState().getOutputGameNum();
        title = new JLabel("Summary of Game " + gameNum);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchReportsButton = new JButton("Submit Game Number");
        sendEmailButton = new JButton("Email Report to You");
        goToMainButton = new JButton("Go To Main Menu");

        searchReportsButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(searchReportsButton)) {
                            ReportState state = reportViewModel.getState();
                            gameReportController.execute(state.getInputGameNum());
                        }
                    }
                }
        );

        sendEmailButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(sendEmailButton)) {
                            // TODO
                            // ReportState state = reportViewModel.getState();
                            // emailReportController.execute(state.getEmail(), state.getOutputGameNum());
                        }
                    }
                }
        );

        goToMainButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(goToMainButton)) {
                            // TODO
                        }
                    }
                }
        );

        gameNumInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final ReportState state = reportViewModel.getState();
                state.setInputGameNum(gameNumInputField.getText());
                reportViewModel.setState(state);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        formatUI();

        this.add(title);
        this.add(dataTable);
        this.add(gameNumInfo);
        this.add(gameNumErrorField);
        this.add(searchReportsButton);
        this.add(sendEmailButton);
        this.add(goToMainButton);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ReportState state = (ReportState) evt.getNewValue();
        setFields(state);
        gameNumErrorField.setText(state.getGameNumError());
    }

    private void setFields(ReportState state) {
        gameNumInputField.setText(state.getInputGameNum());
        title.setText("Summary of Game " + state.getOutputGameNum());

        String[][] gameData = state.getGameData();
        String[][] gameDateWithTurns = formatGameData(gameData);
        formatDataTable(gameDateWithTurns);
    }

    public String getViewName() {
        return viewName;
    }

    public void setGameReportController(GameReportController gameReportController) {
        this.gameReportController = gameReportController;
    }

    public void setEmailReportController(EmailReportController emailReportController) {
        this.emailReportController = emailReportController;
    }

    /**
     * Given a table (2D array) of information about a game, adds headings and move numbers
     * @param gameData a table of information about a game, excluding headings and move numbers
     * @return the gameData with headings and move numbers
     */
    private String[][] formatGameData(String[][] gameData) {
        // Adds headings
        String[][] gameDataWithTurns = new String[10][gameData[0].length + 1];
        gameDataWithTurns[0] = STATISTIC_LABELS;

        // Adds move numbers
        for (int i = 0; i < gameData.length; i++) {
            gameDataWithTurns[i + 1][0] = Integer.toString(i + 1);

            // Adds the data from gameData
            for (int j = 0; j < gameData[i].length; j++) {
                gameDataWithTurns[i + 1][j + 1] = gameData[i][j];
            }
        }

        return gameDataWithTurns;
    }

    /**
     * Updates the information in the data table
     */
    private void formatDataTable(String[][] gameData) {
        // Empties the data table
        for (int i = 0; i < dataTable.getRowCount(); i++) {
            for (int j = 0; j < dataTable.getColumnCount(); j++) {
                dataTable.setValueAt("", i, j);
            }
        }

        // Loads new data into the table
        for (int i = 0; i < gameData.length; i++) {
            for (int j = 0; j < STATISTIC_LABELS.length; j++) {
                dataTable.setValueAt(gameData[i][j], i, j);
            }
        }
    }

    /**
     * Formats the layout of the UI (colours, fonts, coordinates, dimensions)
     */
    private void formatUI() {
        // Fonts and colours
        Color bg = new Color(42,99,39);
        Color textColour = new Color(255, 255, 255);
        Color dataTableBackground = new Color(217, 217, 217);
        Color dataTableForeground = new Color(0, 0, 0);

        Font titleFont = new Font("Times New Roman", Font.BOLD, 32);
        Font textFont = new Font("Times New Roman", Font.BOLD, 20);
        Font dataTableFont = new Font("Times New Roman", Font.BOLD, 16);

        // Setting fonts and colours
        this.setLayout(null);
        this.setBackground(bg);

        title.setFont(titleFont);
        title.setForeground(textColour);

        dataTable.setFont(dataTableFont);
        dataTable.setGridColor(bg);
        dataTable.setBackground(dataTableBackground);
        dataTable.setForeground(dataTableForeground);

        gameNumInfo.setBackground(bg);

        searchReportsButton.setFont(textFont);
        sendEmailButton.setFont(textFont);
        goToMainButton.setFont(textFont);

        gameNumErrorField.setFont(textFont);
        gameNumErrorField.setForeground(textColour);

        // Setting coordinates and dimensions
        title.setBounds(550,60,600,50);
        dataTable.setBounds(85,120,1200,160);
        gameNumInfo.setBounds(150,300,800,50);
        gameNumErrorField.setBounds(950, 300, 800, 50);
        searchReportsButton.setBounds(330, 360, 230, 50);
        sendEmailButton.setBounds(570, 360, 230, 50);
        goToMainButton.setBounds(810, 360, 230, 50);
    }
}
