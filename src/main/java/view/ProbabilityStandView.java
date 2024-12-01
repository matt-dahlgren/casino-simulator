package view;

import interface_adapter.probability.stand.ProbabilityStandViewModel;

import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.*;

import static interface_adapter.probability.ProbabilityColourConstants.TABLECOLOUR;

/**
 * This is the view presenting the information of the ProbabilityStand Use Case.
 * Creates a 300x100 JPanel.
 */
public class ProbabilityStandView extends JPanel {

    private final ProbabilityStandViewModel probabilityStandViewModel;

    public ProbabilityStandView(ProbabilityStandViewModel probabilityStandViewModel) {
        this.probabilityStandViewModel = probabilityStandViewModel;

        setLayout(new BorderLayout());
        setSize(300, 100);
        setBackground(TABLECOLOUR);

        int outputData = probabilityStandViewModel.getState().getStandProbability();

        JLabel hitLabel = new JLabel("<html><font color = 'white'>Stand: " + outputData + "%</font></html>");
        hitLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        add(hitLabel, BorderLayout.LINE_START);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(false);
        progressBar.setValue(outputData);
        progressBar.setBorderPainted(false);
        add(progressBar, BorderLayout.CENTER);

    }

}
