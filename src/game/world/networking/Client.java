package game.world.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Client implements Runnable {

    final Object inputLock;
    List<Object> input;
    ObjectOutputStream out;
    ObjectInputStream in;
    Socket socket;

    public Client(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        inputLock = new Object();
        input = new LinkedList<Object>();
        start();
    }

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        inputLock = new Object();
        input = new LinkedList<Object>();
        start();
    }

    public void start() {
        new Thread(this).start();
    }

    /**
     * Returns the most recent message in the list of messages Will return null
     * if no messages are in list
     *
     * @return
     */
    public Object readMessage() {
        synchronized (inputLock) {
            if (!input.isEmpty()) {
                Object sdf = input.get(0);
                input.remove(0);
                return sdf;
            }
        }
        return null;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
                try {
                    Object buffer = in.readObject();
                    synchronized(inputLock){
                        input.add(buffer);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(0);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
        }
    }
}
