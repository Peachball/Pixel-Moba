package game.hitboxes;

import org.jsfml.system.Vector2i;

public abstract class Hitbox {

    //Bounding Box (top left and lower right)
    public Vector2i a;
    public Vector2i b;
    public abstract boolean intersectsWith(Hitbox b);

}
