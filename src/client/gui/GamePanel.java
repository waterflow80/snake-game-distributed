package client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import client.resources.GameUpdater;
import client.resources.Id;
import client.snake.Colors;
import client.snake.MainSnake;
import client.snake.SnakeLinkedList;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import client.snake.Node;
import client.snake.SnakeUtils;

public class GamePanel extends JPanel implements ActionListener {
    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    static final int UNIT_SIZE = 25;

    final Color appleColor = Color.red;
    public static Color bodyColor = null;

    static final int DELAY = 100; // The interval between one frame and the other

    static Grid screen = new Grid(WIDTH, HEIGHT, UNIT_SIZE);

    // The list of all opponent snakes
    public static List<SnakeLinkedList> opponentSnakes;

    public static SnakeLinkedList mainSnake = MainSnake.mainSnake;
    //int snakeSize = mainSnake.getSize();
    int applesEaten;
    public static Node apple = new Node();
    char direction = 'R'; // the direction where the snake is heading (R, L, U, D)
    boolean running = true;
    Timer timer;
    Random random;

    GameUpdater gameUpdater = new GameUpdater(); // The object that will be charged to update the apple position in
    // the server


    public GamePanel() throws NotBoundException, RemoteException {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() throws RemoteException {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            draw(g);
        } catch (
                InterruptedException |
                RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Drawing the snake to the screen*/
    public void drawSnake(SnakeLinkedList snake, Graphics g) throws InterruptedException {
        //System.out.println("Drawing snake: " + snake);
        Thread.sleep(600); //Used for testing purposes
        // Drawing the head of the snake
        if (snake.getHead() == null)
            return; // the snake has no elements to draw
        g.setColor(snake.getHead().getColor());
        g.fillRect(snake.getHead().getX(), snake.getHead().getY(), screen.getUNIT_SIZE(), screen.getUNIT_SIZE());

        // Drawing the rest of the body parts of the snake
        g.setColor(bodyColor);
        Node part = snake.getHead();
        while (part.getNext() != null) {
            //System.out.println("Printing the rest of the parts");
            part = part.getNext();
            g.fillRect(part.getX(), part.getY(), screen.getUNIT_SIZE(), screen.getUNIT_SIZE());
        }
    }

    public void draw(Graphics g) throws InterruptedException, RemoteException {

        if (running) {
            // Drawing the apple
            g.setColor(appleColor); // Setting the standard color to red; From now on everything drew will be in red
            g.fillOval(apple.getX(), apple.getY(), screen.getUNIT_SIZE(), screen.getUNIT_SIZE());

            // Drawing the main snake (the current player's snake)
            drawSnake(mainSnake, g);

            // Drawing the opponent snakes if existed
            if (opponentSnakes != null){
                for (SnakeLinkedList snake: opponentSnakes){
                    drawSnake(snake, g);
                }
            }

            // Score text
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (screen.getWIDTH() -metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
        }else {
            gameOver(g);
        }
    }

    /**
     * Create a new apple in a random position*/
    public void newApple() throws RemoteException {
        gameUpdater.updateApple();
    }

    /**
     * Perform the move and make the necessary changes*/
    public void move() {
        SnakeUtils.move(mainSnake, direction, screen.getUNIT_SIZE());
    }

    /**
     * Check whether the apple should be eaten or not*/
    public void checkApple() throws RemoteException {
        if ((mainSnake.getHead().getX() == apple.getX()) && mainSnake.getHead().getY() == apple.getY()) {
            SnakeUtils.addPart(mainSnake,direction, screen);
            applesEaten++;
            newApple();
            //System.out.println("snake size: " + mainSnake.getSize());
        }
    }

    /**
     * Check whether the snake collides or not.
     * Results in Game Over*/
    public void checkCollision() {
        // Check collision with the screen border or with the snake itself
        if (SnakeUtils.isSnakeCollides(mainSnake, screen)) {
            running = false; // Stop the game
        }

        // Check if the snake collides with opponent snakes
        if(SnakeUtils.isSnakeCollidesWithOtherSnakes(mainSnake, opponentSnakes)){
            running = false; // Stop the game
        }


        if (!running) {
            timer.stop();
        }
    }

    /**
     * Stop the game and display the Game Over Screen*/
    public void gameOver(Graphics g) throws RemoteException {
        // Telling the server to remove me from the game
        gameUpdater.leaveGame(Id.myId);

        // Score text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (screen.getWIDTH() -metrics1.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());

        // Game over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (screen.getWIDTH() -metrics2.stringWidth("Game Over"))/2, screen.getHEIGHT()/2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // The game process
        if(running) {
            move();
            try {
                checkApple();
            } catch (
                    RemoteException ex) {
                throw new RuntimeException(ex);
            }
            checkCollision();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R')
                        direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L')
                        direction = 'R';
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D')
                        direction = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U')
                        direction = 'D';
                    break;
            }
        }
    }
}
