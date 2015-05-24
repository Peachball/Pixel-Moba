package game.screens;

import game.world.Map;
import game.world.champions.God;
import game.world.champions.Player;
import game.world.networking.Client;
import game.world.networking.Request;
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
    long id;

    public TestScreen(RenderWindow window, String ip) throws IOException {
        super(window);
        God player = new God();
        client = new Client(ip, 1123);
        xPos = 0;
        yPos = 0;
        id = player.id;
        client.sendMessage(player);
    }

    @Override
    public Screen update() {
        window.clear();
        Object buffer = null;
        while (buffer == null) {
            buffer = client.getMap();
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
        Player player = map.getByID(id);
        Event event;
        while ((event = window.pollEvent()) != null) {
            switch (event.type) {
                case KEY_PRESSED:
                    switch (event.asKeyEvent().key) {
                        case Q: {
                            try {
                                client.sendMessage(new Request(id, 0, Mouse.getPosition(window).x - map.xPos, Mouse.getPosition(window).y - map.yPos));
                            } catch (IOException ex) {
                                Logger.getLogger(TestScreen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case W: {
                            try {
                                client.sendMessage(new Request(id, 1, Mouse.getPosition(window).x - map.xPos, Mouse.getPosition(window).y - map.yPos));
                            } catch (IOException ex) {
                                Logger.getLogger(TestScreen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case E: {
                            try {
                                client.sendMessage(new Request(id, 2, Mouse.getPosition(window).x - map.xPos, Mouse.getPosition(window).y - map.yPos));
                            } catch (IOException ex) {
                                Logger.getLogger(TestScreen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case R: {
                            try {
                                client.sendMessage(new Request(id, 3, Mouse.getPosition(window).x - map.xPos, Mouse.getPosition(window).y - map.yPos));
                            } catch (IOException ex) {
                                Logger.getLogger(TestScreen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case D: {
                            try {
                                client.sendMessage(new Request(id, 4, Mouse.getPosition(window).x - map.xPos, Mouse.getPosition(window).y - map.yPos));
                            } catch (IOException ex) {
                                Logger.getLogger(TestScreen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case F: {
                            try {
                                client.sendMessage(new Request(id, 5, Mouse.getPosition(window).x - map.xPos, Mouse.getPosition(window).y - map.yPos));
                            } catch (IOException ex) {
                                Logger.getLogger(TestScreen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case SPACE:
                            if (player != null) {
                                map.center(player);
                                xPos = map.xPos;
                                yPos = map.yPos;
                            }
                            break;
                    }
                    break;
                case MOUSE_BUTTON_PRESSED:
                    switch (event.asMouseButtonEvent().button) {
                        case RIGHT: {
                            try {
                                client.sendMessage(new Request(id, 6, event.asMouseButtonEvent().position.x - map.xPos, event.asMouseButtonEvent().position.y - map.yPos));
                            } catch (IOException ex) {
                                Logger.getLogger(TestScreen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    }
                    break;
            }
        }
        if (Mouse.getPosition(window).x <= 0) {
            xPos += 3;
        }
        if (Mouse.getPosition(window).x >= window.getSize().x - 10) {
            xPos -= 3;
        }
        if (Mouse.getPosition(window).y <= 0) {
            yPos += 3;
        }
        if (Mouse.getPosition(window).y >= window.getSize().y - 10) {
            yPos -= 3;
        }
        return this;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
