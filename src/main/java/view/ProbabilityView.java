package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

/**
 * This is the view presenting the information of the Probability Use Case.
 */
public class ProbabilityView extends JPanel {

    public ProbabilityView(int hit, int stand) {
        setSize(300, 200);
        setLayout(new BorderLayout());
        setBackground(Color.getHSBColor(0f, 102f, 0f));

        add(subPanelBuilder("Hit", hit), BorderLayout.NORTH);
        add(subPanelBuilder("Stand", stand), BorderLayout.SOUTH);
    }

    private JPanel subPanelBuilder(String probabilityType, int percentWin) {
        JPanel panel = new JPanel();
        panel.setSize(300, 100);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.getHSBColor(0f, 102f, 0f));

        JLabel title = new JLabel(probabilityType);
        title.setFont(new Font("Times New Roman", Font.BOLD, 15));

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(percentWin);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.getHSBColor(255f, 255f, 204f));

        panel.add(title, BorderLayout.NORTH);
        panel.add(progressBar, BorderLayout.CENTER);

        return panel;
    }
}
