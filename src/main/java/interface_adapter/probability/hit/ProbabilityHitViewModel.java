package interface_adapter.probability.hit;

import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import static interface_adapter.probability.ColorConstants.TABLECOLOUR;
import static interface_adapter.probability.ColorConstants.PROGRESSBAR;

public class ProbabilityHitViewModel extends JPanel{

    public ProbabilityHitViewModel (int percentWin) {
        this.setSize(300, 100);
        this.setLayout(new BorderLayout());
        this.setBackground(TABLECOLOUR);

        JLabel title = new JLabel("Hit");
        title.setFont(new Font("Times New Roman", Font.BOLD, 15));

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(percentWin);
        progressBar.setStringPainted(true);
        progressBar.setForeground(PROGRESSBAR);

        this.add(title, BorderLayout.NORTH);
        this.add(progressBar, BorderLayout.CENTER);
    }
}
