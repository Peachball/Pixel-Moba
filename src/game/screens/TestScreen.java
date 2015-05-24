package game.screens;

import game.world.Map;
import game.world.networking.Client;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.RenderWindow;

public class TestScreen extends Screen implements Runnable {
    
    Client client;
    
    public TestScreen(RenderWindow window, String ip) throws IOException {
        super(window);
        client = new Client(ip,1123);
    }
    
    @Override
    public Screen update() {
        window.clear();
        Object buffer = null;
        while (buffer == null) {
            buffer = client.readMessage();
        }
        Map map = (Map) buffer;
        
        map.linkWindow(window);
        try {
            map.display();
        } catch (IOException ex) {
            Logger.getLogger(TestScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        window.display();
        return this;
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
