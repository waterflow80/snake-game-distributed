package client.resources;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
        snakeServer.removePlayer(myId);
    }


}
