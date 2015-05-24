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
                while(message == null){
                    message = buffer2.readMessage();
                }
                if(message instanceof Player){
                    
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
