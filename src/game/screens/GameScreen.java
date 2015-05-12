package game.screens;

import game.world.Map;
import game.world.champions.Player;
import game.world.champions.God;
import java.io.IOException;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.View;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;
import static org.jsfml.window.event.Event.Type.CLOSED;
import static org.jsfml.window.event.Event.Type.KEY_PRESSED;
import static org.jsfml.window.event.Event.Type.MOUSE_BUTTON_PRESSED;

public class GameScreen extends Screen {

    Map map;
    Player player;

    /**
     * For testing purposes only
     *
     * @param window
     * @deprecated
     */
    @Deprecated
    public GameScreen(RenderWindow window) throws IOException {
        super(window);
        map = new Map(window);

        //Build a sample sprite
        Texture buffer = Frame.loadImage("ninja.jpg");
        Sprite player2 = new Sprite();
        player2.setTexture(buffer);
        player = new God();
        player.setOriginMiddle();
        map.addSprite(player);
    }

    @Override
    public Screen update() {
        map.update();
        window.clear();
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
                            player.useQ(Mouse.getPosition(window).x - map.xPos, Mouse.getPosition(window).y - map.yPos);
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

            }
        }
        return this;
    }

}
