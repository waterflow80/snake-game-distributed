package client.opponent;

import java.util.List;

import client.snake.SnakeLinkedList;

public class OpponentSnakes {
    public static List<SnakeLinkedList> opponentSnakes;

    /**
     * Updating the opponent snakes received form the server*/
    public static void updateOpponentSnakes(List<SnakeLinkedList> snakes){
        opponentSnakes = snakes;
    }
}
