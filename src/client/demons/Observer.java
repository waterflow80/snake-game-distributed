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

/**
 * This class will be working continuously to check possible changes from
 * both client and server side, And make the changes to the concerned resources*/
public class Observer implements Runnable{

    static final int DELAY = 100; // The interval between one frame and the other

    // Locate the registry
    private Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);

    // Get the server object
    private SnakeServer snakeServer = (SnakeServer) registry.lookup("snakeServer");

    public Observer() throws RemoteException, NotBoundException {
    }

    @Override
    public void run() {
        while (true){
            try {
                ////// FETCHING DATA //////
                List<SnakeLinkedList> opponentSnakes = snakeServer.getOpponentSnakes(Id.myId);
                if (!opponentSnakes.isEmpty()) {
                    // Updating the list of opponent snakes
                    GamePanel.opponentSnakes = opponentSnakes;
                }
                //System.out.println("The n° of opponent snakes in the gameOAnel is: " + GamePanel.opponentSnakes.size());

                ////// SENDING DATA //////


                // Waiting for a frame transition time to update
                Thread.sleep(DELAY);
            } catch (
                    InterruptedException e) {
                throw new RuntimeException(e);
            }catch (RemoteException re){
                System.out.println("Observer: Remote exception thrown !!");
            }
        }
    }


}
