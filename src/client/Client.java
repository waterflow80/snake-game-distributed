package client;

import java.awt.Color;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.demons.Demon;
import client.demons.Observer;
import client.gui.GameFrame;
import client.gui.GamePanel;
import client.resources.GameUpdater;
import client.resources.Id;
import client.snake.Colors;
import client.snake.MainSnake;
import server.SnakeServer;
import server.resources.Coordinates;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {



        // Locate the registry
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);

        // Get the reference of the exported object from RMI Registry
        SnakeServer snakeServer = (SnakeServer) registry.lookup("snakeServer");

        //SnakeLinkedList mainSnake = new SnakeLinkedList(new Node(0,0, Color.BLUE)); // The snake of the current player

        // Requesting Unique coordinates for the head from the server
        Coordinates cord = snakeServer.getHeadCoordinates();
        MainSnake.x = cord.getX();
        MainSnake.y = cord.getY();

        // Requesting the color of the snake from the server
        Color color = snakeServer.getSnakeColor();
        System.out.println("My color is: " + color);
        Colors.snakeHeadColor = color;
        Colors.snakeBodyColor = color.brighter();

        GamePanel.bodyColor = color.brighter();

        // Connecting to the server to start the game
        int id = snakeServer.connect(MainSnake.mainSnake);

        // Saving the id that I got from the server
        Id.myId = id;


        // Starting the observer thread object
        Thread observer = new Thread(new Observer());
        Demon.observer = observer;
        Demon.observer.start();


        Thread.sleep(10); // Waiting few moments until the observer has completed downloading all components from the server

        // Starting the GUI game
        new GameFrame();
        Thread.sleep(1000); // To be removed (for testing purposes only)

        System.out.println("Waiting for observer");

        // waiting for the observer to complete the job
        Demon.observer.join();

        System.out.println("---- Thread joined ----");
    }
}
