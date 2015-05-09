package game;

import java.io.IOException;
import java.util.ArrayList;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

public class Map {

    ArrayList<MapObject> sprites;
    Texture background;
    int sizex;
    int sizey;
    int xPos;
    int yPos;
    RenderWindow frame;

    /**
     * For testing purposes only
     *
     * @deprecated
     */
    public Map(RenderWindow window) {
        frame = window;
        sizex = frame.getSize().x;
        sizey = frame.getSize().y;
        xPos = 0;
        yPos = 0;
        background = new Texture();
        sprites = new ArrayList<MapObject>();
    }

    public void loadMap(String path) throws IOException {
        background.loadFromStream(ClassLoader.getSystemResourceAsStream("res/samplemap.png"));
    }

    public void addSprite(MapObject object) {
        sprites.add(object);
    }

    public void removeSprite(MapObject object) {
        sprites.remove(object);
    }

    public void display() {
        frame.clear();
        Sprite buffer = new Sprite();
        buffer.setTexture(background);
        buffer.setPosition(xPos, yPos);
        frame.draw(buffer);
        for (MapObject i : sprites) {
            if (i.remove) {
                sprites.remove(i);
            }
            if (!i.display) {
                continue;
            }
            i.texture.setPosition(i.xPos - xPos, i.yPos - yPos);
            frame.draw(i.texture);
        }

        frame.display();
    }
}
