package server.resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains information about current clients*/
public class Clients {
    public static List<Client> clients;

    public static void addClient(Client client){
        if (clients == null)
            clients = new ArrayList<>();
        clients.add(client);

    }
}
