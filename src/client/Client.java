package client;

import java.awt.Color;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.demons.Observer;
import client.gui.GameFrame;
import client.resources.Id;
import client.snake.Node;
import client.snake.SnakeLinkedList;
import server.SnakeServer;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {



        // Locate the registry
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);

        // Get the reference of the exported object from RMI Registry
        SnakeServer snakeServer = (SnakeServer) registry.lookup("snakeServer");

        SnakeLinkedList mainSnake = new SnakeLinkedList(new Node(0,0, Color.BLUE)); // The snake of the current player



        // Connecting to the server
        int id = snakeServer.connect(mainSnake);

        Id.myId = id;

        // Starting the observer object
        Thread observer = new Thread(new Observer());
        observer.start();

        Thread.sleep(1000);
        new GameFrame();

        // waiting for the observer to complete the job
        observer.join();
    }
}
