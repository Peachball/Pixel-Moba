package game.screens;

import game.world.Map;
import game.world.MapObject;
import java.io.IOException;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import static org.jsfml.window.Keyboard.Key.DOWN;
import static org.jsfml.window.Keyboard.Key.LEFT;
import static org.jsfml.window.Keyboard.Key.RIGHT;
import static org.jsfml.window.Keyboard.Key.UP;
import org.jsfml.window.event.Event;
import static org.jsfml.window.event.Event.Type.CLOSED;
import static org.jsfml.window.event.Event.Type.KEY_PRESSED;
import static org.jsfml.window.event.Event.Type.MOUSE_BUTTON_PRESSED;

public class GameScreen extends Screen {
    
    Map map;
    MapObject player;

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
        player = new MapObject(player2, 20, 20, 0, 0);
        player.setOriginMiddle();
        map.addSprite(player);
        
    }
    
    @Override
    public Screen update() {
        map.display();

        //Event loop
        Event buffer;
        while ((buffer = window.pollEvent()) != null) {
            switch (buffer.type) {
                case CLOSED:
                    window.close();
                    break;
                case KEY_PRESSED:
                    switch (buffer.asKeyEvent().key) {
                        case UP:
                            player.setPosition(player.texture.getPosition().x, player.texture.getPosition().y + 1);
                            break;
                        case RIGHT:
                            player.setPosition(player.texture.getPosition().x + 1, player.texture.getPosition().y);
                            break;
                        case DOWN:
                            player.setPosition(player.texture.getPosition().x, player.texture.getPosition().y - 1);
                            break;
                        case LEFT:
                            player.setPosition(player.texture.getPosition().x - 1, player.texture.getPosition().y);
                            break;
                        case ESCAPE:
                            window.close();
                            break;
                    }
                    break;
                case MOUSE_BUTTON_PRESSED:
                    switch (buffer.asMouseButtonEvent().button) {
                        case RIGHT:
                            
                            double x = buffer.asMouseButtonEvent().position.x - player.texture.getPosition().x;
                            double y = buffer.asMouseButtonEvent().position.y - player.texture.getPosition().y;
                            double angle;
                            if (x < 0) {
                                angle = Math.atan(y / x) + Math.PI;
                            } else {
                                angle = Math.atan(y / x) ;
                            }
                            player.shift((float) Math.cos(angle) * 10, (float) Math.sin(angle) * 10);
                            break;
                    }
            }
        }
        return this;
    }
    
}
