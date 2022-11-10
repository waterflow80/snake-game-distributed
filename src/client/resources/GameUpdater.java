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


    private String ipAddressRmi;
    private int port;

    // Locate the registry
    private Registry registry;

    // Get the server object
    private SnakeServer snakeServer;

    public GameUpdater(String ip, int port) throws RemoteException, NotBoundException {
        this.ipAddressRmi = ip;
        this.port = port;
        registry = LocateRegistry.getRegistry(ipAddressRmi, port);
        snakeServer = (SnakeServer) registry.lookup("snakeServer");
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
