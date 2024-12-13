package snake;

import javax.swing.*;
import java.awt.*;

public class SnakeGameMain extends JFrame {
    public SnakeGameMain() {
        setTitle("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // 初始化各屏幕
        HomeScreen homeScreen = new HomeScreen(cardLayout, mainPanel);
        OptionScreen optionScreen = new OptionScreen(cardLayout, mainPanel);
        SnakeGame gamePanel = new SnakeGame(cardLayout, mainPanel);

        // 添加屏幕到主面板
        mainPanel.add(homeScreen, "HomeScreen");
        mainPanel.add(optionScreen, "OptionScreen");
        mainPanel.add(new JPanel(new BorderLayout()) {{
            setBackground(Color.BLACK);
            add(gamePanel, BorderLayout.CENTER);
        }}, "GameScreen");

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SnakeGameMain::new);
    }
}
