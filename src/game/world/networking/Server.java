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

public class Server implements Runnable {

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
        new Thread(new RequestDownloader(clients, requests)).start();
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (requests) {
                if (!requests.isEmpty()) {
                    Object request = requests.get(0);
                    requests.remove(0);
                    if (request instanceof Request) {
                        Player player = map.getByID(((Request) request).id);
                        if (player != null) {
                            switch (((Request) request).keycode) {
                                case 0:
                                    player.useQ((int) ((Request) request).x, (int) ((Request) request).y);
                                    break;
                                case 1:
                                    player.useW((int) ((Request) request).x, (int) ((Request) request).y);
                                    break;
                                case 2:
                                    player.useE((int) ((Request) request).x, (int) ((Request) request).y);
                                    break;
                                case 3:
                                    player.useR((int) ((Request) request).x, (int) ((Request) request).y);
                                    break;
                                case 4:
                                    player.useD((int) ((Request) request).x, (int) ((Request) request).y);
                                    break;
                                case 5:
                                    player.useF((int) ((Request) request).x, (int) ((Request) request).y);
                                    break;
                                case 6:
                                    player.setTarget((int)((Request)request).x, (int)((Request)request).y);
                                    break;

                            }
                        }
                    }
                    if (request instanceof Player) {
                        map.addSprite(((Player) request));
                    }
                }
            }
        }
    }
}

class RequestDownloader implements Runnable {

    List<Client> clients;
    List<Object> requests;

    public RequestDownloader(List<Client> clients, List<Object> requests) {
        this.clients = clients;
        this.requests = requests;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (clients) {
                for (int counter = 0; counter < clients.size(); counter++) {
                    Object buffer = clients.get(counter).readMessage();
                    if (buffer != null) {
                        synchronized (requests) {
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
