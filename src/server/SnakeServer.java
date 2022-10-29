package server;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import client.snake.SnakeLinkedList;
import server.resources.Coordinates;

public interface SnakeServer extends Remote {
    public int connect(SnakeLinkedList snake) throws RemoteException; // connecting to the server by adding his own snake and gets an id back
    public List<SnakeLinkedList> getOpponentSnakes(int exceptId) throws RemoteException;
    public void updateSnakes(int clientId, SnakeLinkedList snake) throws RemoteException;

    public Coordinates getApplePosition() throws RemoteException;

    public void updateApple() throws RemoteException;

    public void removePlayer(int clientId) throws RemoteException;

    public Coordinates getHeadCoordinates() throws RemoteException;

    public Color getSnakeColor() throws RemoteException;


}
