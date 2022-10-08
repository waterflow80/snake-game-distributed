package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.GamePanel;

public class Client {

	public static void main(String[] args) {
		try {
			// Locate the registry 
			Registry registry = LocateRegistry.getRegistry("127.0.0.1",9100);
			
			// Get the reference of the exported object from RMI Registry
			SnakeGameInterface snakeGame = (SnakeGameInterface) registry.lookup("sg");

			GamePanel gpGamePanel = snakeGame.requestGame(0);
			
			
			
			
		}catch(Exception e) {
			System.out.println("Client side error..." + e);
		}
	}

	}

