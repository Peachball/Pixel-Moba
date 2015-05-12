package game.hitboxes;

import org.jsfml.system.Vector2i;

public class CircleBox extends Hitbox {

    public int radius;
    public Vector2i origin;

    public CircleBox(Vector2i a, Vector2i b) {
        origin = new Vector2i((a.x + b.x) / 2, (a.y + b.y) / 2);
        radius = Math.abs((a.x - b.x) / 2);
        this.a = a;
        this.b = b;
    }
    
    public CircleBox(Vector2i origin, int radius){
        this.origin = origin;
        this.radius = radius;
    }

    @Override
    public boolean intersectsWith(Hitbox b) {
        if (b instanceof CircleBox) {
            CircleBox buffer = (CircleBox) b;
            return Math.sqrt((buffer.origin.x - origin.x) * (buffer.origin.x - origin.x) + (buffer.origin.y - origin.y) * (buffer.origin.y - origin.y)) <= buffer.radius + radius;
        }
        return false;
    }

}
