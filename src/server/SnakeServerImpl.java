package server;

import java.awt.Color;
import java.rmi.RemoteException;
import java.util.List;

import client.snake.SnakeLinkedList;
import server.resources.Apple;
import server.resources.Coordinates;
import server.utils.IdGenerator;
import server.resources.Client;
import server.resources.Clients;
import server.resources.GameSnakes;

public class SnakeServerImpl implements SnakeServer{


    @Override
    /**
     * Add the client to the list of clients, and return an id to the client*/
    public int connect(SnakeLinkedList snake) throws RemoteException {
        int id = IdGenerator.getId();
        Client client = new Client(id, snake);
        Clients.addClient(client); // Adding a client to the list of the clients
        GameSnakes.addGameSnake(id, snake); // Adding the tuple (id, snake) to the GameSnake Map
        //System.out.println("The received snake is : " + snake);
        return id;
    }

    @Override
    /**
     * Return all the opponent Snakes*/
    public List<SnakeLinkedList> getOpponentSnakes(int exceptId) throws RemoteException {
        return GameSnakes.getAllSnakesExcept(exceptId);
    }

    @Override
    /**
     * Updating the state of the client's snakes*/
    public void updateSnakes(int clientId, SnakeLinkedList snake) throws RemoteException{
        GameSnakes.updateClientSnake(clientId, snake);
    }

    @Override
    /**
     * Return the current apple position*/
    public Coordinates getApplePosition() throws RemoteException{
        return Apple.getAppleCoordinates();
    }

    @Override
    /**
     * Updating (changing) the apple position in the game (after being eaten)*/
    public void updateApple() throws RemoteException{
        Apple.updateAppleCoordinates();
    }

    @Override
    /**
     * Removing the player with the specified id from the game (removing his snake)*/
    public void removePlayer(int clientId) throws RemoteException {
        GameSnakes.removeGameSnakeByClientId(clientId);
    }

    @Override
    public Coordinates getHeadCoordinates() throws RemoteException {
        return GameSnakes.generateNewCoordinates();
    }

    /**
     * Return a unique color that has not been used*/
    @Override
    public Color getSnakeColor() throws RemoteException {
        return GameSnakes.generateColor();
    }
}
