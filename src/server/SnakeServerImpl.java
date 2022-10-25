package server;

import java.rmi.RemoteException;
import java.util.List;

import client.snake.SnakeLinkedList;
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
}
