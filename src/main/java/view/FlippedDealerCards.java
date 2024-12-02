package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

import static interface_adapter.assisted_mode.AssistedModeColourConstants.TABLECOLOUR;

public class FlippedDealerCards extends JPanel {

    public FlippedDealerCards(ArrayList<String> dealerCards) {

        setLayout(new FlowLayout());
        setBackground(TABLECOLOUR);

        for (String card : dealerCards) {
            try {
                URL imageUrl = new URL(card);
                BufferedImage image = ImageIO.read(imageUrl);
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);
                imageLabel.setSize(100, 100);
                add(imageLabel);
            } catch (IOException e) {
                e.printStackTrace();
                JLabel errorLabel = new JLabel("Failed to load image.");
                errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                add(errorLabel, BorderLayout.CENTER);
            }

        }
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        int x = this.getWidth() / 2;
        int y = this.getHeight() / 2;
        g.rotate(Math.toRadians(180.0), x, y);
        super.paintComponent(g);
    }

}
