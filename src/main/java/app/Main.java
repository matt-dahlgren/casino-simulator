package app;

import interface_adapter.report.ReportViewModel;
import view.ReportView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Main class of this program.
 * Responsible for actually running the app!
 */
public class Main {
    static final int WIDTH = 1400;
    static final int HEIGHT = 800;

    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addTeamView()
                .build();

        application.pack();

        application.setSize(WIDTH, HEIGHT);

        application.setVisible(true);
    }
}
