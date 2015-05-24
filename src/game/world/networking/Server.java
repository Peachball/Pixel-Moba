package game.world.networking;

import game.world.Map;
import game.world.champions.Player;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    ServerSocket server;
    ObjectOutputStream output;
    List<Client> clients;
    Map map;

    public Server(int port) throws IOException {
        server = new ServerSocket(port);
        clients = Collections.synchronizedList(new ArrayList<Client>());
        map = new Map(null);
    }

    public void start() {
        new Thread(new ClientAccepter(clients, server)).start();
        new Thread(new BroadcastMap(map, clients)).start();
    }
}

class BroadcastMap implements Runnable {

    Map map;
    List<Client> clients;

    public BroadcastMap(Map map, List<Client> clients) {
        this.map = map;
        this.clients = clients;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (clients) {
                for (Client i : clients) {
                    try {
                        i.out.writeObject(map);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

}

class ClientAccepter implements Runnable {

    List<Client> clients;
    ServerSocket socket;

    public ClientAccepter(List<Client> clients, ServerSocket socket) {
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket buffer = socket.accept();
                Client buffer2 = new Client(buffer);
                clients.add(buffer2);
                Object message = null;
                while (message == null) {
                    message = buffer2.readMessage();
                }
                if (message instanceof Player) {

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
