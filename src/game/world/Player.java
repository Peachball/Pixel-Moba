package game.world;

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

    public Player(Sprite texture, int sizex, int sizey, float posx, float posy) {
        super(texture, sizex, sizey, posx, posy);
        hp = 0;
        maxhp = 0;
        mana = 0;
        speed = 0;
        def = 0;
        dmg = 0;
        hpregen = 0;
        xTar = 10;
        yTar = 10;
    }

    public abstract void useQ();

    public abstract void useW();

    public abstract void useE();

    public abstract void useR();

    public abstract void useD();

    public abstract void useF();

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
        }
        else {
            angle = Math.atan(y / x);
        }
        shift((float) Math.cos(angle) * speed, (float) Math.sin(angle) * speed);
    }

}
