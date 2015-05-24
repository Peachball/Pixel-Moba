package game.world.networking;

import game.world.Map;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {

    ServerSocket server;
    ObjectOutputStream output;
    List<Client> clients;
    Map map;
    List<Object> requests;

    public Server(int port) throws IOException {
        server = new ServerSocket(port);
        clients = Collections.synchronizedList(new ArrayList<Client>());
        map = new Map(null);
        map.loadMap("samplemap.png");
        requests = Collections.synchronizedList(new ArrayList<Object>());
    }

    public void start() {
        new Thread(new ClientAccepter(clients, server)).start();
        new Thread(new BroadcastMap(map, clients)).start();
    }
}

class PlayerUpdater implements Runnable {

    List<Client> clients;
    List<Object> requests;

    public PlayerUpdater(List<Client> clients, List<Object> requests) {
        this.clients = clients;
        this.requests = requests;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (clients) {
                for (int counter = 0; counter < clients.size(); counter++) {
                    Object buffer = clients.get(counter).readMessage();
                    if(buffer != null && buffer instanceof Request){
                        synchronized(requests){
                            requests.add(buffer);
                        }
                    }
                }
            }
        }
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
                        i.out.flush();
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
                synchronized (clients) {
                    clients.add(buffer2);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
