package server.resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import client.snake.SnakeLinkedList;

/**
 * This class contains the opponentSnakes that will be sent to the
 * requested user*/
public class GameState implements Serializable {
    private List<SnakeLinkedList> opponentSnakes;

    public void setOpponentSnakes(List<SnakeLinkedList> snakes){
        this.opponentSnakes = snakes;
    }

    public List<SnakeLinkedList> getOpponentSnakes(){
        return opponentSnakes;
    }

}
