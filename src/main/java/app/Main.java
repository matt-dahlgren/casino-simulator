package app;

import javax.swing.JFrame;

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
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addInitialView()
                .build();

        application.pack();

        application.setSize(WIDTH, HEIGHT);

        application.setVisible(true);
    }
}
