package server;

import java.rmi.RemoteException;


import common.GamePanel;
import common.SnakeLinkedList;

public class SnakeGameImpl implements SnakeGameInterface {
	
	GamePanel gamePanel1 = new GamePanel();
	
	
	


	

	@Override
	/**
	 * Return the game panel to the player that contains the updated state.
	 * If the player is new, create all needed elements for him in the game server*/
	public GamePanel requestGame(int playerId) throws RemoteException {
		return this.gamePanel1;
	}





	@Override
	public int getId() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public GamePanel move(SnakeLinkedList snake, char direction) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
