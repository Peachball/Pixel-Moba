package game.hitboxes;

import org.jsfml.system.Vector2i;

public class Hitbox {

    //Bounding Box

    Vector2i a;
    Vector2i b;
    Vector2i c;
    Vector2i d;

    public Hitbox(Vector2i a, Vector2i b, Vector2i c, Vector2i d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

}
