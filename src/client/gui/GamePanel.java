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
import client.snake.SnakeLinkedList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import client.snake.Node;
import client.snake.SnakeUtils;

public class GamePanel extends JPanel implements ActionListener {
    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    static final int UNIT_SIZE = 25;

    final Color headColor = new Color(0,240,45);
    final Color appleColor = Color.red;
    final Color bodyColor = new Color(0,180,45);

    static final int DELAY = 100; // The interval between one frame and the other

    static Grid screen = new Grid(WIDTH, HEIGHT, UNIT_SIZE);


    SnakeLinkedList mainSnake = new SnakeLinkedList(new Node(0,0,headColor)); // The snake of the current player
    //int snakeSize = mainSnake.getSize();
    int applesEaten;
    Node apple = new Node();
    char direction = 'R'; // the direction where the snake is heading (R, L, U, D)
    boolean running = true;
    Timer timer;
    Random random;


    public GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }


    /**
     * Drawing the snake to the screen*/
    public void drawSnake(SnakeLinkedList snake, Graphics g){
        // Drawing the head of the snake
        if (mainSnake.getHead() == null)
            return; // the snake has no elements to draw
        g.setColor(mainSnake.getHead().getColor());
        g.fillRect(mainSnake.getHead().getX(), mainSnake.getHead().getY(), screen.getUNIT_SIZE(), screen.getUNIT_SIZE());

        // Drawing the rest of the body parts of the snake
        g.setColor(bodyColor);
        Node part = mainSnake.getHead();
        while (part.getNext() != null) {
            //System.out.println("Printing the rest of the parts");
            part = part.getNext();
            g.fillRect(part.getX(), part.getY(), screen.getUNIT_SIZE(), screen.getUNIT_SIZE());
        }
    }

    public void draw(Graphics g) {

        if (running) {
            // Drawing the apple
            g.setColor(appleColor); // Setting the standard color to red; From now on everything drew will be in red
            g.fillOval(apple.getX(), apple.getY(), screen.getUNIT_SIZE(), screen.getUNIT_SIZE());

            // Drawing the main snake (the current player's snake)
            drawSnake(mainSnake, g);

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
    public void newApple() {
        apple.setX(random.nextInt((int) screen.getWIDTH()/UNIT_SIZE) * UNIT_SIZE);
        apple.setY(random.nextInt((int) screen.getHEIGHT()/UNIT_SIZE) * UNIT_SIZE);
    }

    /**
     * Perform the move and make the necessary changes*/
    public void move() {
        SnakeUtils.move(mainSnake, direction, screen.getUNIT_SIZE());
    }

    /**
     * Check whether the apple should be eaten or not*/
    public void checkApple() {
        if ((mainSnake.getHead().getX() == apple.getX()) && mainSnake.getHead().getY() == apple.getY()) {
            SnakeUtils.addPart(mainSnake,direction, screen, bodyColor);
            applesEaten++;
            newApple();
            //System.out.println("snake size: " + mainSnake.getSize());
        }
    }

    /**
     * Check whether the snake collides or not.
     * Results in Game Over*/
    public void checkCollision() {
        if (SnakeUtils.isSnakeCollides(mainSnake, screen)) {
            running = false; // Stop the game
        }


        if (!running) {
            timer.stop();
        }
    }

    /**
     * Stop the game and display the Game Over Screen*/
    public void gameOver(Graphics g) {
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
    public void actionPerformed(ActionEvent e ) {
        // The game process
        if(running) {
            move();
            checkApple();
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
