package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Server {
    public static void main(String[] args){

        try {
            // Set hostname for the server using JavaProperty
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            System.out.println("Server has been started..");

            // Create an instance of the SnakeServer
            SnakeServer snakeServer = new SnakeServerImpl();

            // Export the SnakeServer
            SnakeServer stub = (SnakeServer) UnicastRemoteObject.exportObject(snakeServer, 0);

            // Get the registry to register the object
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);

            // Register the exported class in RMI registry with some name
            registry.bind("snakeServer", stub);

            System.out.println("Exporting and binding of objects has completed");
        }catch (Exception e){
            System.out.println("Server: Some server problem: " + e);
        }
    }
}
