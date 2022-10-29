package client.snake;

import java.awt.Color;

public class MainSnake {

    // The position(coordinates) of the snake
    public static int x;
    public static int y;

    // The snake of the current player
    public static SnakeLinkedList mainSnake = new SnakeLinkedList(new Node(x,y,Colors.snakeHeadColor));


}
