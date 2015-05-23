package game.hitboxes;

import org.jsfml.system.Vector2i;

public class RectangleBox extends Hitbox {

    Vector2i w, x, y, z;

    public RectangleBox(Vector2i a, Vector2i b, Vector2i c, Vector2i d) {
        w = a;
        x = b;
        y = c;
        z = d;
    }

    @Override
    public boolean intersectsWith(Hitbox b) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
