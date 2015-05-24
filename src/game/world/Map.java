package game.world;

import game.hitboxes.Hitbox;
import game.world.bullets.Bullet;
import game.world.champions.Player;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;

public class Map implements Serializable {

    volatile ArrayList<MapObject> sprites;
    Texture background;
    public int sizex;
    public int sizey;
    public int xPos;
    public int yPos;
    RenderWindow frame;
    private Texture green;
    private Text text;

    protected void init() throws IOException {

        xPos = 0;
        yPos = 0;
        background = new Texture();
        sprites = new ArrayList<MapObject>();
        green = new Texture();
        green.loadFromStream(ClassLoader.getSystemResourceAsStream("res/healthbar.png"));
        text = new Text();
        Font font = new Font();
        font.loadFromStream(ClassLoader.getSystemResourceAsStream("res/Time Roman.ttf"));
        text.setFont(font);
    }

    public void linkWindow(RenderWindow window) {
        frame = window;
    }

    /**
     * For testing purposes only
     *
     * @param window
     * @deprecated
     */
    public Map(RenderWindow window) throws IOException {
        init();
        if (window == null) {
            sizex = 1000;
            sizey = 1000;
        } else {
            frame = window;
            sizex = frame.getSize().x;
            sizey = frame.getSize().y;
        }
    }

    public Map(RenderWindow window, int xsize, int ysize) throws IOException {
        init();
        frame = window;
        this.sizex = xsize;
        this.sizey = ysize;
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
        Collections.sort(sprites, new MapObjectComparator());
        for (MapObject i : sprites) {
            Hitbox buffer;
            Hitbox buffer2;
            if (i instanceof Bullet) {
                buffer = ((Bullet) i).hitbox;
            } else if (i instanceof Player) {
                buffer = ((Player) i).hitbox;
            } else {
                continue;
            }
            for (MapObject i2 : sprites) {
                if (i2 instanceof Bullet) {
                    buffer2 = ((Bullet) i2).hitbox;
                } else if (i2 instanceof Player) {
                    buffer2 = ((Player) i2).hitbox;
                } else {
                    continue;
                }
                if (buffer.intersectsWith(buffer2)) {
                    i.intersectsWith(i2);
                }
            }
        }
    }

    public void display() throws IOException {
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
            if (sprites.get(counter) instanceof Player) {
                float x = sprites.get(counter).xPos;
                float y = sprites.get(counter).yPos;
                Player player = (Player) sprites.get(counter);
                Sprite healthBar = new Sprite();

                healthBar.setTexture(green);
                healthBar.setColor(Color.GREEN);
                healthBar.setPosition(player.xPos - (float) (player.sizex / 2.0) + xPos, player.yPos - player.sizey + yPos);
                healthBar.setTextureRect(new IntRect(0, 0, (int) (player.hp * 1.0 / player.maxhp * player.sizex), 5));
                frame.draw(healthBar);
                text.setString("GOD IS OP");
            }
            sprites.get(counter).texture.setPosition(sprites.get(counter).xPos + xPos, sprites.get(counter).yPos + yPos);
            frame.draw(sprites.get(counter).texture);
        }
    }

    public void center(MapObject player) {
        xPos = -(int) player.xPos;
        yPos = -(int) player.yPos;
    }
}

class MapObjectComparator implements Comparator<MapObject> {

    @Override
    public int compare(MapObject o1, MapObject o2) {
        return Math.round(o1.xPos - o2.xPos);
    }

}
