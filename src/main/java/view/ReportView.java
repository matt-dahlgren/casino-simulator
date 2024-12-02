package view;

import data_access.GameReportDAOConstants;
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

/**
 * The View for when the user is viewing game data
 */
public class ReportView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final String GAME_NUM_PROMPT = "<html><span style='font-family: Times New Roman; font-size: 20px; " +
            "color: white;'>Enter the number of the game report you'd like to see: </span></html>";

    private final JTextField gameNumInputField = new JTextField(15);
    private final JLabel feedbackField = new JLabel();

    private final JButton searchReportsButton;
    private final JButton sendEmailButton;
    private final JButton goToMainButton;

    private final LabelTextPanel gameNumInfo = new LabelTextPanel(new JLabel(GAME_NUM_PROMPT), gameNumInputField);

    private final JLabel title;
    private final JTable dataTable;

    private transient GameReportController gameReportController;
    private transient EmailReportController emailReportController;

    public ReportView(ReportViewModel reportViewModel) {
        reportViewModel.addPropertyChangeListener(this);

        ReportState state = reportViewModel.getState();
        String[][] gameData = state.getGameData();
        String[][] gameDateWithTurns = formatGameData(gameData);
        String[] statisticLabels = GameReportDAOConstants.getStatisticLabels();

        dataTable = new JTable(10, statisticLabels.length);
        formatDataTable(gameDateWithTurns);

        int gameNum = reportViewModel.getState().getOutputGameNum();
        title = new JLabel("Summary of Game " + gameNum);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchReportsButton = new JButton("Submit Game Number");
        sendEmailButton = new JButton("Email Report to You");
        goToMainButton = new JButton("Go To Main Menu");

        searchReportsButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(searchReportsButton)) {
                        ReportState state1 = reportViewModel.getState();
                        gameReportController.execute(state1.getInputGameNum());
                    }
                }
        );

        sendEmailButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(sendEmailButton)) {
                        ReportState state2 = reportViewModel.getState();
                        emailReportController.execute(state2.getOutputGameNum());
                    }
                }
        );

        goToMainButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(goToMainButton)) {
                        gameReportController.switchToMainMenuView();
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
        this.add(feedbackField);
        this.add(searchReportsButton);
        this.add(sendEmailButton);
        this.add(goToMainButton);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ReportState state = (ReportState) evt.getNewValue();
        setFields(state);
        feedbackField.setText(state.getFeedback());
    }

    private void setFields(ReportState state) {
        gameNumInputField.setText(state.getInputGameNum());
        title.setText("Summary of Game " + state.getOutputGameNum());

        String[][] gameData = state.getGameData();
        String[][] gameDateWithTurns = formatGameData(gameData);
        formatDataTable(gameDateWithTurns);
    }

    public String getViewName() {
        return "report";
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
        gameDataWithTurns[0] = GameReportDAOConstants.getStatisticLabels();

        // Adds move numbers
        for (int i = 0; i < gameData.length; i++) {
            gameDataWithTurns[i + 1][0] = Integer.toString(i + 1);

            // Adds the data from gameData
            System.arraycopy(gameData[i], 0, gameDataWithTurns[i + 1], 1, gameData[i].length);
        }

        return gameDataWithTurns;
    }

    /**
     * Updates the information in the data table
     */
    private void formatDataTable(String[][] gameData) {
        String[] statisticLabels = GameReportDAOConstants.getStatisticLabels();

        // Empties the data table
        for (int i = 0; i < dataTable.getRowCount(); i++) {
            for (int j = 0; j < dataTable.getColumnCount(); j++) {
                dataTable.setValueAt("", i, j);
            }
        }

        // Loads new data into the table
        for (int i = 0; i < gameData.length; i++) {
            for (int j = 0; j < statisticLabels.length; j++) {
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

        String timesNewRoman = "Times New Roman";
        Font titleFont = new Font(timesNewRoman, Font.BOLD, 32);
        Font textFont = new Font(timesNewRoman, Font.BOLD, 20);
        Font dataTableFont = new Font(timesNewRoman, Font.BOLD, 16);

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

        feedbackField.setFont(textFont);
        feedbackField.setForeground(textColour);

        // Setting coordinates and dimensions
        title.setBounds(550,60,600,50);
        dataTable.setBounds(85,120,1200,160);
        gameNumInfo.setBounds(150,300,800,50);
        feedbackField.setBounds(950, 300, 800, 50);
        searchReportsButton.setBounds(330, 360, 230, 50);
        sendEmailButton.setBounds(570, 360, 230, 50);
        goToMainButton.setBounds(810, 360, 230, 50);
    }
}
