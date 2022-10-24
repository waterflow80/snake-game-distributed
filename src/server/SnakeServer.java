package server;

import java.rmi.Remote;
import java.util.List;

import client.snake.SnakeLinkedList;

public interface SnakeServer extends Remote {
    public int connect(SnakeLinkedList snake); // connecting to the server by adding his own snake and gets an id back
    public List<SnakeLinkedList> getOpponentSnakes();
}
