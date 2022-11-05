package server.resources;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import client.snake.SnakeLinkedList;

public class GameSnakes {
    // The gameSnakes contains all client's snakes for each client id
    private static Map<Integer, SnakeLinkedList> gameSnakes = new HashMap<>();
    private static List<Coordinates> initialSnakeHeadCoordinates = new ArrayList<>(); // ( coordinates)
    private static Random random = new Random();

    private static List<Color> snakeColors = new ArrayList<>(); // snake colors

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
        System.out.println("Snakes before: " + gameSnakes.size());
        gameSnakes.remove(id);
        System.out.println("Snakes after: " + gameSnakes.size());
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
       // System.out.println("All Snakes Except are : " + snakes.size());
        return snakes;
    }

    /**
     * Update the snake which is mapped with the specified clientId*/
    public static void updateClientSnake(int clientId, SnakeLinkedList snake){
        if (snake != null)
            gameSnakes.put(clientId, snake);
        //System.out.println("Snake N°: (Y=" + snake.getHead().getY() + ",X=" + snake.getHead().getX() + ")"); // Testing purposes
    }

    /**Generating a new coordinates, different from those in the initialSnakeHeadCoordinates and saving them
     * */
    public static Coordinates generateNewCoordinates(){
        // Random coordinates that does not exist in the Map
        boolean exist = true;
        int x;
        int y;
        x = random.nextInt((int) GuiInfo.WIDTH / GuiInfo.UNIT_SIZE) * GuiInfo.UNIT_SIZE;
        y = random.nextInt((int) GuiInfo.WIDTH / GuiInfo.UNIT_SIZE) * GuiInfo.UNIT_SIZE;
        while (exist){
            exist = false;
            for (Coordinates c : initialSnakeHeadCoordinates){
                if (c.getX() == x && c.getY() == y){
                    exist = true;
                    x = random.nextInt((int) GuiInfo.WIDTH / GuiInfo.UNIT_SIZE) * GuiInfo.UNIT_SIZE;
                    y = random.nextInt((int) GuiInfo.WIDTH / GuiInfo.UNIT_SIZE) * GuiInfo.UNIT_SIZE;
                }

            }
        }
        Coordinates cord = new Coordinates(x,y);
        // saving new coordinates to the map
        initialSnakeHeadCoordinates.add(cord);

        // return the new coordinates
        return cord;
    }

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Check weather the color exists or not*/
    private static boolean isColorExist(int r, int g, int b){
        for (Color c: snakeColors){
            if (c.getBlue() == b || c.getGreen() == g || c.getRed() == r){
                return true;
            }

        }
        return false;
    }

    /**
     * Generating a new color that hasn't been used before*/
    public static Color generateColor(){
        int r = getRandomNumber(0, 255);
        int g = getRandomNumber(0, 255);
        int b = getRandomNumber(0, 255);

        while (isColorExist(r,g,b)){
            r = getRandomNumber(0, 255);
            g = getRandomNumber(0, 255);
            b = getRandomNumber(0, 255);
        }

        Color color = new Color(r,g,b);
        snakeColors.add(color); // adding the new color to the list
        return color;


    }

}
