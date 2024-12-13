package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {
    private static final int TILE_SIZE = 25;
    private static final int GRID_WIDTH = 20;
    private static final int GRID_HEIGHT = 18;

    private ArrayList<Point> snake;
    private Point food;
    private Point star;
    private String direction = "RIGHT";
    private boolean gameOver = false;
    private int score = 0;
    private Timer timer;
    private int gameSpeed;

    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    private ImageIcon appleImage;
    private ImageIcon starImage;

    public SnakeGame(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        appleImage = new ImageIcon("apple.png");
        starImage = new ImageIcon("stars.png");

        snake = new ArrayList<>();
        snake.add(new Point(5, 5));
        spawnFood();

        setPreferredSize(new Dimension(GRID_WIDTH * TILE_SIZE, GRID_HEIGHT * TILE_SIZE));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameOver) return;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> direction = "UP";
                    case KeyEvent.VK_DOWN -> direction = "DOWN";
                    case KeyEvent.VK_LEFT -> direction = "LEFT";
                    case KeyEvent.VK_RIGHT -> direction = "RIGHT";
                }
            }
        });

        timer = new Timer(150, this);
        timer.start();
    }

    public void setGameSpeed(int speed) {
        this.gameSpeed = speed;
        timer.setDelay(speed);
    }

    private void spawnFood() {
        Random random = new Random();
        food = new Point(random.nextInt(GRID_WIDTH), random.nextInt(GRID_HEIGHT));
    }

    private void spawnStar() {
        Random random = new Random();
        star = new Point(random.nextInt(GRID_WIDTH), random.nextInt(GRID_HEIGHT));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            timer.stop();
            return;
        }

        // 移動蛇
        Point head = snake.get(0);
        Point newHead = switch (direction) {
            case "UP" -> new Point(head.x, head.y - 1);
            case "DOWN" -> new Point(head.x, head.y + 1);
            case "LEFT" -> new Point(head.x - 1, head.y);
            case "RIGHT" -> new Point(head.x + 1, head.y);
            default -> head;
        };

        if (newHead.equals(food)) {
            score += 100;
            spawnFood();
        } else {
            snake.remove(snake.size() - 1);
        }

        if (newHead.x < 0 || newHead.x >= GRID_WIDTH || newHead.y < 0 || newHead.y >= GRID_HEIGHT || snake.contains(newHead)) {
            gameOver = true;
        }

        snake.add(0, newHead);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        if (appleImage != null) {
            g.drawImage(appleImage.getImage(), food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
        }

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 10);

        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over", getWidth() / 2 - 50, getHeight() / 2);
        }
    }
}
