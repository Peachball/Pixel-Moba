package game.world;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

public class Map implements Serializable {

    volatile ArrayList<MapObject> sprites;
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

    public Map(RenderWindow window, int xsize, int ysize) {
        xPos = 0;
        frame = window;
        sizex = xsize;
        sizey = ysize;
        background = new Texture();
        sprites = new ArrayList<MapObject>();
    }

    public void loadMap(String path) throws IOException {
        background.loadFromStream(ClassLoader.getSystemResourceAsStream("res/" + path));
        background.setRepeated(true);
    }

    public void addSprite(MapObject object) {
        sprites.add(object);
        object.map = this;
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
        buffer.setTextureRect(new IntRect(0, 0, sizex, sizey));
        buffer.setPosition(xPos, yPos);
        frame.draw(buffer);
        for (int counter = 0; counter < sprites.size(); counter++) {
            if (sprites.get(counter).remove) {
                sprites.remove(counter);
                counter--;
                continue;
            }
            if (!sprites.get(counter).display) {
                continue;
            }
            sprites.get(counter).texture.setPosition(sprites.get(counter).xPos + xPos, sprites.get(counter).yPos + yPos);
            frame.draw(sprites.get(counter).texture);
        }
    }

    public void center(MapObject player) {
        xPos =-(int) player.xPos;
        yPos = -(int)player.yPos;
    }
}

class MapObjectComparator implements Comparator<MapObject> {

    @Override
    public int compare(MapObject o1, MapObject o2) {
        return Math.round(o1.xPos - o2.xPos);
    }

}
