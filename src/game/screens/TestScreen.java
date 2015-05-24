package game.screens;

import game.world.Map;
import game.world.networking.Client;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

public class TestScreen extends Screen implements Runnable {

    Client client;
    float xPos;
    float yPos;

    public TestScreen(RenderWindow window, String ip) throws IOException {
        super(window);
        client = new Client(ip, 1123);
        xPos = 0;
        yPos = 0;
    }

    @Override
    public Screen update() {
        window.clear();
        Object buffer = null;
        while (buffer == null) {
            buffer = client.readMessage();
        }
        Map map = (Map) buffer;
        map.xPos = (int) xPos;
        map.yPos = (int) yPos;
        try {
            map.loadMap("samplemap.png");
        } catch (IOException ex) {
            Logger.getLogger(TestScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        map.linkWindow(window);
        map.display();

        window.display();

        Event event;
        while ((event = window.pollEvent()) != null) {
            switch (event.type) {
                case KEY_PRESSED:
                    switch (event.asKeyEvent().key) {
                        case Q:
                            break;
                        case W:
                            break;
                        case E:
                            break;
                        case R:
                            break;
                        case D:
                            break;
                        case F:
                            break;
                        case SPACE:
                            break;
                    }
                    break;
            }
        }
        if (Mouse.getPosition(window).x <= 0) {
            xPos++;
        }
        if (Mouse.getPosition(window).x >= window.getSize().x - 10) {
            xPos--;
        }
        if (Mouse.getPosition(window).y <= 0) {
            yPos++;
        }
        if (Mouse.getPosition(window).y >= window.getSize().y - 10) {
            yPos--;
        }
        return this;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
