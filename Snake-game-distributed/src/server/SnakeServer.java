package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SnakeServer {
	static Registry registry;
	public static void main(String[] args) {
		try {

			// Set hostname for the server using JavaProperty
			System.setProperty("java.rmi.server.hostname", "127.0.0.1");
			System.out.println("Server has been started..");
			
			
			// Let's create some SnakeGameInterface objects 
			SnakeGameImpl sg1 = new SnakeGameImpl();
			
			// Export sg1 object using UnicastRemoteObject class
			SnakeGameInterface stub1 = (SnakeGameInterface) UnicastRemoteObject.exportObject(sg1, 0);
			
			
			// Get the registry to register the object
			registry = LocateRegistry.getRegistry("127.0.0.1", 9100);
			
			
			registry.bind("sg", stub1);
			
			
			
		}catch(Exception e) {
			System.out.println("Some server error..." + e);
		}
		System.out.println("Out of the server");
	}

}
