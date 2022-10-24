package server.resources;

import client.snake.SnakeLinkedList;

public class Client {
    private int id;
    private SnakeLinkedList snake;

    public Client(int id, SnakeLinkedList snake) {
        this.id = id;
        this.snake = snake;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SnakeLinkedList getSnake() {
        return snake;
    }

    public void setSnake(SnakeLinkedList snake) {
        this.snake = snake;
    }
}
