package game.world;

import org.jsfml.graphics.Sprite;

public abstract class Player extends MapObject {
    
    //List some stats
    int hp;
    int maxhp;
    int mana;
    int maxmana;
    int speed;
    int def;
    int dmg;
    int hpregen;

    public Player(Sprite texture, int sizex, int sizey, float posx, float posy) {
        super(texture, sizex, sizey, posx, posy);
        hp = 0;
        maxhp = 0;
        mana = 0;
        speed = 0;
        def = 0;
        dmg = 0;
        hpregen = 0;
    }
    
    
    public abstract void useQ();
    public abstract void useW();
    public abstract void useE();
    public abstract void useR();
    public abstract void useD();
    public abstract void useF();
    public abstract void levelup();

}
