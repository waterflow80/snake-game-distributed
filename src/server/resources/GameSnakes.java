package server.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import client.snake.SnakeLinkedList;

public class GameSnakes {
    // The gameSnakes contains all client's snakes for each client id
    private static Map<Integer, SnakeLinkedList> gameSnakes = new HashMap<>();

    /**
     * Add a new game snake to the gameSnakes*/
    public static void addGameSnake(Integer clientId, SnakeLinkedList clientSnake){
        gameSnakes.put(clientId, clientSnake);
    }

    /**
     * Return the snake to which the specified key is mapped*/
    public static SnakeLinkedList getSnakeById(Integer id){
        return gameSnakes.get(id);
    }

    /**
     * Return all the snakes currently in the game*/
    public static List<SnakeLinkedList> getAllSnakes(){
        if (gameSnakes.isEmpty())
            return null;

        List<SnakeLinkedList> snakes = new ArrayList<>(gameSnakes.values());

        return snakes;
    }

    /**
     * Remove the tuple (clientId, Snake) from the gamesSnakes
     */
    public static void removeGameSnakeByClientId(Integer id){
        gameSnakes.remove(id);
    }

    /**
     * Check if the snake which is mapped by the given id exists or not*/
    public static boolean isSnakeExists(Integer clientId){
        return gameSnakes.containsKey(clientId);
    }

    /**
     * Return all snakes except the one that is mapped by the specified id*/
    public static List<SnakeLinkedList> getAllSnakesExcept(Integer clientId){
        if (gameSnakes.isEmpty())
            return null;

        List<SnakeLinkedList> snakes = new ArrayList<>();
        for (Map.Entry<Integer, SnakeLinkedList> entry: gameSnakes.entrySet()){
            if(entry.getKey() != clientId)
                snakes.add(entry.getValue());
        }
       // System.out.println("GamesSnakes: Snakes are: ");
       // snakes.forEach(System.out::println);
        return snakes;
    }

    /**
     * Update the snake which is mapped with the specified clientId*/
    public static void updateClientSnake(int clientId, SnakeLinkedList snake){
        gameSnakes.put(clientId, snake);
    }

}
