package view;

import javax.swing.*;
import java.awt.*;

import static interface_adapter.assisted_mode.AssistedModeColourConstants.TABLECOLOUR;

public class ProbabilityHitView extends JPanel{

    public ProbabilityHitView(int outputData) {

        setLayout(new BorderLayout());
        setSize(300, 100);
        setBackground(TABLECOLOUR);

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
