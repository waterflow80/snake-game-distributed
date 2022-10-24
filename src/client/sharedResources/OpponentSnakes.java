package client.sharedResources;

import java.util.ArrayList;
import java.util.List;

import client.snake.SnakeLinkedList;

/**
 * This class contains a list of opponent snakes (other than the current snake)*/
public class OpponentSnakes {
    public static List<SnakeLinkedList> opponentSnakes = new ArrayList<>();

    public static void addSnake(SnakeLinkedList snake){
        opponentSnakes.add(snake);
    }


}
