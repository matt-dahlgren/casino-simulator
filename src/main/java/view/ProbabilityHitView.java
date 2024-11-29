package view;

import interface_adapter.probability.hit.ProbabilityHitViewModel;

import javax.swing.*;
import java.awt.*;

import static interface_adapter.probability.ProbabilityColourConstants.TABLECOLOUR;

public class ProbabilityHitView extends JPanel{

    private ProbabilityHitViewModel probabilityHitViewModel;

    public ProbabilityHitView(ProbabilityHitViewModel probabilityHitViewModel) {

        this.probabilityHitViewModel = probabilityHitViewModel;

        setLayout(new BorderLayout());
        setSize(300, 100);
        setBackground(TABLECOLOUR);

        int outputData = probabilityHitViewModel.getState().getStandProbability();

        JLabel hitLabel = new JLabel("<html><font color = 'white'>Hit: " + outputData + "%</font></html>");
        hitLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        add(hitLabel, BorderLayout.LINE_START);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(false);
        progressBar.setValue(outputData);
        progressBar.setBorderPainted(false);
        add(progressBar, BorderLayout.CENTER);
    }
}
