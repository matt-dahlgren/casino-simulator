package use_case.email_report;

import data_access.AccountInfoDAO;
import use_case.endgame_report.GameReportDataAccessInterface;
import data_access.GameReportDAOConstants;
import use_case.signup.SignupUserDataAccessInterface;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * The email game report interactor.
 */
public class EmailReportInteractor implements EmailReportInputBoundary {
    private final GameReportDataAccessInterface reportDataAccessObject;
    private final EmailReportOutputBoundary reportPresenter;
    private final SignupUserDataAccessInterface accountInfoDAO;

    public EmailReportInteractor(GameReportDataAccessInterface reportDataAccessObject,
                                 EmailReportOutputBoundary reportOutputBoundary,
                                 SignupUserDataAccessInterface accountInfoDAO) {
        this.reportDataAccessObject = reportDataAccessObject;
        this.reportPresenter = reportOutputBoundary;
        this.accountInfoDAO = accountInfoDAO;
    }

    @Override
    public void execute(EmailReportInputData emailReportInputData) {
        int gameNum = emailReportInputData.getGameNum();
        String[][] gameData = reportDataAccessObject.getGameSummary(gameNum);
        String body = emailFromData(gameData);

        String from = "learnblackjack101";
        String pass = "sdap cdvb hmte uqph";
        String to = accountInfoDAO.getCurrentEmail();
        String subject = "Your Blackjack Game Summary";

        // If the email is sent, prepare the success view
        // Otherwise, prepare the fail view
        if (sendEmail(from, pass, to, subject, body)) {
            final EmailReportOutputData emailReportOutputData = new EmailReportOutputData("Sent!");
            reportPresenter.prepareSuccessView(emailReportOutputData);
        }
        else {
            reportPresenter.prepareFailView("Sorry, we couldn't reach your email");
        }
    }

    /**
     * Sends an email to the user.
     * @param from who is sending the email
     * @param pass the password of the sender
     * @param to the recipient of the email
     * @param subject the subject of the email
     * @param body the body/text of the email
     * @return true if and only if the email is successfully sent
     */
    private static boolean sendEmail(String from, String pass, String to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * Converts the data for a game to a formatted table to be emailed.
     * @param gameData the data for a game stored in a 2D array
     * @return the game data formatted to be easily readable
     */
    public String emailFromData(String[][] gameData) {
        String[] statisticLabels = GameReportDAOConstants.getStatisticLabels();
        String[] columnSpacing = GameReportDAOConstants.getColumnSpacing();

        String email = String.join("      ", statisticLabels);

        for (int i = 0; i < gameData.length; i++) {
            email += "\n" + (i + 1) + columnSpacing[0];

            for (int j = 1; j < statisticLabels.length; j++) {
                email += gameData[i][j - 1] + columnSpacing[j];
            }
        }

        return email;
    }
}
