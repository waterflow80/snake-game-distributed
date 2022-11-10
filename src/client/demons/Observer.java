package client.demons;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import client.gui.GamePanel;
import client.opponent.OpponentSnakes;
import client.resources.Id;
import client.snake.SnakeLinkedList;
import server.SnakeServer;
import server.resources.Coordinates;

/**
 * This class will be working continuously to check possible changes from
 * both client and server side, And make the changes to the concerned resources*/
public class Observer implements Runnable{

    static final int DELAY = 100; // The interval between one frame and the other

    public String ipAddressRmi;
    public Integer port;

    private Registry registry;
    private SnakeServer snakeServer;

    public Observer(String ip, Integer port) throws RemoteException, NotBoundException {
        this.ipAddressRmi = ip;
        this.port = port;
        registry = LocateRegistry.getRegistry(ipAddressRmi, port);
        snakeServer = (SnakeServer) registry.lookup("snakeServer");
    }

    // Locate the registry


    // Get the server object


    @Override
    public void run() {
        while (true){
            try {
                ////// FETCHING APPLE COORDINATES /////
                Coordinates cord = snakeServer.getApplePosition();
                GamePanel.apple.setX(cord.getX());
                GamePanel.apple.setY(cord.getY());

                ////// FETCHING OPPONENT SNAKES //////
                List<SnakeLinkedList> opponentSnakes = snakeServer.getOpponentSnakes(Id.myId);
                if (!opponentSnakes.isEmpty()) {
                    // Updating the list of opponent snakes
                    //GamePanel.opponentSnakes = opponentSnakes;
                    System.out.println("Fetched opponent snakes: " + opponentSnakes.size());
                    GamePanel.opponentSnakes.clear(); // deleting the old list of snakes
                    GamePanel.opponentSnakes.addAll(opponentSnakes); // updating the list of opponents
                }
                //System.out.println("The n° of opponent snakes in the gameOAnel is: " + GamePanel.opponentSnakes.size());

                ////// SENDING DATA (SENDING MY SNAKE STATE TO THE SERVER)//////
                if (GamePanel.mainSnake != null){
                    snakeServer.updateSnakes(Id.myId, GamePanel.mainSnake);
                }


                // Waiting for a frame transition time to update
                //Thread.sleep(DELAY);
            } catch (RemoteException re){
                System.out.println("Observer: Remote exception thrown !!");
            }
        }
    }


}
