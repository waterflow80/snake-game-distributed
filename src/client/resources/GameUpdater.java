package client.resources;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.demons.Demon;
import client.demons.Observer;
import server.SnakeServer;

/**
 * Will be charged of sending game updates to the server*/
public class GameUpdater {

    // Locate the registry
    private Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);

    // Get the server object
    private SnakeServer snakeServer = (SnakeServer) registry.lookup("snakeServer");

    public GameUpdater() throws RemoteException, NotBoundException {

    }

    /**
     * Sending a request to the server to ask him to update (change) the apple position*/
    public void updateApple() throws RemoteException {
        snakeServer.updateApple();
    }

    /**
     * Send a request to the server to leave the game (Game over)*/
    public void leaveGame(int myId) throws RemoteException {
        // killing the observer thread, so he won't send extra information to the server
        Demon.observer.interrupt();

        // Request the server to remove myself from the server
        snakeServer.removePlayer(myId);
    }


}
