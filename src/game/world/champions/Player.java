package game.world.champions;

import game.world.MapObject;
import org.jsfml.graphics.Sprite;

public abstract class Player extends MapObject {

    //List some stats
    public int hp;
    public int maxhp;
    public int mana;
    public int maxmana;
    public int speed;
    public int def;
    public int dmg;
    public int hpregen;

    //To see which direction the champion wants to move
    float xTar;
    float yTar;

    private void init() {
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
        float x = xTar - xPos;
        float y = yTar - yPos;
        double angle;
        if (x < 0) {
            angle = Math.atan(y / x) + Math.PI;
        } else {
            angle = Math.atan(y / x);
        }
        shift((float) Math.cos(angle) * speed, (float) Math.sin(angle) * speed);
    }

}
