package game.world;

import java.io.IOException;
import java.util.ArrayList;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

public class Map {

    ArrayList<MapObject> sprites;
    Texture background;
    public int sizex;
    public int sizey;
    public int xPos;
    public int yPos;
    RenderWindow frame;

    /**
     * For testing purposes only
     *
     * @param window
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

    //Shouldn't use this system...maybe use multiple threads later?
    //Will this be slow...?
    public void update() {
        for (MapObject i : sprites) {
            i.update();
        }
    }

    /**
     * Doesn't clear the screen now
     */
    public void display() {
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

    }
}
