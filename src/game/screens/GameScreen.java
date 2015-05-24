package game.screens;

import game.world.Map;
import game.world.champions.Player;
import game.world.champions.God;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.View;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;
import static org.jsfml.window.event.Event.Type.CLOSED;
import static org.jsfml.window.event.Event.Type.KEY_PRESSED;
import static org.jsfml.window.event.Event.Type.MOUSE_BUTTON_PRESSED;

public class GameScreen extends Screen {

    Map map;
    Player player;
    Player ai;

    /**
     * For testing purposes only
     *
     * @param window
     * @deprecated
     */
    @Deprecated
    public GameScreen(RenderWindow window) throws IOException {
        super(window);
        map = new Map(window, 2000, 2000);

        //Build a sample sprite
        player = new God();
        player.setOriginMiddle();
        map.addSprite(player);
        map.loadMap("samplemap.png");
        ai = new God();
        ai.setOriginMiddle();
        map.addSprite(ai);
    }

    @Override
    public Screen update() {
        map.update();
        window.clear(Color.WHITE);
        
            map.display();

        window.display();
        
        //Event loop
        Event buffer;
        while ((buffer = window.pollEvent()) != null) {
            switch (buffer.type) {
                case CLOSED:
                    window.close();
                    break;
                case KEY_PRESSED:
                    switch (buffer.asKeyEvent().key) {
                        case ESCAPE:
                            window.close();
                            break;
                        case Q:
                            player.useQ(Mouse.getPosition(window).x -map.xPos, Mouse.getPosition(window).y- map.yPos);
                            break;
                        case E:
                            player.useE(Mouse.getPosition(window).x -map.xPos, Mouse.getPosition(window).y- map.yPos);
                            break;
                        case D:
                            player.useD(Mouse.getPosition(window).x -map.xPos, Mouse.getPosition(window).y- map.yPos);
                            break;
                        case SPACE:
                            map.center(player);
                            map.xPos += window.getSize().x/2;
                            map.yPos += window.getSize().y/2;
                            break;
                        case A:
                            window.create(VideoMode.getFullscreenModes()[0], "EYY",Window.FULLSCREEN);
                            break;
                        case B:
                            window.create(new VideoMode(800,600),"SMALL");
                            break;
                    }
                    break;
                case MOUSE_BUTTON_PRESSED:
                    switch (buffer.asMouseButtonEvent().button) {
                        case RIGHT:
                            player.setTarget(buffer.asMouseButtonEvent().position.x - map.xPos, buffer.asMouseButtonEvent().position.y - map.yPos);
                            break;
                    }
                case RESIZED:
                    window.setView(new View(new FloatRect(0, 0, window.getSize().x, window.getSize().y)));
                    break;

            }
        }
        if (Mouse.getPosition(window).x <= 0) {
            map.xPos++;
        }
        if (Mouse.getPosition(window).x >= window.getSize().x -10) {
            map.xPos--;
        }
        if (Mouse.getPosition(window).y <= 0) {
            map.yPos++;
        }
        if (Mouse.getPosition(window).y >= window.getSize().y-10) {
            map.yPos--;
        }
        return this;
    }

}
