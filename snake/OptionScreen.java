package snake;

import javax.swing.*;
import java.awt.*;

public class OptionScreen extends JPanel {
    private boolean enableStars = true;
    private int gameSpeed = 150;

    public OptionScreen(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(null);

        JLabel optionLabel = new JLabel("Settings", SwingConstants.CENTER);
        optionLabel.setFont(new Font("Arial", Font.BOLD, 36));
        optionLabel.setBounds(200, 20, 400, 50);
        add(optionLabel);

        JCheckBox starCheckBox = new JCheckBox("Enable Stars");
        starCheckBox.setSelected(enableStars);
        starCheckBox.setFont(new Font("Arial", Font.PLAIN, 20));
        starCheckBox.setBounds(200, 100, 400, 30);
        add(starCheckBox);

        JLabel speedLabel = new JLabel("Game Speed");
        speedLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        speedLabel.setBounds(150, 150, 600, 30);
        add(speedLabel);

        JSlider speedSlider = new JSlider(50, 300, gameSpeed);
        speedSlider.setMajorTickSpacing(50);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setBounds(250, 200, 300, 50);
        add(speedSlider);

        JButton backButton = UIUtils.createImageButton("back.png", 300, 300, 200, 80);
        add(backButton);

        backButton.addActionListener(e -> {
            enableStars = starCheckBox.isSelected();
            gameSpeed = speedSlider.getValue();
            cardLayout.show(mainPanel, "HomeScreen");
        });
    }
}
