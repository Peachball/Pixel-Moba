package game.world.champions;

import game.hitboxes.CircleBox;
import game.hitboxes.Hitbox;
import game.world.MapObject;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

public abstract class Player extends MapObject {

    int size;

    //List some stats
    public int hp;
    public int maxhp;
    public int mana;
    public int maxmana;
    public int speed;
    public int def;
    public int dmg;
    public double hpregen;

    //To see which direction the champion wants to move
    float xTar;
    float yTar;

    public Hitbox hitbox;

    private void init() {
        size = 10;
        hp = 0;
        maxhp = 0;
        mana = 0;
        speed = 0;
        def = 0;
        dmg = 0;
        hpregen = 0;
        xTar = 10;
        yTar = 10;
        remove = false;
        display = true;
        hitbox = new CircleBox(new Vector2f(xPos, yPos), 10);
    }

    public Player(Sprite texture, int sizex, int sizey, float posx, float posy) {
        super(texture, sizex, sizey, posx, posy);
        init();
    }

    public Player() {
        super();
        init();
    }

    public abstract void useQ(int x, int y);

    public abstract void useW(int x, int y);

    public abstract void useE(int x, int y);

    public abstract void useR(int x, int y);

    public abstract void useD(int x, int y);

    public abstract void useF(int x, int y);

    public abstract void levelup();

    public void setTarget(float x, float y) {
        xTar = x;
        yTar = y;
    }

    @Override
    public void update() {
        if (Math.sqrt(Math.pow(xTar - xPos, 2) + Math.pow(yTar - yPos, 2)) < speed) {
            xPos = xTar;
            yPos = yTar;
            return;
        }
        float x = xTar - xPos;
        float y = yTar - yPos;
        double angle;
        if (x < 0) {
            angle = Math.atan(y / x) + Math.PI;
        } else {
            angle = Math.atan(y / x);
        }
        double shiftx = Math.cos(angle) * speed;
        double shifty = Math.sin(angle) * speed;
        if (xPos + shiftx < 0 || xPos + shiftx > map.sizex || yPos + shifty < 0 || yPos + shifty > map.sizey) {
            return;
        }
        shift((float) shiftx, (float) shifty);
        if (hp < 0) {
            remove = true;
        }
    }

}
