import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class App extends JPanel implements Runnable {
    private int ballX, ballY, ballSpeedX = 3, ballSpeedY = 3;
    private int paddle1Y, paddle2Y, paddleSpeed = 5;
    private int paddleWidth = 10, paddleHeight = 60;
    private int player1Score = 0, player2Score = 0;
    private final int maxScore = 5;
    private Thread ballThread, paddleThread;
    private Set<Integer> keysPressed = new HashSet<>(); // Conjunto de teclas presionadas

    public App() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keysPressed.add(e.getKeyCode()); // Agregar la tecla presionada al conjunto
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keysPressed.remove(e.getKeyCode()); // Quitar la tecla cuando se suelta
            }
        });

        resetGame();
    }

    public void startGame() {
        ballThread = new Thread(this::moveBall);
        paddleThread = new Thread(this::movePaddles);
        ballThread.start();
        paddleThread.start();
    }

    private void resetGame() {
        ballX = getWidth() / 2;
        ballY = getHeight() / 2;
        paddle1Y = getHeight() / 2 - paddleHeight / 2;
        paddle2Y = getHeight() / 2 - paddleHeight / 2;
    }

    // Hilo para mover la pelota
    private void moveBall() {
        while (true) {
            ballX += ballSpeedX;
            ballY += ballSpeedY;
            checkCollisions();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Hilo para mover las paletas según las teclas presionadas
    private void movePaddles() {
        while (true) {
            if (keysPressed.contains(KeyEvent.VK_W)) {
                paddle1Y = Math.max(paddle1Y - paddleSpeed, 0);
            }
            if (keysPressed.contains(KeyEvent.VK_S)) {
                paddle1Y = Math.min(paddle1Y + paddleSpeed, getHeight() - paddleHeight);
            }
            if (keysPressed.contains(KeyEvent.VK_UP)) {
                paddle2Y = Math.max(paddle2Y - paddleSpeed, 0);
            }
            if (keysPressed.contains(KeyEvent.VK_DOWN)) {
                paddle2Y = Math.min(paddle2Y + paddleSpeed, getHeight() - paddleHeight);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkCollisions() {
        int panelHeight = getHeight();
        int panelWidth = getWidth();

        // Colisión con bordes superior e inferior
        if (ballY <= 0 || ballY >= panelHeight - 10) {
            ballSpeedY = -ballSpeedY;
        }

        // Colisión con paletas
        if (new Rectangle(ballX, ballY, 10, 10).intersects(new Rectangle(30, paddle1Y, paddleWidth, paddleHeight))) {
            ballSpeedX = Math.abs(ballSpeedX);
        }
        if (new Rectangle(ballX, ballY, 10, 10).intersects(new Rectangle(panelWidth - 40, paddle2Y, paddleWidth, paddleHeight))) {
            ballSpeedX = -Math.abs(ballSpeedX);
        }

        // Verificar si alguien anotó
        if (ballX < 0) {
            player2Score++;
            checkWinner();
            resetGame();
        } else if (ballX > panelWidth) {
            player1Score++;
            checkWinner();
            resetGame();
        }
    }

    private void checkWinner() {
        if (player1Score >= maxScore) {
            showWinner("Player 1 Wins!");
        } else if (player2Score >= maxScore) {
            showWinner("Player 2 Wins!");
        }
    }

    private void showWinner(String message) {
        JOptionPane.showMessageDialog(this, message);
        player1Score = 0;
        player2Score = 0;
        resetGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar paletas y pelota
        g.setColor(Color.WHITE);
        g.fillRect(30, paddle1Y, paddleWidth, paddleHeight);
        g.fillRect(getWidth() - 40, paddle2Y, paddleWidth, paddleHeight);
        g.fillOval(ballX, ballY, 10, 10);

        // Dibujar puntaje
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player 1: " + player1Score, 50, 30);
        g.drawString("Player 2: " + player2Score, getWidth() - 150, 30);
    }

    @Override
    public void run() {
        // Este método queda sin uso porque los hilos para la pelota y las paletas están separados
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        App game = new App();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.startGame();
    }
}
