package interface_adapter.probability.stand;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import static interface_adapter.probability.ProbabilityColourConstants.TABLECOLOUR;
import static interface_adapter.probability.ProbabilityColourConstants.PROGRESSCOLOUR;
import static interface_adapter.probability.ProbabilityColourConstants.NONPROGRESSEDCOLOUR;

public class ProbabilityStandViewModel extends JPanel {

    public ProbabilityStandViewModel(int outputData) {
        setSize(300, 100);
        setBackground(TABLECOLOUR);

        JPanel labelPanel = new JPanel(new BorderLayout());

        JLabel hitLabel = new JLabel("Stand");
        hitLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        JLabel percentLabel = new JLabel(String.valueOf(outputData));
        percentLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        labelPanel.add(hitLabel, BorderLayout.WEST);
        labelPanel.add(percentLabel, BorderLayout.EAST);

        add(labelPanel, BorderLayout.NORTH);

        JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(false);
        progressBar.setValue(outputData);
        progressBar.setPreferredSize(new Dimension(200, 50));
        progressBar.setForeground(PROGRESSCOLOUR);
        progressBar.setBackground(NONPROGRESSEDCOLOUR);
        progressPanel.add(progressBar);

        add(progressPanel, BorderLayout.SOUTH);
    }
}
