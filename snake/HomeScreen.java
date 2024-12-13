package snake;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {
    public HomeScreen(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(null);

        // 设置背景图片
        JLabel backgroundLabel = new JLabel(new ImageIcon("background.jpg"));
        backgroundLabel.setBounds(0, 0, 800, 600);
        add(backgroundLabel);

        JLabel titleLabel = new JLabel("Snake", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(200, 50, 400, 50);
        add(titleLabel);

        JButton startButton = UIUtils.createImageButton("start.png", 300, 200, 200, 80);
        JButton optionButton = UIUtils.createImageButton("option.png", 300, 320, 200, 80);

        add(startButton);
        add(optionButton);

        // 按钮行为
        startButton.addActionListener(e -> cardLayout.show(mainPanel, "GameScreen"));
        optionButton.addActionListener(e -> cardLayout.show(mainPanel, "OptionScreen"));
    }
}
