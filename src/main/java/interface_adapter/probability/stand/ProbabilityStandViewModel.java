package interface_adapter.probability.stand;

import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import static interface_adapter.probability.ColorConstants.TABLECOLOUR;
import static interface_adapter.probability.ColorConstants.PROGRESSBAR;

public class ProbabilityStandViewModel extends JPanel {
    public ProbabilityStandViewModel (int percentWin) {
        JPanel panel = new JPanel();
        panel.setSize(300, 100);
        panel.setLayout(new BorderLayout());
        panel.setBackground(TABLECOLOUR);

        JLabel title = new JLabel("Stand");
        title.setFont(new Font("Times New Roman", Font.BOLD, 15));

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(percentWin);
        progressBar.setStringPainted(true);
        progressBar.setForeground(PROGRESSBAR);

        panel.add(title, BorderLayout.NORTH);
        panel.add(progressBar, BorderLayout.CENTER);
    }
}
