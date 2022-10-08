package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.GamePanel;
import common.SnakeLinkedList;

public interface SnakeGameInterface extends Remote{
	public int getId() throws RemoteException;
	 public GamePanel requestGame(int playerId) throws RemoteException;
	 public GamePanel move(SnakeLinkedList snake, char direction) throws RemoteException;
}
