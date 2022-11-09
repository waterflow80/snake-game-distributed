package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import server.rmi.RmiLauncher;

public class Server {
    public static void main(String[] args) throws InterruptedException {

        // Check how many arguments were passed in
        if (args.length < 2) {
            System.out.println("Destination IP Address, and Port Required ! For RMI Registry");
            System.exit(0);
        }

        final String ipAddressServer = args[0]; //ip address of the rmi registry
        final int port = Integer.parseInt(args[1]); // the port of the rmi registry

        System.out.println("Launching rmi Registry....");
        Thread rmiThread = new Thread(new RmiLauncher(port));
        rmiThread.start();
        Thread.sleep(100);

        System.out.println("Rmi registry successfully started");

        try {
            // Set hostname for the server using JavaProperty
            System.setProperty("java.rmi.server.hostname", ipAddressServer);
            System.out.println("Server has been started..");

            // Create an instance of the SnakeServer
            SnakeServer snakeServer = new SnakeServerImpl();

            // Export the SnakeServer
            SnakeServer stub = (SnakeServer) UnicastRemoteObject.exportObject(snakeServer, 0);

            // Get the registry to register the object
            Registry registry = LocateRegistry.getRegistry(ipAddressServer, port);

            // Register the exported class in RMI registry with some name
            registry.bind("snakeServer", stub);

            System.out.println("Exporting and binding of objects has completed");

            rmiThread.join();
        }catch (Exception e){
            System.out.println("Server: Some server problem: " + e);
        }
    }
}
